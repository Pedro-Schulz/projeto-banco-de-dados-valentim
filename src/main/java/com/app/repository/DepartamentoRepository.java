package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Departamento;

import java.sql.*;
import java.util.ArrayList;

public class DepartamentoRepository {

    public void salvar(Departamento departamento) throws RuntimeException {
        String sql = """
            INSERT INTO departamentos (nome, gastos, retorno, ativo)
            VALUES (?, ?, ?);
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setString(1, departamento.getNome());
            p.setDouble(2, departamento.getGastos());
            p.setDouble(3, departamento.getRetorno());
            p.setBoolean(4, departamento.isAtivo());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                Long id = rs.getLong(1);
                departamento.setIdDepartamento(id);
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar departamento!");
        }
    }

    public Departamento buscarPorId(Long id) throws RuntimeException {
        String sql = """
            SELECT *
            FROM departamentos
            WHERE id_departamento = ?;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                Departamento departamento = new Departamento(
                    rs.getLong("id_departamento"),
                    rs.getString("nome"),
                    rs.getDouble("gastos"),
                    rs.getDouble("retorno"),
                    rs.getBoolean("ativo")
                );

                return departamento;
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao buscar o departamento pelo ID!");
        }
        return null;
    }

    public ArrayList<Departamento> listarTodos() {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        String sql = """
            SELECT *
            FROM departamentos;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();

            while(rs.next()) {
                Departamento departamento = new Departamento(
                    rs.getLong("id_departamento"),
                    rs.getString("nome"),
                    rs.getDouble("gastos"),
                    rs.getDouble("retorno"),
                    rs.getBoolean("ativo")
                );
                departamentos.add(departamento);
            }
            return departamentos;
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar os departamentos! \n");
        }
    }

    public void desativar(Long id) throws RuntimeException {
        String sql = """
            SELECT departamentos
            SET ativo = false
            WHERE id_departamento = ?;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            p.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar departamento!");
        }
    }
}
