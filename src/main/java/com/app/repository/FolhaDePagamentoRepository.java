package com.app.repository;

import com.app.config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FolhaDePagamentoRepository {
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
