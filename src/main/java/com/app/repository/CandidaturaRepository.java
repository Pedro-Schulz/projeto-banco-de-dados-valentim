package com.app.repository;

import com.app.config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CandidaturaRepository {
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
