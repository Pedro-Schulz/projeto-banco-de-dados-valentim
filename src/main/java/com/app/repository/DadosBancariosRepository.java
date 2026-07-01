package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.DadosBancarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DadosBancariosRepository {

    public void salvar(DadosBancarios dadosBancarios) {
        String sql = """
            INSERT INTO dados_bancarios (numero_conta, agencia_bancaria, instituicao_bancaria, id_funcionario)
            VALUES (?, ?, ?, ?);
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            p.setInt(1, dadosBancarios.getNumeroConta());
            p.setString(2, dadosBancarios.getAgenciaBancaria());
            p.setString(3, dadosBancarios.getInstituicaoBancaria());
            p.setLong(4, dadosBancarios.getFuncionario().getIdFuncionario());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                Long id = rs.getLong(1);
                dadosBancarios.getFuncionario().setIdFuncionario(id);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar um conjunto de dados bancários!", e);
        }
    }
}
