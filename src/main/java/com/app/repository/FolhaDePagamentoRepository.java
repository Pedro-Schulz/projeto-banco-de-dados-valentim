package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.FolhaDePagamento;
import com.app.model.Funcionario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FolhaDePagamentoRepository {

    public void salvar(FolhaDePagamento folha) {
        String sql = """
            INSERT INTO folha_de_pagamento (horas_trabalhadas, data_emissao, descontos, horas_extras, id_funcionario, ativo)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setInt(1, folha.getHorasTrabalhadas());
            p.setDate(2, Date.valueOf(folha.getDataEmissao()));
            p.setDouble(3, folha.getDescontos());
            p.setInt(4, folha.getHorasExtras());
            p.setLong(5, folha.getFuncionario().getIdFuncionario());
            p.setBoolean(6, folha.isAtivo());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                folha.setIdFolhaDePagamento(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar folha de pagamento!");
        }
    }

    public ArrayList<FolhaDePagamento> listarTodos() {
        ArrayList<FolhaDePagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM folha_de_pagamento;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id_funcionario"));

                FolhaDePagamento folha = new FolhaDePagamento(
                        rs.getLong("id_folha_de_pagamento"),
                        rs.getInt("horas_trabalhadas"),
                        rs.getDate("data_emissao").toLocalDate(),
                        rs.getDouble("descontos"),
                        rs.getInt("horas_extras"),
                        funcionario,
                        rs.getBoolean("ativo")
                );
                lista.add(folha);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar folhas de pagamento!");
        }
        return lista;
    }

    public FolhaDePagamento buscarPorId(Long id) {
        String sql = "SELECT * FROM folha_de_pagamento WHERE id_folha_de_pagamento = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id_funcionario"));

                return new FolhaDePagamento(
                        rs.getLong("id_folha_de_pagamento"),
                        rs.getInt("horas_trabalhadas"),
                        rs.getDate("data_emissao").toLocalDate(),
                        rs.getDouble("descontos"),
                        rs.getInt("horas_extras"),
                        funcionario,
                        rs.getBoolean("ativo")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar folha de pagamento por ID!");
        }
        return null;
    }

    public void desativar(Long id) {
        String sql = "UPDATE folha_de_pagamento SET ativo = false WHERE id_folha_de_pagamento = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar folha de pagamento!");
        }
    }

    public void desativarPorFuncionario(Long idFuncionario) {
        String sql = "UPDATE folha_de_pagamento SET ativo = false WHERE id_funcionario = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idFuncionario);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar folha de pagamento do funcionário!");
        }
    }

    public boolean vinculoFuncionario(Long idFuncionario) {
        String sql = "SELECT 1 FROM folha_de_pagamento WHERE id_funcionario = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idFuncionario);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo com funcionário!");
        }
    }
}