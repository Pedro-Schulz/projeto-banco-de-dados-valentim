package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Funcionario;
import com.app.model.Vaga;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FuncionarioRepository {
    private VagaRepository vagaRepository = new VagaRepository();

    public void salvar(Funcionario funcionario) throws RuntimeException {
        String sql = """
            INSERT INTO funcionarios (nome, data_nascimento, cpf, cep, email, telefone, estado_civil, genero, id_vaga, ativo)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setString(1, funcionario.getNome());
            p.setObject(2, funcionario.getDataNascimento());
            p.setString(3, funcionario.getCpf());
            p.setString(4, funcionario.getCep());
            p.setString(5, funcionario.getEmail());
            p.setString(6, funcionario.getTelefone());
            p.setString(7, funcionario.getEstadoCivil());
            p.setString(8, funcionario.getGenero());
            p.setObject(9, funcionario.getVaga().getIdVaga());
            p.setBoolean(10, funcionario.getAtivo());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                Long id = rs.getLong(1);
                funcionario.setIdFuncionario(id);
            }

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar funcionário!");
        }
    }

    public Funcionario buscarPorCpf(String cpf) throws RuntimeException {
        String sql = """
            SELECT f.id_funcionario, f.ativo, v.cargo
            FROM funcionarios AS f
            JOIN vagas AS v ON v.id_vaga = f.id_vaga
            WHERE cpf = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setString(1, cpf);
            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                String cargo = rs.getString("cargo");
                Vaga vaga = new Vaga(cargo);

                Funcionario funcionario = new Funcionario(
                        rs.getLong("id_funcionario"),
                        rs.getBoolean("ativo"),
                        vaga
                );
                return funcionario;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar funcionário!");
        }
        return null;
    }

    public Funcionario buscarPorId(Long id) throws RuntimeException {
        String sql = """
            SELECT * 
            FROM funcionarios
            WHERE id_funcionario = ?;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();

                Long idVaga = rs.getLong("id_vaga");
                Vaga vaga = vagaRepository.buscarPorId(idVaga);

                Funcionario funcionario = new Funcionario(
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
                    rs.getInt("version")
                );

                return funcionario;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar funcionário!");
        }
        return null;
    }

    public ArrayList<Funcionario> listarTodos() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = """
            SELECT f.*, v.id_vaga
            FROM funcionarios AS f
            JOIN vagas AS v ON v.id_vaga = f.id_vaga;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            ResultSet rs = p.executeQuery();

            while(rs.next()) {
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
                    new Vaga(rs.getLong("id_vaga")),
                    rs.getBoolean("ativo"),
                    rs.getInt("version")
                );

                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar funcionários!");
        }

        return funcionarios;
    }

    public void desativar(Long id) throws RuntimeException {
        String sql = """
            UPDATE funcionarios
            SET ativo = false
            WHERE id_funcionario = ? AND ativo = true;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, id);

            p.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar funcionário!");
        }
    }

    public void desativarPorVaga(Long idVaga) throws RuntimeException {
        String sql = """
            UPDATE funcionarios
            SET ativo = false
            WHERE id_vaga = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idVaga);

            p.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar funcionário!");
        }
    }

    public boolean vinculoVaga(Long idVaga) throws RuntimeException {
        String sql = """
            SELECT 1
            FROM funcionarios
            WHERE id_vaga = ?
            LIMIT 1;
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setLong(1, idVaga);

            ResultSet rs = p.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo funcionário -> vaga");
        }
    }
}
