package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Candidato;
import com.app.model.Candidatura;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.*;

public class CandidaturaRepository {

    public void salvar(Candidatura candidatura) {
        String sql = """
            INSERT INTO candidaturas (status_candidatura, data_candidatura, prazo, etapa, id_vaga, id_candidato)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setBoolean(1, candidatura.getStatusCandidatura());
            p.setDate(2, Date.valueOf(candidatura.getDataCandidatura()));
            p.setDate(3, Date.valueOf(candidatura.getPrazo()));
            p.setString(4, candidatura.getEtapa());
            p.setLong(5, candidatura.getVaga().getIdVaga());
            p.setLong(6, candidatura.getCandidato().getIdCandidato());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                Long id = rs.getLong(1);
                candidatura.setIdCandidatura(id);
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao salvar candidatura!", e);
        }
    }

    public Candidatura buscarPorId(Long id) {
        String sql = """
            SELECT *
            FROM candidaturas
            WHERE id_candidatura = ?;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                Candidatura candidatura = new Candidatura(
                        rs.getLong("id_candidatura"),
                        rs.getBoolean("status_candidatura"),
                        rs.getDate("data_candidatura").toLocalDate(),
                        rs.getDate("prazo").toLocalDate(),
                        rs.getString("etapa"),
                        new Vaga(rs.getLong("id_vaga")),
                        new Candidato(rs.getLong("id_candidato")),
                        rs.getBoolean("ativo")
                );

                return candidatura;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar candidatura!", e);
        }
        return null;
    }

    public ArrayList<Candidatura> listarTodos() {
        ArrayList<Candidatura> candidaturas = new ArrayList<>();
        String sql = """
            SELECT c.*, v.id_vaga, candidato.id_candidato
            FROM candidaturas AS c
            JOIN vagas AS v ON v.id_vaga = c.id_vaga
            JOIN candidatos AS candidato ON candidato.id_candidato = c.id_candidato;
        """;
        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();
            while(rs.next()) {
                Candidatura candidatura = new Candidatura(
                        rs.getLong("id_candidatura"),
                        rs.getBoolean("status_candidatura"),
                        rs.getDate("data_candidatura").toLocalDate(),
                        rs.getDate("prazo").toLocalDate(),
                        rs.getString("etapa"),
                        new Vaga(rs.getLong("id_vaga")),
                        new Candidato(rs.getLong("id_candidato")),
                        rs.getBoolean("ativo")
                );
                candidaturas.add(candidatura);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar candidaturas!", e);
        }
        return candidaturas;
    }

    public void desativar(Long id) {
        String sql = """
            UPDATE candidaturas
            SET ativo = FALSE
            WHERE id_candidatura = ?;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            p.executeUpdate();
        } catch(Exception e) {
            throw new RuntimeException("Erro ao desativar candidatura!", e);
        }
    }

    public boolean vinculoVaga(Long id_vaga) throws RuntimeException {
        String sql = """
            SELECT 1
            FROM candidaturas
            WHERE id_vaga = ?
            LIMIT 1;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id_vaga);

            ResultSet rs = p.executeQuery();

            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar vínculo candidatura -> vaga", e);
        }
    }
}
