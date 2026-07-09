package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.FolhaDePagamento;
import com.app.model.Funcionario;
import com.app.service.FolhaDePagamentoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class FolhaDePagamentoRepository {

    public ArrayList<FolhaDePagamento> listarTodos() {
        ArrayList<FolhaDePagamento> folhasDePagamento = new ArrayList<>();
        String sql = """
            SELECT fp.*, func.id_funcionario
            FROM folhas_de_pagamento AS fp
            JOIN funcionarios AS func ON func.id_funcionario = fp.id_funcionario;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();

            while(rs.next()) {
                FolhaDePagamento folhaDePagamento = new FolhaDePagamento(
                    rs.getLong("id_folha"),
                    rs.getInt("horas_trabalhadas"),
                    rs.getDate("data_emissao").toLocalDate(),
                    rs.getDouble("descontos"),
                    rs.getInt("horas_extras"),
                    new Funcionario(rs.getLong("id_funcionario")),
                    rs.getBoolean("ativo")
                );

                folhasDePagamento.add(folhaDePagamento);
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao listar folhas de pagamento! \n", e);
        }

        return folhasDePagamento;
    }

    public void desativarPorFuncionario(Long idFuncionario) throws RuntimeException {
        String sql = """
            UPDATE folhas_de_pagamento
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
            throw new RuntimeException("Erro ao dessativar folha de pagamento!", e);
        }
    }

    public void desativar(Long id) throws RuntimeException {
        String sql = """
            UPDATE folhas_de_pagamento
            SET ativo = false
            WHERE id_folha = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            p.executeUpdate();

        } catch(Exception e) {
            throw new RuntimeException("Erro ao dessativar folha de pagamento!", e);
        }
    }

    public boolean vinculoFuncionario(Long id) throws RuntimeException {
        String sql = """
            SELECT 1
            FROM folhas_de_pagamento
            WHERE id_funcionario = ? AND ativo = 1
            LIMIT 1;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            return rs.next();

        } catch(Exception e) {
            throw new RuntimeException("Erro ao verificar vinculo folha de pagamento -> funcionário", e);
        }
    }
}
