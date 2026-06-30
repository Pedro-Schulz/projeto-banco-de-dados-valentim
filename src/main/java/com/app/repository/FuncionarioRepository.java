package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Funcionario;

import java.sql.*;

public class FuncionarioRepository {

    public void salvar(Funcionario funcionario) {
        String sql = """
            INSERT INTO funcionarios (nome, data_nascimento, cpf, cep, email, telefone, estadoCivil, genero, id_vaga)
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

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();

            if(rs.next()) {
                int id = rs.getInt(1);
                funcionario.getVaga().setIdVaga(id);
            }

        } catch(Exception e) {
            throw new RuntimeException("Erro ao salvar funcionário!", e);
        }
    }
}
