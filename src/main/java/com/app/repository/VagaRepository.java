package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Departamento;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class VagaRepository {

    public void salvar(Vaga vaga) {
        String sql = """
            INSERT INTO (turno, cargo, salario_hora, id_departamento)
            VALUES (?, ?, ?, ?);        
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            p.setString(1, vaga.getTurno());
            p.setString(2, vaga.getCargo());
            p.setDouble(3, vaga.getSalarioHora());
            p.setInt(4, vaga.getDepartamento().getIdDepartamento());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                int id = rs.getInt(1);
                vaga.getDepartamento().setIdDepartamento(id);
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao criar uma vaga!", e);
        }
    }

    public static Vaga buscarPorId(Long id) {
        String sql = """
            SELECT id_vaga, turno, cargo, salario_hora, id_departamento
            FROM vagas
            WHERE id_vaga = ?;
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                Long idVaga = rs.getLong("id_vega");
                Departamento departamento = DepartamentoRepository.buscarPorId(idVaga);

                Vaga vaga = new Vaga(
                    rs.getLong("id_vaga"),
                    rs.getString("turno"),
                    rs.getString("cargo"),
                    rs.getDouble("salario_hora"),
                    departamento
                );
            }

        } catch(Exception e) {
            throw new RuntimeException("Erro ao achar vaga pelo ID!", e);
        }
        return null;

    }
}
