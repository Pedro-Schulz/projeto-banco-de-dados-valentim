package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Contrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ContratoRepository {

    public void salvar(Contrato contrato) throws RuntimeException {
        String sql = """
                    INSERT INTO contratos (status_contrato, data_emissao, prazo, id_funcionario)
                    VALUES (?, ?, ?, ?);
                """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            p.setBoolean(1, contrato.getStatusContrato());
            p.setDate(2, java.sql.Date.valueOf(contrato.getDataEmissao()));
            p.setInt(3, contrato.getPrazo());
            p.setLong(4, contrato.getFuncionario().getIdFuncionario());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if (rs.next()) {
                Long id = rs.getLong(1);
                contrato.setIdContrato(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar contrato!", e);
        }
    }

    public void desativar(Long id) throws RuntimeException {
        String sql = """
            UPDATE contratos
            SET ativo = FALSE
            WHERE id_contrato = ?;
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id);

            p.executeUpdate();
        } catch(Exception e) {
            throw new RuntimeException("Erro ao desativar contrato!", e);
        }
    }

    public boolean vinculoFuncionario(Long id_funcionario) throws RuntimeException {
        String sql = """
                    SELECT 1
                    FROM contratos
                    WHERE id_funcionario = ? AND ativo = 1
                    LIMIT 1;        
                """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id_funcionario);

            ResultSet rs = p.executeQuery();

            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar vinculo contrato -> funcionário", e);
        }
    }
}