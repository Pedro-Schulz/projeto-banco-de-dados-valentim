package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VagaRepository {

    public void salvar(Vaga vaga) {
        String sql = """
            INSERT INTO vagas (cargo, salario_hora, turno, ativo)
            VALUES (?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setString(1, vaga.getTituloVaga());
            p.setDouble(2, vaga.getSalario()); // Converte o double diretamente para o JDBC
            p.setString(3, "Integral");        // Campo turno padrão obrigatório na tabela
            p.setBoolean(4, vaga.isDisponivel());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                vaga.setIdVaga(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar vaga!");
        }
    }

    public ArrayList<Vaga> listarTodos() {
        ArrayList<Vaga> lista = new ArrayList<>();
        String sql = "SELECT * FROM vagas;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getLong("id_vaga"));
                vaga.setTituloVaga(rs.getString("cargo"));
                vaga.setSalario(rs.getDouble("salario_hora")); // Lê como double
                vaga.setDisponivel(rs.getBoolean("ativo"));    // Usa setDisponivel

                lista.add(vaga);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar vagas!");
        }
        return lista;
    }

    public Vaga buscarPorId(Long id) {
        String sql = "SELECT * FROM vagas WHERE id_vaga = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getLong("id_vaga"));
                vaga.setTituloVaga(rs.getString("cargo"));
                vaga.setSalario(rs.getDouble("salario_hora"));
                vaga.setDisponivel(rs.getBoolean("ativo"));

                return vaga;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar vaga por ID!");
        }
        return null;
    }

    public void desativar(Long id) {
        String sql = "UPDATE vagas SET ativo = false WHERE id_vaga = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar vaga!");
        }
    }

    public void desativarPorDepartamento(Long idDepartamento) {
        String sql = "UPDATE vagas SET ativo = false WHERE id_departamento = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idDepartamento);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar vagas do departamento!");
        }
    }

    public boolean vinculoDepartamento(Long idDepartamento) {
        String sql = "SELECT 1 FROM vagas WHERE id_departamento = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idDepartamento);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo com departamento!");
        }
    }
}