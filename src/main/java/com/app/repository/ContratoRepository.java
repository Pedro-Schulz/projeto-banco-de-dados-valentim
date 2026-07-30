package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Contrato;
import com.app.model.Funcionario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ContratoRepository {

    public void salvar(Contrato contrato) throws RuntimeException {
        String sql = """
            INSERT INTO contratos (status_contrato, data_contrato, prazo_contrato, id_funcionario, ativo, version)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            p.setBoolean(1, contrato.isStatusContrato());
            p.setDate(2, Date.valueOf(contrato.getDataContrato()));
            p.setDate(3, Date.valueOf(contrato.getPrazoContrato()));
            p.setLong(4, contrato.getFuncionario().getIdFuncionario());
            p.setBoolean(5, contrato.isContratoAtivo());
            p.setInt(6, 1);

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if (rs.next()) {
                contrato.setIdContrato(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar contrato!", e);
        }
    }

    public Contrato buscarPorId(Long id) {
        String sql = "SELECT * FROM contratos WHERE id_contrato = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id_funcionario"));

                return new Contrato(
                        rs.getLong("id_contrato"),
                        rs.getBoolean("status_contrato"),
                        rs.getDate("data_contrato").toLocalDate(),
                        rs.getDate("prazo_contrato").toLocalDate(),
                        funcionario,
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar contrato por ID!", e);
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
                ResultSet rs = p.executeQuery()
        ) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id_funcionario"));

                Contrato contrato = new Contrato(
                        rs.getLong("id_contrato"),
                        rs.getBoolean("status_contrato"),
                        rs.getDate("data_contrato").toLocalDate(),
                        rs.getDate("prazo_contrato").toLocalDate(),
                        funcionario,
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );
                contratos.add(contrato);
            }
            return contratos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar os contratos!", e);
        }
    }

    public void atualizar(Contrato contrato) throws RuntimeException {
        String sql = """
            UPDATE contratos
            SET status_contrato = ?, data_contrato = ?, prazo_contrato = ?, version = version + 1
            WHERE id_contrato = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setBoolean(1, contrato.isStatusContrato());
            p.setDate(2, Date.valueOf(contrato.getDataContrato()));
            p.setDate(3, Date.valueOf(contrato.getPrazoContrato()));
            p.setLong(4, contrato.getIdContrato());
            p.setInt(5, contrato.getVersion());

            if (p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            contrato.setVersion(contrato.getVersion() + 1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar contrato!", e);
        }
    }

    public void desativar(Long id) throws RuntimeException {
        String sql = "UPDATE contratos SET ativo = false WHERE id_contrato = ? AND ativo = true;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar contrato!", e);
        }
    }

    public void desativarPorFuncionario(Long idFuncionario) throws RuntimeException {
        String sql = "UPDATE contratos SET ativo = false WHERE id_funcionario = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idFuncionario);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar contrato por funcionário!", e);
        }
    }

    public boolean vinculoFuncionario(Long idFuncionario) throws RuntimeException {
        String sql = "SELECT 1 FROM contratos WHERE id_funcionario = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idFuncionario);
            ResultSet rs = p.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo contrato -> funcionário", e);
        }
    }
}