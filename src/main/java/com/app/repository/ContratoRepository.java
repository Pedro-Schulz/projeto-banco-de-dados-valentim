package com.app.repository;

import com.app.config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContratoRepository {
    public void desativar(Long id) throws RuntimeException {
        String sql = """
            UPDATE contratos
            SET ativo = NOT ativo
            WHERE id_contrato = ?;
        """;

        try {

        } catch(Exception e) {
            throw new RuntimeException("Erro ao desativar contrato!", e);
        }
    }

    public boolean vinculoFuncionario(Long id_funcionario) throws RuntimeException {
        String sql = """
            SELECT 1
            FROM contratos
            WHERE id_funcionario = ?
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
