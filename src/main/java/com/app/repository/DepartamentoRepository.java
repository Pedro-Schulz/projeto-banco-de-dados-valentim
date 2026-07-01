package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static Departamento buscarPorId(Long id) {
        String sql = """
            SELECT *
            FROM departamentos
            WHERE id_departamento = ?;
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                Departamento departamento = new Departamento(
                    rs.getLong("id_departamento"),
                    rs.getString("nome"),
                    rs.getDouble("gastos"),
                    rs.getDouble("retorno")
                );

                return departamento;
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao buscar o departamento pelo ID!");
        }
        return null;
    }
}
