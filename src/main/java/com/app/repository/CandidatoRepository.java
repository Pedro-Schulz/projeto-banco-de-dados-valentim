package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.exception.RepositoryException;
import com.app.model.Candidato;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CandidatoRepository {

    public void salvar(Candidato candidato) {
        String sql = """
            INSERT INTO candidatos (nome, cpf, cep, email, telefone, genero, estado_civil, data_nascimento)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setString(1, candidato.getNome());
            p.setString(2, candidato.getCpf());
            p.setString(3, candidato.getCep());
            p.setString(4, candidato.getEmail());
            p.setString(5, candidato.getTelefone());
            p.setString(6, candidato.getGenero());
            p.setString(7, candidato.getEstadoCivil());
            p.setDate(8, Date.valueOf(candidato.getDataNascimento()));

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                Long id = rs.getLong(1);
                candidato.setIdCandidato(id);
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao salvar candidato!");
        }
    }

    public Candidato buscarPorId(Long id) {
        String sql = """
            SELECT *
            FROM candidatos
            WHERE id_candidato = ?;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
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
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );
                return candidato;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao buscar candidato!");
        }
        return null;
    }

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
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );
                candidatos.add(candidato);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao listar candidatos!");
        }
        return candidatos;
    }

    public void atualizar(Candidato candidato) throws RuntimeException {
        String sql = """
            UPDATE candidatos
            SET nome = ?, cpf = ?, cep = ?, email = ?, telefone = ?, genero = ?, estado_civil = ?, data_nascimento = ?, version = version + 1
            WHERE id_candidato = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setString(1, candidato.getNome());
            p.setString(2, candidato.getCpf());
            p.setString(3, candidato.getCep());
            p.setString(4, candidato.getEmail());
            p.setString(5, candidato.getTelefone());
            p.setString(6, candidato.getGenero());
            p.setString(7, candidato.getEstadoCivil());
            p.setDate(8, Date.valueOf(candidato.getDataNascimento()));
            p.setLong(9, candidato.getIdCandidato());
            p.setInt(10, candidato.getVersion());

            if(p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            candidato.setVersion(candidato.getVersion() + 1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao atualizar candidato!");
        }
    }

    public void desativar(Long id) {
        String sql = """
            UPDATE candidatos
            SET ativo = FALSE
            WHERE id_candidato = ? AND ativo = true;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            p.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao desativar candidato!");
        }
    }
}
