package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VagaRepository {

    // 1. Cadastrar/Salvar nova Vaga
    public void salvar(Vaga vaga) {
        String sql = """
            INSERT INTO vagas (turno, salario_hora, cargo, id_departamento, ativo, version)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            p.setString(1, vaga.getTurno());
            p.setDouble(2, vaga.getSalarioHora());
            p.setString(3, vaga.getCargo());
            p.setObject(4, vaga.getIdDepartamento());
            p.setBoolean(5, vaga.isAtivo());
            p.setInt(6, 1);

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                vaga.setIdVaga(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar vaga!", e);
        }
    }

    // 2. Compatibilidade: cadastrarVaga retornando boolean
    public boolean cadastrarVaga(Vaga vaga) {
        try {
            salvar(vaga);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 3. Listar todas as vagas (Ativas e Inativas)
    public ArrayList<Vaga> listarTodos() {
        ArrayList<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vagas;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
                ResultSet rs = p.executeQuery()
        ) {
            while (rs.next()) {
                Vaga vaga = new Vaga(
                        rs.getLong("id_vaga"),
                        rs.getString("turno"),
                        rs.getDouble("salario_hora"),
                        rs.getString("cargo"),
                        rs.getObject("id_departamento") != null ? rs.getLong("id_departamento") : null,
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );
                vagas.add(vaga);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vagas;
    }

    // 4. Listar APENAS as vagas ativas
    public ArrayList<Vaga> listarTodasAtivas() {
        ArrayList<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vagas WHERE ativo = true;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
                ResultSet rs = p.executeQuery()
        ) {
            while (rs.next()) {
                Vaga vaga = new Vaga(
                        rs.getLong("id_vaga"),
                        rs.getString("turno"),
                        rs.getDouble("salario_hora"),
                        rs.getString("cargo"),
                        rs.getObject("id_departamento") != null ? rs.getLong("id_departamento") : null,
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );
                vagas.add(vaga);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vagas;
    }

    // 5. Buscar vaga por ID
    public Vaga buscarPorId(Long id) {
        String sql = "SELECT * FROM vagas WHERE id_vaga = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                return new Vaga(
                        rs.getLong("id_vaga"),
                        rs.getString("turno"),
                        rs.getDouble("salario_hora"),
                        rs.getString("cargo"),
                        rs.getObject("id_departamento") != null ? rs.getLong("id_departamento") : null,
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar vaga por ID!", e);
        }
        return null;
    }

    // 6. Desativar vaga por ID
    public boolean desativar(long idVaga) {
        String sql = "UPDATE vagas SET ativo = false WHERE id_vaga = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idVaga);
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 7. Desativar todas as vagas vinculadas a um Departamento por ID
    public void desativarPorDepartamento(Long idDepartamento) {
        String sql = "UPDATE vagas SET ativo = false WHERE id_departamento = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idDepartamento);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar vagas do departamento!", e);
        }
    }

    // 8. Verificar se existe vínculo de vagas com um Departamento
    public boolean vinculoDepartamento(Long idDepartamento) {
        String sql = "SELECT 1 FROM vagas WHERE id_departamento = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idDepartamento);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Busca todas as vagas ativas de um determinado departamento
    public ArrayList<Vaga> buscarPorDepartamento(Long idDepartamento) throws RuntimeException {
        ArrayList<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vagas WHERE id_departamento = ? AND ativo = true;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idDepartamento);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                vagas.add(new Vaga(
                        rs.getLong("id_vaga"),
                        rs.getString("turno"),
                        rs.getDouble("salario_hora"),
                        rs.getString("cargo"),
                        rs.getLong("id_departamento"),
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar vagas por departamento!", e);
        }

        return vagas;
    }
}