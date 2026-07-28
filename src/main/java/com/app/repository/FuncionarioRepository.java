package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Funcionario;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncionarioRepository {

    public void salvar(Funcionario funcionario) {
        String sql = """
            INSERT INTO funcionarios (nome, data_nascimento, cpf, cep, email, telefone, estado_civil, genero, id_vaga, ativo)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setString(1, funcionario.getNome());
            p.setDate(2, Date.valueOf(funcionario.getDataNascimento()));
            p.setString(3, funcionario.getCpf());
            p.setString(4, funcionario.getCep());
            p.setString(5, funcionario.getEmail());
            p.setString(6, funcionario.getTelefone());
            p.setString(7, funcionario.getEstadoCivil());
            p.setString(8, funcionario.getGenero());
            p.setLong(9, funcionario.getVaga().getIdVaga());
            p.setBoolean(10, funcionario.getAtivo());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                funcionario.setIdFuncionario(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar funcionário!");
        }
    }

    public ArrayList<Funcionario> listarTodos() {
        ArrayList<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getLong("id_vaga"));

                Funcionario funcionario = new Funcionario(
                        rs.getLong("id_funcionario"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("cpf"),
                        rs.getString("cep"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("estado_civil"),
                        rs.getString("genero"),
                        vaga,
                        rs.getBoolean("ativo"),
                        0
                );
                lista.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar funcionários!");
        }
        return lista;
    }

    public Funcionario buscarPorId(Long id) {
        String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getLong("id_vaga"));

                return new Funcionario(
                        rs.getLong("id_funcionario"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("cpf"),
                        rs.getString("cep"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("estado_civil"),
                        rs.getString("genero"),
                        vaga,
                        rs.getBoolean("ativo"),
                        0
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar funcionário por ID!");
        }
        return null;
    }

    public Funcionario buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM funcionarios WHERE cpf = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setString(1, cpf);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getLong("id_vaga"));

                return new Funcionario(
                        rs.getLong("id_funcionario"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("cpf"),
                        rs.getString("cep"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("estado_civil"),
                        rs.getString("genero"),
                        vaga,
                        rs.getBoolean("ativo"),
                        0
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar funcionário por CPF!");
        }
        return null;
    }

    public void desativar(Long id) {
        String sql = "UPDATE funcionarios SET ativo = false WHERE id_funcionario = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar funcionário!");
        }
    }

    public void desativarPorVaga(Long idVaga) {
        String sql = "UPDATE funcionarios SET ativo = false WHERE id_vaga = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idVaga);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar funcionários da vaga!");
        }
    }

    public boolean vinculoVaga(Long idVaga) {
        String sql = "SELECT 1 FROM funcionarios WHERE id_vaga = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idVaga);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo com a vaga!");
        }
    }

    public Funcionario buscarPorEmail(String email) {
        String sql = "SELECT * FROM funcionarios WHERE email = ?;";
        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setString(1, email);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getLong("id_funcionario"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setAtivo(rs.getBoolean("ativo"));
                return f;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}