package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Departamento;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class VagaRepository {

    public void salvar(Vaga vaga) throws RuntimeException {
        String sql = """
            INSERT INTO vagas (turno, cargo, salario_hora, id_departamento)
            VALUES (?, ?, ?, ?);        
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            p.setString(1, vaga.getTurno());
            p.setString(2, vaga.getCargo());
            p.setDouble(3, vaga.getSalarioHora());
            p.setLong(4, vaga.getDepartamento().getIdDepartamento());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                Long id = rs.getLong(1);
                vaga.setIdVaga(id);
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao criar uma vaga!", e);
        }
    }

    public Vaga buscarPorId(Long id) throws RuntimeException {
        String sql = """
            SELECT *
            FROM vagas
            WHERE id_vaga = ?;
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                DepartamentoRepository dr = new DepartamentoRepository();

                Long idVaga = rs.getLong("id_vaga");
                Departamento departamento = dr.buscarPorId(idVaga);

                Vaga vaga = new Vaga(
                    rs.getLong("id_vaga"),
                    rs.getString("turno"),
                    rs.getString("cargo"),
                    rs.getDouble("salario_hora"),
                    departamento
                );

                return vaga;
            }

        } catch(Exception e) {
            throw new RuntimeException("Erro ao achar vaga pelo ID!", e);
        }
        return null;
    }

    public void deletar(Long id) throws RuntimeException {
        String sql = """
            DELETE FROM vagas
            WHERE id_vaga = ?;
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id);

            p.executeUpdate();

        } catch(Exception e) {
            throw new RuntimeException("Erro ao deletar vaga!", e);
        }
    }
}
