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

    public void salvar(Contrato contrato) {
        String sql = """
            INSERT INTO contratos (status_contrato, data_contrato, prazo_contrato, id_funcionario, contrato_ativo)
            VALUES (?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setBoolean(1, contrato.isStatusContrato());
            p.setDate(2, Date.valueOf(contrato.getDataContrato()));
            p.setDate(3, Date.valueOf(contrato.getPrazoContrato()));
            p.setLong(4, contrato.getFuncionario().getIdFuncionario());
            p.setBoolean(5, contrato.isContratoAtivo());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                contrato.setIdContrato(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar contrato!");
        }
    }

    public ArrayList<Contrato> listarTodos() {
        ArrayList<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contratos;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id_funcionario"));

                Contrato contrato = new Contrato(
                        rs.getLong("id_contrato"),
                        rs.getBoolean("status_contrato"),
                        rs.getDate("data_contrato").toLocalDate(),
                        rs.getDate("prazo_contrato").toLocalDate(),
                        funcionario,
                        rs.getBoolean("contrato_ativo")
                );
                lista.add(contrato);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar contratos!");
        }
        return lista;
    }

    public void desativar(Long id) {
        String sql = "UPDATE contratos SET contrato_ativo = false WHERE id_contrato = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar contrato!");
        }
    }

    public void desativarPorFuncionario(Long idFuncionario) {
        String sql = "UPDATE contratos SET contrato_ativo = false WHERE id_funcionario = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idFuncionario);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar contratos do funcionário!");
        }
    }

    public boolean vinculoFuncionario(Long idFuncionario) {
        String sql = "SELECT 1 FROM contratos WHERE id_funcionario = ? AND contrato_ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idFuncionario);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo de contrato!");
        }
    }
}