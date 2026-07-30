package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioRepository {

    // 1. Salvar Usuário (Geral)
    public void salvar(Usuario usuario) throws RuntimeException {
        String sql = """
            INSERT INTO usuarios (cpf, ativo, id_funcionario, perfil, senha, version)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            p.setString(1, usuario.getCpf().replaceAll("[^0-9]", ""));
            p.setBoolean(2, usuario.getAtivo());
            p.setLong(3, usuario.getIdFuncionario());
            p.setString(4, usuario.getPerfil() != null ? usuario.getPerfil() : "USER");
            p.setString(5, usuario.getSenhaHash());
            p.setInt(6, 1);

            p.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar usuário!", e);
        }
    }

    // 2. Buscar Usuário por CPF
    public Usuario buscarPorCpf(String cpf) throws RuntimeException {
        String sql = "SELECT * FROM usuarios WHERE REPLACE(REPLACE(cpf, '.', ''), '-', '') = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, cpf.replaceAll("[^0-9]", ""));
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getString("perfil"),
                        rs.getBoolean("ativo"),
                        rs.getLong("id_funcionario"),
                        rs.getInt("version")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar usuário!", e);
        }
        return null;
    }

    // 3. Atualizar Usuário com controle de versão (Optimistic Locking)
    public void atualizar(Usuario usuario) throws RuntimeException {
        String sql = """
            UPDATE usuarios
            SET senha = ?, perfil = ?, ativo = ?, version = version + 1
            WHERE REPLACE(REPLACE(cpf, '.', ''), '-', '') = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, usuario.getSenhaHash());
            p.setString(2, usuario.getPerfil());
            p.setBoolean(3, usuario.getAtivo());
            p.setString(4, usuario.getCpf().replaceAll("[^0-9]", ""));
            p.setInt(5, usuario.getVersion());

            if (p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa ou o registro não foi encontrado. Atualize a página!");
            }

            usuario.setVersion(usuario.getVersion() + 1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar usuário!", e);
        }
    }

    // 4. Busca o ID do funcionário pelo CPF
    public Long buscarIdFuncionarioPorCpf(String cpf) {
        String sql = "SELECT id_funcionario FROM funcionarios WHERE REPLACE(REPLACE(cpf, '.', ''), '-', '') = ?;";
        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, cpf.replaceAll("[^0-9]", ""));
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return rs.getLong("id_funcionario");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 5. Verifica se o usuário já está cadastrado
    public boolean existeUsuarioPorCpf(String cpf) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE REPLACE(REPLACE(cpf, '.', ''), '-', '') = ?;";
        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, cpf.replaceAll("[^0-9]", ""));
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 6. Cadastra novo usuário com BCrypt (Usado pelo Menu Admin)
    public boolean cadastrarUsuario(String cpf, String senha, String perfil) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        if (existeUsuarioPorCpf(cpfLimpo)) {
            System.out.println("\n [AVISO]: Já existe um usuário cadastrado para este CPF!");
            return false;
        }

        Long idFuncionario = buscarIdFuncionarioPorCpf(cpfLimpo);
        if (idFuncionario == null) {
            System.out.println("\n [ERRO]: Funcionário não encontrado para o CPF fornecido.");
            return false;
        }

        // Criptografa a senha antes de salvar no MySQL
        String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

        String sql = "INSERT INTO usuarios (cpf, senha, perfil, ativo, id_funcionario, version) VALUES (?, ?, ?, ?, ?, ?);";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, cpfLimpo);
            p.setString(2, senhaCriptografada);
            p.setString(3, (perfil != null && !perfil.isBlank()) ? perfil.toUpperCase() : "USER");
            p.setBoolean(4, true);
            p.setLong(5, idFuncionario);
            p.setInt(6, 1);

            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 7. Valida credenciais e obtém o perfil (BCrypt + Fallback Texto Puro)
    public String autenticarEObterPerfil(String cpf, String senhaDigitada) {
        String sql = "SELECT senha, perfil FROM usuarios WHERE REPLACE(REPLACE(cpf, '.', ''), '-', '') = ? AND ativo = true;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, cpf.replaceAll("[^0-9]", ""));
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                String senhaBanco = rs.getString("senha");
                String perfil = rs.getString("perfil");

                try {
                    if (BCrypt.checkpw(senhaDigitada, senhaBanco)) {
                        return perfil;
                    }
                } catch (IllegalArgumentException e) {
                    // Se a senha no banco for texto puro (ex: '123456')
                    if (senhaBanco.equals(senhaDigitada)) {
                        return perfil;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 8. Verifica se o CPF pertence a algum funcionário cadastrado na empresa
    public boolean existeFuncionarioPorCpf(String cpf) {
        return buscarIdFuncionarioPorCpf(cpf) != null;
    }
}