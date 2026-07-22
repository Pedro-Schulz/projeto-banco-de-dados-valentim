package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
