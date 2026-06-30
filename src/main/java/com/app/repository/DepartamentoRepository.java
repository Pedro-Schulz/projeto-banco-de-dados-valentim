package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartamentoRepository {

    public void salvar(Departamento departamento) {
        String sql = """
            INSERT INTO departamentos (nome, gastos, retorno)
            VALUES (?, ?, ?);
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setString(1, departamento.getNome());
            p.setDouble(2, departamento.getGastos());
            p.setDouble(3, departamento.getRetorno());

            p.executeUpdate();
        } catch(Exception e) {
            throw new RuntimeException("Erro ao salvar departamento!", e);
        }
    }
}
