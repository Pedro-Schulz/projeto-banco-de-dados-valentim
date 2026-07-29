package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Candidatura;
import com.app.model.Funcionario;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CandidaturaRepository {

    public void salvar(Candidatura candidatura) {
        String sql = """
            INSERT INTO candidaturas (id_funcionario, id_vaga, data_candidatura, status, ativo)
            VALUES (?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setLong(1, candidatura.getFuncionario().getIdFuncionario());
            p.setLong(2, candidatura.getVaga().getIdVaga());
            p.setDate(3, Date.valueOf(candidatura.getDataCandidatura()));
            p.setString(4, candidatura.getStatus());
            p.setBoolean(5, candidatura.isAtivo());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                candidatura.setIdCandidatura(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar candidatura!");
        }
    }

    public ArrayList<Candidatura> listarTodos() {
        ArrayList<Candidatura> lista = new ArrayList<>();
        String sql = "SELECT * FROM candidaturas;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id_funcionario"));

                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getLong("id_vaga"));

                Candidatura candidatura = new Candidatura(
                        rs.getLong("id_candidatura"),
                        funcionario,
                        vaga,
                        rs.getDate("data_candidatura").toLocalDate(),
                        rs.getString("status"),
                        rs.getBoolean("ativo")
                );
                lista.add(candidatura);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar candidaturas!");
        }
        return lista;
    }

    public Candidatura buscarPorId(Long id) {
        String sql = "SELECT * FROM candidaturas WHERE id_candidatura = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id_funcionario"));

                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getLong("id_vaga"));

                return new Candidatura(
                        rs.getLong("id_candidatura"),
                        funcionario,
                        vaga,
                        rs.getDate("data_candidatura").toLocalDate(),
                        rs.getString("status"),
                        rs.getBoolean("ativo")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar candidatura por ID!");
        }
        return null;
    }

    public void atualizar(Candidatura candidatura) throws RuntimeException {
        String sql = """
            UPDATE candidaturas
            SET status_candidatura = ?,data_candidatura = ?, prazo = ?, etapa = ?, version = version + 1
            WHERE id_candidatura = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setBoolean(1, candidatura.getStatusCandidatura());
            p.setDate(2, Date.valueOf(candidatura.getDataCandidatura()));
            p.setDate(3, Date.valueOf(candidatura.getPrazo()));
            p.setString(4, candidatura.getEtapa());
            p.setLong(5, candidatura.getIdCandidatura());
            p.setInt(6, candidatura.getVersion());

            if(p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            candidatura.setVersion(candidatura.getVersion() + 1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar candidatura!");
        }
    }

    public void desativar(Long id) {
        String sql = "UPDATE candidaturas SET ativo = false WHERE id_candidatura = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar candidatura!");
        }
    }

    public void desativarPorVaga(Long idVaga) {
        String sql = "UPDATE candidaturas SET ativo = false WHERE id_vaga = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idVaga);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar candidaturas por vaga!");
        }
    }

    public void desativarPorCandidato(Long idCandidato) {
        String sql = "UPDATE candidaturas SET ativo = false WHERE id_funcionario = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idCandidato);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar candidaturas por candidato!");
        }
    }

    public boolean vinculoCandidato(Long idCandidato) {
        String sql = "SELECT 1 FROM candidaturas WHERE id_funcionario = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idCandidato);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo com o candidato!");
        }
    }

    public boolean vinculoVaga(Long idVaga) {
        String sql = "SELECT 1 FROM candidaturas WHERE id_vaga = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idVaga);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo com a vaga!");
        }
    }
}