package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Candidato;
import com.app.model.Candidatura;
import com.app.model.Vaga;

import java.sql.*;

public class CandidaturaRepository {

    public void salvar(Candidatura candidatura) {
        String sql = """
            INSERT INTO candidaturas (status_candidatura, data_candidatura, prazo, etapa, id_vaga, id_candidato)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                VagaRepository vr = new VagaRepository();
                CandidatoRepository cr = new CandidatoRepository();

                Vaga vaga = vr.buscarPorId(rs.getLong("id_vaga"));
                Candidato candidato = cr.buscarPorId(rs.getLong("id_candidato"));

                Candidatura candidatura = new Candidatura(
                        rs.getLong("id_candidatura"),
                        rs.getBoolean("status_candidatura"),
                        rs.getDate("data_candidatura").toLocalDate(),
                        rs.getDate("prazo").toLocalDate(),
                        rs.getString("etapa"),
                        vaga,
                        candidato,
                        rs.getBoolean("ativo")   // ← faltava essa linha
                );

                return candidatura;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar candidatura!", e);
        }
        return null;
    }

    public void desativar(Long id) {
        String sql = """
            UPDATE candidaturas
            SET ativo = FALSE
            WHERE id_candidatura = ?;
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id);

            p.executeUpdate();
        } catch(Exception e) {
            throw new RuntimeException("Erro ao desativar candidatura!", e);
        }
    }
}