package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Candidato;
import com.app.model.Funcionario;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CandidatoRepository {

    public ArrayList<Candidato> listarTodos() {
        ArrayList<Candidato> candidatos = new ArrayList<>();
        String sql = """
            SELECT *
            FROM candidatos;
        """;
        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();
            while(rs.next()) {
                Candidato candidato = new Candidato(
                        rs.getLong("id_candidato"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("cep"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("genero"),
                        rs.getString("estado_civil"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getBoolean("ativo")
                );
                candidatos.add(candidato);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar candidatos!", e);
        }
        return candidatos;
    }
}
