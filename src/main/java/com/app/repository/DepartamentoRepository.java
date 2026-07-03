package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Departamento;

import java.sql.*;

public class DepartamentoRepository {

    public void salvar(Departamento departamento) throws RuntimeException {
        String sql = """
            INSERT INTO departamentos (nome, gastos, retorno)
            VALUES (?, ?, ?);
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            p.setString(1, departamento.getNome());
            p.setDouble(2, departamento.getGastos());
            p.setDouble(3, departamento.getRetorno());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                Long id = rs.getLong(1);
                departamento.setIdDepartamento(id);
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao salvar departamento!", e);
        }
    }

    public Departamento buscarPorId(Long id) throws RuntimeException {
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

    public void deletar(Long id) throws RuntimeException {
        String sql = """
            DELETE FROM departamentos
            WHERE id_departamento = ?;
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id);

            p.executeUpdate();

        } catch(Exception e) {
            throw new RuntimeException("Erro ao deletar departamento!", e);
        }
    }
}
