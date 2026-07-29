package com.app.repository;

import com.app.config.ConnectionFactory;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.app.model.Funcionario;
import com.app.model.Usuario;

import java.sql.*;

public class UsuarioRepository {
    package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Funcionario;
import com.app.model.Usuario;

import java.sql.*;

public class UsuarioRepository {
    public void salvar(Usuario usuario) throws RuntimeException {
        String sql = """
            INSERT INTO usuarios (cpf, ativo, id_funcionario, perfil, senha)
            VALUES (?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            p.setString(1, usuario.getCpf());
            p.setBoolean(2, usuario.getAtivo());
            p.setLong(3, usuario.getIdFuncionario());
            p.setString(4, "ADMIN");
            p.setString(5, usuario.getSenhaHash());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar usuário!");
        }
    }

    public Usuario buscarPorCpf(String cpf) throws RuntimeException {
        String sql = """
            SELECT *
            FROM usuarios
            WHERE cpf = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setString(1, cpf);
            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getString("perfil"),
                        rs.getBoolean("ativo"),
                        rs.getLong("id_funcionario"),
                        rs.getInt("version")
                );
                return usuario;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar funcionário!");
        }
        return null;
    }

    public void atualizar(Usuario usuario) throws RuntimeException {
        String sql = """
            UPDATE usuarios
            SET senha = ?, perfil = ?, ativo = ?, version = version + 1
            WHERE id_usuario = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setString(1, usuario.getSenhaHash());
            p.setString(2, usuario.getPerfil());
            p.setBoolean(3, usuario.getAtivo());
            p.setString(4, usuario.getCpf());
            p.setInt(5, usuario.getVersion());

            if(p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            usuario.setVersion(usuario.getVersion() + 1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar usuario!");
        }
    }
}


    // 1. Busca o ID do funcionario pelo CPF
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

    // 2. Verifica se o usuário já está cadastrado
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

    // 3. Cadastra o novo usuário com BCrypt
    public boolean cadastrarUsuario(String cpf, String senha, String perfil) {
        //  DECLARAÇÃO DAS VARIÁVEIS (Corrigindo erro de compilação)
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

        // Criptografa a senha em texto puro antes de enviar para o MySQL
        String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

        String sql = "INSERT INTO usuarios (cpf, senha, perfil, ativo, id_funcionario, version) VALUES (?, ?, ?, ?, ?, ?);";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, cpfLimpo);
            p.setString(2, senhaCriptografada); // Salva o hash no banco
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

    // 4. Valida credenciais usando BCrypt.checkpw
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

    // 5. Verifica se o CPF pertence a algum funcionário cadastrado na empresa
    public boolean existeFuncionarioPorCpf(String cpf) {
        return buscarIdFuncionarioPorCpf(cpf) != null;
    }
}
    public void atualizar(Usuario usuario) throws RuntimeException {
        String sql = """
            UPDATE usuarios
            SET senha = ?, perfil = ?, ativo = ?, version = version + 1
            WHERE id_usuario = ? AND version = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
        ) {
            p.setString(1, usuario.getSenhaHash());
            p.setString(2, usuario.getPerfil());
            p.setBoolean(3, usuario.getAtivo());
            p.setString(4, usuario.getCpf());
            p.setInt(5, usuario.getVersion());

            if(p.executeUpdate() == 0) {
                throw new RuntimeException("Este dado foi alterado por outra pessoa. Atualize a página!");
            }

            usuario.setVersion(usuario.getVersion() + 1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar usuario!");
        }
    }
}
