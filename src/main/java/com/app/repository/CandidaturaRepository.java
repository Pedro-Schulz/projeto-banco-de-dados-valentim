package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Candidato;
import com.app.model.Candidatura;
import com.app.model.Funcionario;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CandidaturaRepository {

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
