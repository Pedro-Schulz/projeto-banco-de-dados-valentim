package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionarioRepository {

    public void salvar(Funcionario funcionario) {
        String sql = """
            INSERT INTO Funcionarios (nome, dataNascimento, cpf, cep, email, telefone, estadoCivil, genero, vaga)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
            ) {
            p.setString(1, funcionario.getNome());
            p.setObject(2, funcionario.getDataNascimento());
            p.setString(3, funcionario.getCpf());
            p.setString(4, funcionario.getCep());
            p.setString(5, funcionario.getEmail());
            p.setString(6, funcionario.getTelefone());
            p.setString(7, funcionario.getEstadoCivil());
            p.setString(8, funcionario.getGenero());
            p.setObject(9, funcionario.getVaga());

            p.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário!");
        }
    }
}
