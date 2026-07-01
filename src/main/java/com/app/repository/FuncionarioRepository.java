package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Funcionario;
import com.app.model.Vaga;
import java.sql.*;
import java.time.LocalDate;

public class FuncionarioRepository {

    public void salvar(Funcionario funcionario) {
        String sql = """
            INSERT INTO funcionarios (nome, data_nascimento, cpf, cep, email, telefone, estadoCivil, genero, id_vaga)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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
                Long id = rs.getLong(1);
                funcionario.getVaga().setIdVaga(id);
            }

        } catch(Exception e) {
            throw new RuntimeException("Erro ao salvar funcionário!", e);
        }
    }

    public static Funcionario buscarPorId(Long id) {
        String sql = """
            SELECT * 
            FROM funcionarios
            WHERE id_funcionario = ?;
        """;

        try {
            Connection c = ConnectionFactory.getConnection();
            PreparedStatement p = c.prepareStatement(sql);

            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                Long idVaga = rs.getLong("id_vaga");
                Vaga vaga = VagaRepository.buscarPorId(idVaga);

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
                    vaga
                );

                return funcionario;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar funcionário!", e);
        }
        return null;
    }
}
