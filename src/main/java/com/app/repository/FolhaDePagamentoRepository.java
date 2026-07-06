package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.FolhaDePagamento;
import com.app.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FolhaDePagamentoRepository {

    public void salvar(FolhaDePagamento folha) throws RuntimeException {
        String sql = """
            INSERT INTO folhas_de_pagamento (horas_trabalhadas, data_emissao, descontos, horas_extras, id_funcionario, ativo)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setInt(1, folha.getHorasTrabalhadas());
            p.setObject(2, folha.getDataEmissao());
            p.setDouble(3, folha.getDescontos());
            p.setInt(4, folha.getHorasExtras());
            p.setLong(5, folha.getFuncionario().getIdFuncionario());
            p.setBoolean(6, folha.getAtivo());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if (rs.next()) {
                Long id = rs.getLong(1);
                folha.setIdFolha(id);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar folha de pagamento!", e);
        }
    }

    public FolhaDePagamento buscarPorId(Long id) throws RuntimeException {
        String sql = """
            SELECT id_folha, horas_trabalhadas, data_emissao, descontos, horas_extras, id_funcionario, ativo
            FROM folhas_de_pagamento
            WHERE id_folha = ? AND ativo = true;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar folha de pagamento!", e);
        }
    }

    public List<FolhaDePagamento> listar() throws RuntimeException {
        String sql = """
            SELECT id_folha, horas_trabalhadas, data_emissao, descontos, horas_extras, id_funcionario, ativo
            FROM folhas_de_pagamento
            WHERE ativo = true;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();

            List<FolhaDePagamento> folhas = new ArrayList<>();

            while (rs.next()) {
                folhas.add(mapear(rs));
            }

            return folhas;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar folhas de pagamento!", e);
        }
    }

    public void atualizar(FolhaDePagamento folha) throws RuntimeException {
        String sql = """
            UPDATE folhas_de_pagamento
            SET horas_trabalhadas = ?, data_emissao = ?, descontos = ?, horas_extras = ?, id_funcionario = ?
            WHERE id_folha = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setInt(1, folha.getHorasTrabalhadas());
            p.setObject(2, folha.getDataEmissao());
            p.setDouble(3, folha.getDescontos());
            p.setInt(4, folha.getHorasExtras());
            p.setLong(5, folha.getFuncionario().getIdFuncionario());
            p.setLong(6, folha.getIdFolha());

            p.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar folha de pagamento!", e);
        }
    }

    public void desativar(Long id_funcionario) throws RuntimeException {
        String sql = """
            UPDATE folhas_de_pagamento
            SET ativo = false
            WHERE id_funcionario = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id_funcionario);
            p.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao desativar folha de pagamento!", e);
        }
    }

    public boolean vinculoFuncionario(Long id) throws RuntimeException {
        String sql = """
            SELECT 1
            FROM folhas_de_pagamento
            WHERE id_folha = ? AND ativo = 1
            LIMIT 1;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            return rs.next();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar vínculo folha de pagamento -> funcionário", e);
        }
    }

    private FolhaDePagamento mapear(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(rs.getLong("id_funcionario"));

        return new FolhaDePagamento(
                rs.getLong("id_folha"),
                rs.getInt("horas_trabalhadas"),
                rs.getObject("data_emissao", java.time.LocalDate.class),
                rs.getDouble("descontos"),
                rs.getInt("horas_extras"),
                funcionario,
                rs.getBoolean("ativo")
        );
    }
}