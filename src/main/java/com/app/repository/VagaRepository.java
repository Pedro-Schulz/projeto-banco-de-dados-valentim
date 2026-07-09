package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Departamento;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VagaRepository {

    public void salvar(Vaga vaga) throws RuntimeException {
        String sql = """
            INSERT INTO vagas (turno, cargo, salario_hora, id_departamento, ativo)
            VALUES (?, ?, ?, ?);        
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setString(1, vaga.getTurno());
            p.setString(2, vaga.getCargo());
            p.setDouble(3, vaga.getSalarioHora());
            p.setLong(4, vaga.getDepartamento().getIdDepartamento());
            p.setBoolean(5, vaga.getAtivo());

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

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
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
                    departamento,
                    rs.getBoolean("ativo")
                );

                return vaga;
            }

        } catch(Exception e) {
            throw new RuntimeException("Erro ao achar vaga pelo ID!", e);
        }
        return null;
    }

    public ArrayList<Vaga> listarTodos() throws RuntimeException {
        ArrayList<Vaga> vagas = new ArrayList<>();
        String sql = """
            SELECT v.*, d.id_departamento
            FROM vagas AS v
            JOIN departamentos AS d ON d.id_departamento = v.id_departamento;
        """;

        try(
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ){
            ResultSet rs = p.executeQuery();

            while(rs.next()) {
                Vaga vaga = new Vaga(
                    rs.getLong("id_vaga"),
                    rs.getString("turno"),
                    rs.getString("cargo"),
                    rs.getDouble("salario_hora"),
                    new Departamento(rs.getLong("id_departamento")),
                    rs.getBoolean("ativo")
                );

                vagas.add(vaga);
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao listar as vagas!", e);
        }

        return vagas;
    }

    public void desativar(Long id) throws RuntimeException {
        String sql = """
            SELECT vagas
            SET ativo = false
            WHERE id_vaga = ?;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            p.executeUpdate();

        } catch(Exception e) {
            throw new RuntimeException("Erro ao deletar vaga!", e);
        }
    }
}
