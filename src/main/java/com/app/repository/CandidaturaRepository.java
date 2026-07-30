package com.app.repository;

import com.app.config.ConnectionFactory;
import com.app.model.Candidatura;
import com.app.model.Funcionario;
import com.app.model.Vaga;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class CandidaturaRepository {

    public void salvar(Candidatura candidatura) {
        String sql = """
            INSERT INTO candidaturas (id_funcionario, id_vaga, data_candidatura, status, ativo)
            VALUES (?, ?, ?, ?, ?);
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            p.setLong(1, candidatura.getFuncionario().getIdFuncionario());
            p.setLong(2, candidatura.getVaga().getIdVaga());
            p.setDate(3, candidatura.getDataCandidatura() != null ? Date.valueOf(candidatura.getDataCandidatura()) : null);
            p.setString(4, candidatura.getStatus());
            p.setBoolean(5, candidatura.isAtivo());

            p.executeUpdate();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                candidatura.setIdCandidatura(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar candidatura!", e);
        }
    }

    public ArrayList<Candidatura> listarTodos() {
        ArrayList<Candidatura> lista = new ArrayList<>();
        String sql = "SELECT * FROM candidaturas WHERE ativo = true;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql);
                ResultSet rs = p.executeQuery()
        ) {
            while (rs.next()) {
                lista.add(montarObjetoCandidatura(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar candidaturas!", e);
        }
        return lista;
    }

    public Candidatura buscarPorId(Long id) {
        String sql = "SELECT * FROM candidaturas WHERE id_candidatura = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                return montarObjetoCandidatura(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar candidatura por ID!", e);
        }
        return null;
    }

    public void atualizar(Candidatura candidatura) {
        String sql = """
            UPDATE candidaturas
            SET status = ?, data_candidatura = ?, ativo = ?
            WHERE id_candidatura = ?;
        """;

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setString(1, candidatura.getStatus());
            p.setDate(2, candidatura.getDataCandidatura() != null ? Date.valueOf(candidatura.getDataCandidatura()) : null);
            p.setBoolean(3, candidatura.isAtivo());
            p.setLong(4, candidatura.getIdCandidatura());

            if (p.executeUpdate() == 0) {
                throw new RuntimeException("Registro não encontrado para atualização!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar candidatura!", e);
        }
    }

    public void desativar(Long id) {
        String sql = "UPDATE candidaturas SET ativo = false WHERE id_candidatura = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar candidatura!", e);
        }
    }

    public void desativarPorVaga(Long idVaga) {
        String sql = "UPDATE candidaturas SET ativo = false WHERE id_vaga = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idVaga);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar candidaturas por vaga!", e);
        }
    }

    public void desativarPorCandidato(Long idCandidato) {
        String sql = "UPDATE candidaturas SET ativo = false WHERE id_candidato = ?;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idCandidato);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao desativar candidaturas por candidato!", e);
        }
    }

    public boolean vinculoCandidato(Long idCandidato) {
        String sql = "SELECT 1 FROM candidaturas WHERE id_candidato = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idCandidato);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo com o candidato!", e);
        }
    }

    public boolean vinculoVaga(Long idVaga) {
        String sql = "SELECT 1 FROM candidaturas WHERE id_vaga = ? AND ativo = true LIMIT 1;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idVaga);
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar vínculo com a vaga!", e);
        }
    }

    // Busca candidaturas ativas por Candidato (Funcionário)
    public ArrayList<Candidatura> buscarPorCandidato(Long idFuncionario) throws RuntimeException {
        ArrayList<Candidatura> candidaturas = new ArrayList<>();
        // Trocou id_funcionario por id_candidato
        String sql = "SELECT * FROM candidaturas WHERE id_candidato = ? AND ativo = true;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idFuncionario);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                candidaturas.add(montarObjetoCandidatura(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar candidaturas por candidato!", e);
        }

        return candidaturas;
    }

    // Busca candidaturas ativas por Vaga
    public ArrayList<Candidatura> buscarPorVaga(Long idVaga) throws RuntimeException {
        ArrayList<Candidatura> candidaturas = new ArrayList<>();
        String sql = "SELECT * FROM candidaturas WHERE id_vaga = ? AND ativo = true;";

        try (
                Connection c = ConnectionFactory.getConnection();
                PreparedStatement p = c.prepareStatement(sql)
        ) {
            p.setLong(1, idVaga);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                candidaturas.add(montarObjetoCandidatura(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar candidaturas por vaga!", e);
        }

        return candidaturas;
    }

    // Método auxiliar privado para centralizar a criação dos objetos
    // Método auxiliar privado para montar o objeto Candidatura com leitura segura de colunas
    private Candidatura montarObjetoCandidatura(ResultSet rs) throws Exception {

        // Tenta buscar o ID do candidato testando 'id_candidato' e depois 'id_funcionario'
        Long idCandidato = 0L;
        try {
            idCandidato = rs.getLong("id_candidato");
        } catch (Exception e) {
            try {
                idCandidato = rs.getLong("id_funcionario");
            } catch (Exception ex) {
                // Se nenhum dos dois nomes existir
            }
        }

        Funcionario funcionario = new Funcionario(idCandidato);
        Vaga vaga = new Vaga(rs.getLong("id_vaga"));

        // Leitura segura da Data da Candidatura
        LocalDate dataCandidatura = null;
        try {
            if (rs.getDate("data_candidatura") != null) {
                dataCandidatura = rs.getDate("data_candidatura").toLocalDate();
            }
        } catch (Exception e) {
            // Ignora se a coluna não existir no BD
        }

        // Leitura segura do Status
        String status = "PENDENTE";
        try {
            status = rs.getString("status");
            if (status == null) status = "PENDENTE";
        } catch (Exception e) {
            // Ignora se a coluna não existir no BD
        }

        return new Candidatura(
                rs.getLong("id_candidatura"),
                funcionario,
                vaga,
                dataCandidatura,
                status,
                rs.getBoolean("ativo")
        );
    }
}