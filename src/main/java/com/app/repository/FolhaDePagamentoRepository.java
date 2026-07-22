package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.FolhaDePagamento;
import com.app.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

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
            SELECT *
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
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
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

    public void atualizar(FolhaDePagamento folha) throws RuntimeException {
        String sql = """
            UPDATE folhas_de_pagamento
            SET horas_trabalhadas = ?, data_emissao = ?, descontos = ?, horas_extras = ?, id_funcionario = ?, version = version + 1
            WHERE id_folha = ? AND version = ?;
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
            p.setInt(7, folha.getVersion());

            if(p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            folha.setVersion(folha.getVersion() + 1);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar folha de pagamento!", e);
        }
    }

    public void desativar(FolhaDePagamento folhaDePagamento) throws RuntimeException {
        String sql = """
            UPDATE folhas_de_pagamento
            SET ativo = false
            WHERE id_folha = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, folhaDePagamento.getIdFolha());
            p.setInt(2, folhaDePagamento.getVersion());

            if(p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            folhaDePagamento.setVersion(folhaDePagamento.getVersion() + 1);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao desativar folha de pagamento!", e);
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

        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar vínculo folha de pagamento -> funcionário", e);
        }
    }

    private FolhaDePagamento mapear(ResultSet rs) throws SQLException {
        return new FolhaDePagamento(
                rs.getLong("id_folha"),
                rs.getInt("horas_trabalhadas"),
                rs.getObject("data_emissao", java.time.LocalDate.class),
                rs.getDouble("descontos"),
                rs.getInt("horas_extras"),
                new Funcionario(rs.getLong("id_funcionario")),
                rs.getBoolean("ativo"),
                rs.getInt("version")
        );
    }
}