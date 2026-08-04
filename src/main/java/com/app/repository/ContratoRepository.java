package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.exception.RepositoryException;
import com.app.model.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ContratoRepository {

    public void salvar(Contrato contrato) throws RepositoryException {
        String sql = """
                    INSERT INTO contratos (status_contrato, data_emissao, prazo, id_funcionario)
                    VALUES (?, ?, ?, ?);
                """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setBoolean(1, contrato.getStatusContrato());
            p.setDate(2, java.sql.Date.valueOf(contrato.getDataEmissao()));
            p.setInt(3, contrato.getPrazo());
            p.setLong(4, contrato.getFuncionario().getIdFuncionario());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if (rs.next()) {
                Long id = rs.getLong(1);
                contrato.setIdContrato(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao salvar contrato!");
        }
    }

    public Contrato buscarPorId(Long id) {
        String sql = """
            SELECT *
            FROM contratos
            WHERE id_contrato = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                Contrato contrato = new Contrato(
                        rs.getLong("id_contrato"),
                        rs.getBoolean("status_contrato"),
                        rs.getDate("data_emissao").toLocalDate(),
                        rs.getInt("prazo"),
                        new Funcionario(rs.getLong("id_funcionario")),
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );

                return contrato;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao buscar contrato!");
        }
        return null;
    }

    public ArrayList<Contrato> listarTodos() {
        ArrayList<Contrato> contratos = new ArrayList<>();
        String sql = """
            SELECT c.*, f.id_funcionario
            FROM contratos AS c
            JOIN funcionarios AS f ON f.id_funcionario = c.id_funcionario;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();

            while(rs.next()) {
                Contrato contrato = new Contrato(
                        rs.getLong("id_contrato"),
                        rs.getBoolean("status_contrato"),
                        rs.getDate("data_emissao").toLocalDate(),
                        rs.getInt("prazo"),
                        new Funcionario(rs.getLong("id_funcionario")),
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );
                contratos.add(contrato);
            }
            return contratos;
        } catch(Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao listar os contratos! \n");
        }
    }

    public void atualizar(Contrato contrato) throws RepositoryException {
        String sql = """
            UPDATE contratos
            SET status_contrato = ?,data_emissao = ?, prazo = ?,  version = version + 1
            WHERE id_contrato = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setBoolean(1, contrato.getStatusContrato());
            p.setDate(2, java.sql.Date.valueOf(contrato.getDataEmissao()));
            p.setInt(3, contrato.getPrazo());
            p.setLong(4, contrato.getIdContrato());
            p.setInt(5, contrato.getVersion());

            if(p.executeUpdate() == 0) {
                throw new RepositoryException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            contrato.setVersion(contrato.getVersion() + 1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao atualizar contrato!");
        }
    }

    public void desativar(Long id) throws RepositoryException {
        String sql = """
            UPDATE contratos
            SET ativo = FALSE
            WHERE id_contrato = ? AND ativo = true;
        """;
        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            p.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao desativar contrato!");
        }
    }

    public void desativarPorFuncionario(Long idFuncionario) throws RepositoryException {
        String sql = """
            UPDATE contratos
            SET ativo = false
            WHERE id_funcionario = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idFuncionario);
            p.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao desativar contrato!");
        }
    }

    public boolean vinculoFuncionario(Long id_funcionario) throws RepositoryException {
        String sql = """
                    SELECT 1
                    FROM contratos
                    WHERE id_funcionario = ? AND ativo = 1
                    LIMIT 1;        
                """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id_funcionario);

            ResultSet rs = p.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Erro ao verificar vinculo contrato -> funcionário");
        }
    }
}