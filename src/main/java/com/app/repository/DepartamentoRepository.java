package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.DadosBancarios;
import com.app.model.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartamentoRepository {

    public void salvar(Departamento departamento) throws RuntimeException {
        String sql = """
            INSERT INTO departamentos (nome, gastos, retorno, ativo)
            VALUES (?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setString(1, departamento.getNome());
            p.setDouble(2, departamento.getGastos());
            p.setDouble(3, departamento.getRetorno());
            p.setBoolean(4, departamento.getAtivo());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if (rs.next()) {
                Long id = rs.getLong(1);
                departamento.setIdDepartamento(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar departamento!", e);
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

            if (rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(rs.getLong("id_departamento"));
                departamento.setNome(rs.getString("nome"));
                departamento.setGastos(rs.getDouble("gastos"));
                departamento.setRetorno(rs.getDouble("retorno"));
                departamento.setAtivo(rs.getBoolean("ativo"));
                departamento.setVersion(rs.getInt("version"));

                return departamento;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar o departamento pelo ID!", e);
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

            while (rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(rs.getLong("id_departamento"));
                departamento.setNome(rs.getString("nome"));
                departamento.setGastos(rs.getDouble("gastos"));
                departamento.setRetorno(rs.getDouble("retorno"));
                departamento.setAtivo(rs.getBoolean("ativo"));
                departamento.setVersion(rs.getInt("version"));

                departamentos.add(departamento);
            }
            return departamentos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar os departamentos!", e);
        }
    }

    public void atualizar(Departamento departamento) throws RuntimeException {
        String sql = """
            UPDATE departamentos
            SET nome = ?,gastos = ?, retorno = ?, ativo = ?,version = version + 1
            WHERE id_departamento = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setString(1, departamento.getNome());
            p.setDouble(2, departamento.getGastos());
            p.setDouble(3, departamento.getRetorno());
            p.setBoolean(4, departamento.getAtivo());
            p.setLong(5, departamento.getIdDepartamento());
            p.setInt(6, departamento.getVersion());

            if(p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            departamento.setVersion(departamento.getVersion() + 1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar departamento!");
        }
    }

    public void desativar(Long id) throws RuntimeException {
        String sql = """
            UPDATE departamentos
            SET ativo = false
            WHERE id_departamento = ? AND ativo = true;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            p.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar departamento!", e);
        }
    }
}