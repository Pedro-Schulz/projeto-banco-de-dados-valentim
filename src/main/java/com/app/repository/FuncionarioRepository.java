package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Funcionario;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class FuncionarioRepository {
    private VagaRepository vagaRepository = new VagaRepository();

    // 1. Salvar novo funcionário (com senha e perfil)
    public void salvar(Funcionario funcionario) throws RuntimeException {
        String sql = """
            INSERT INTO funcionarios (nome, data_nascimento, cpf, cep, email, telefone, estado_civil, genero, id_vaga, ativo, perfil, senha)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            p.setString(1, funcionario.getNome());
            p.setDate(2, funcionario.getDataNascimento() != null ? Date.valueOf(funcionario.getDataNascimento()) : null);
            p.setString(3, funcionario.getCpf());
            p.setString(4, funcionario.getCep());
            p.setString(5, funcionario.getEmail());
            p.setString(6, funcionario.getTelefone());
            p.setString(7, funcionario.getEstadoCivil());
            p.setString(8, funcionario.getGenero());
            p.setObject(9, funcionario.getVaga() != null ? funcionario.getVaga().getIdVaga() : null);
            p.setBoolean(10, funcionario.getAtivo());
            p.setString(11, funcionario.getPerfil());
            p.setString(12, funcionario.getSenha());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                funcionario.setIdFuncionario(rs.getLong(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar funcionário!", e);
        }
    }

    // 2. Buscar funcionário por CPF (carrega todas as colunas necessárias para o Login)
    public Funcionario buscarPorCpf(String cpf) throws RuntimeException {
        String sql = "SELECT * FROM funcionarios WHERE cpf = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, cpf);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                return montarObjetoFuncionario(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar funcionário por CPF!", e);
        }
        return null;
    }

    // 3. Buscar funcionário por ID
    public Funcionario buscarPorId(Long id) throws RuntimeException {
        String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                return montarObjetoFuncionario(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar funcionário por ID!", e);
        }
        return null;
    }

    // 4. Listar todos os funcionários
    public ArrayList<Funcionario> listarTodos() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
                ResultSet rs = p.executeQuery()
        ) {
            while (rs.next()) {
                funcionarios.add(montarObjetoFuncionario(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar funcionários!", e);
        }

        return funcionarios;
    }

    // 5. Atualizar funcionário
    public void atualizar(Funcionario funcionario) throws RuntimeException {
        String sql = """
            UPDATE funcionarios
            SET nome = ?, data_nascimento = ?, cpf = ?, cep = ?, email = ?, telefone = ?, estado_civil = ?, genero = ?, perfil = ?, senha = ?
            WHERE id_funcionario = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, funcionario.getNome());
            p.setDate(2, funcionario.getDataNascimento() != null ? Date.valueOf(funcionario.getDataNascimento()) : null);
            p.setString(3, funcionario.getCpf());
            p.setString(4, funcionario.getCep());
            p.setString(5, funcionario.getEmail());
            p.setString(6, funcionario.getTelefone());
            p.setString(7, funcionario.getEstadoCivil());
            p.setString(8, funcionario.getGenero());
            p.setString(9, funcionario.getPerfil());
            p.setString(10, funcionario.getSenha());
            p.setLong(11, funcionario.getIdFuncionario());

            if (p.executeUpdate() == 0) {
                throw new RuntimeException("Funcionário não encontrado para atualização!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar funcionário!", e);
        }
    }

    // 6. Desativar funcionário por ID
    public void desativar(Long id) throws RuntimeException {
        String sql = "UPDATE funcionarios SET ativo = false WHERE id_funcionario = ? AND ativo = true;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar funcionário!", e);
        }
    }

    // 7. Desativar todos os funcionários vinculados a uma Vaga por ID
    public void desativarPorVaga(Long idVaga) throws RuntimeException {
        String sql = "UPDATE funcionarios SET ativo = false WHERE id_vaga = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idVaga);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar funcionários por vaga!", e);
        }
    }

    // 8. Verificar se existe vínculo de funcionário com uma Vaga
    public boolean vinculoVaga(Long idVaga) throws RuntimeException {
        String sql = "SELECT 1 FROM funcionarios WHERE id_vaga = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idVaga);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo funcionário -> vaga", e);
        }
    }

    // 9. Buscar funcionário por Email
    public Funcionario buscarPorEmail(String email) {
        String sql = "SELECT * FROM funcionarios WHERE email = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, email);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                return montarObjetoFuncionario(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 10. Ativar funcionário por ID
    public void ativar(Long id) throws RuntimeException {
        String sql = "UPDATE funcionarios SET ativo = true WHERE id_funcionario = ? AND ativo = false;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao ativar funcionário!", e);
        }
    }

    // Busca a lista de funcionários ativos vinculados a uma Vaga
    public ArrayList<Funcionario> buscarPorVaga(Long idVaga) throws RuntimeException {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios WHERE id_vaga = ? AND ativo = true;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idVaga);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                funcionarios.add(montarObjetoFuncionario(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar funcionários por vaga!", e);
        }

        return funcionarios;
    }

    private Funcionario montarObjetoFuncionario(ResultSet rs) throws Exception {
        LocalDate dataNascimento = rs.getDate("data_nascimento") != null ? rs.getDate("data_nascimento").toLocalDate() : null;

        Long idVaga = rs.getLong("id_vaga");
        Vaga vaga = (idVaga != 0 && !rs.wasNull()) ? vagaRepository.buscarPorId(idVaga) : null;

        return new Funcionario(
                rs.getLong("id_funcionario"),
                rs.getString("nome"),
                dataNascimento,
                rs.getString("cpf"),
                rs.getString("cep"),
                rs.getString("email"),
                rs.getString("telefone"),
                rs.getString("estado_civil"),
                rs.getString("genero"),
                vaga,
                rs.getBoolean("ativo"),
                rs.getString("perfil"),
                rs.getString("senha")
        );
    }
}