package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.DadosBancarios;
import com.app.model.FolhaDePagamento;
import com.app.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DadosBancariosRepository {

    public void salvar(DadosBancarios dadosBancarios) throws RuntimeException {
        String sql = """
            INSERT INTO dados_bancarios (numero_conta, agencia_bancaria, instituicao_bancaria, id_funcionario, ativo)
            VALUES (?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setInt(1, dadosBancarios.getNumeroConta());
            p.setString(2, dadosBancarios.getAgenciaBancaria());
            p.setString(3, dadosBancarios.getInstituicaoBancaria());
            p.setLong(4, dadosBancarios.getFuncionario().getIdFuncionario());
            p.setBoolean(5, dadosBancarios.getContaAtiva());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                Long id = rs.getLong(1);
                dadosBancarios.setIdDadosBancarios(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar um conjunto de dados bancários!");
        }
    }

    public DadosBancarios buscarPorId(Long id) throws RuntimeException {
        String sql = """
            SELECT *
            FROM dados_bancarios
            WHERE id_dados_bancarios = ?;        
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                FuncionarioRepository fr = new FuncionarioRepository();

                Long idFuncionario = rs.getLong("id_funcionario");
                Funcionario funcionario = fr.buscarPorId(idFuncionario);

                DadosBancarios dadosBancarios = new DadosBancarios(
                        rs.getLong("id_dados_bancarios"),
                        rs.getInt("numero_conta"),
                        rs.getString("instituicao_bancaria"),
                        rs.getString("agencia_bancaria"),
                        funcionario,
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );

                return dadosBancarios;
            }
        } catch(Exception e) {
            throw new RuntimeException("Erro ao buscar um conjunto de dados bancários!", e);
        }
        return null;
    }

    public ArrayList<DadosBancarios> listarTodos() {
        ArrayList<DadosBancarios> dadosBancariosList = new ArrayList<>();
        String sql = """
            SELECT d.*, f.id_funcionario
            FROM dados_bancarios AS d
            JOIN funcionarios AS f ON f.id_funcionario = d.id_funcionario;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();

            while(rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id_funcionario"));

                DadosBancarios dadosBancarios = new DadosBancarios(
                        rs.getLong("id_dados_bancarios"),
                        rs.getInt("numero_conta"),
                        rs.getString("instituicao_bancaria"),
                        rs.getString("agencia_bancaria"),
                        funcionario,
                        rs.getBoolean("ativo"),
                        rs.getInt("version")
                );
                dadosBancariosList.add(dadosBancarios);
            }
            return dadosBancariosList;
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar os dados bancários!");
        }
    }

    public void atualizar(DadosBancarios dadosBancarios) throws RuntimeException {
        String sql = """
            UPDATE dados_bancarios
            SET numero_conta = ?,instituicao_bancaria = ?, agencia_bancaria = ?,version = version + 1
            WHERE id_dados_bancarios = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setInt(1, dadosBancarios.getNumeroConta());
            p.setString(2, dadosBancarios.getInstituicaoBancaria());
            p.setString(3, dadosBancarios.getAgenciaBancaria());
            p.setLong(4, dadosBancarios.getIdDadosBancarios());
            p.setInt(5, dadosBancarios.getVersion());

            if(p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            dadosBancarios.setVersion(dadosBancarios.getVersion() + 1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar dados bancarios!");
        }
    }

    public void desativar(Long id) throws RuntimeException {
        String sql = """
            UPDATE dados_bancarios
            SET ativo = false
            WHERE id_dados_bancarios = ? AND ativo = true;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            p.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar dados bancários!");
        }
    }

    public void desativarPorFuncionario(Long idFuncionario) throws RuntimeException {
        String sql = """
            UPDATE dados_bancarios
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
            throw new RuntimeException("Erro ao deletar dados bancários!");
        }
    }

    public boolean vinculoFuncionario(Long idFuncionario) throws RuntimeException {
        String sql = """
            SELECT 1
            FROM dados_bancarios
            WHERE id_funcionario = ? AND ativo = 1
            LIMIT 1;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idFuncionario);

            ResultSet rs = p.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vinculo dados bancários -> funcionário");
        }
    }
}