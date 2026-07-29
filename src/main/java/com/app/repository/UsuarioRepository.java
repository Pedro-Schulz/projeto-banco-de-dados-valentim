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
