package com.app.service;

import com.app.config.ConnectionFactory;
import com.app.enums.StatusVinculos;
import com.app.model.Candidato;
import com.app.model.Candidatura;
import com.app.repository.CandidaturaRepository;
import com.app.util.Validar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CandidaturaService {
    private final CandidaturaRepository candidaturaRepository = new CandidaturaRepository();

    public void salvar(Candidatura candidatura) {
        Validar.validar(candidatura);
        candidaturaRepository.salvar(candidatura);
    }

    public Candidatura buscarPorId(Long id) {
        return candidaturaRepository.buscarPorId(id);
    }

    public ArrayList<Candidatura> listarTodos() {
        return candidaturaRepository.listarTodos();
    }

    public void atualizar(Candidatura candidatura) {
        candidaturaRepository.atualizar(candidatura);
    }

    public void desativar(Long id) {
        candidaturaRepository.desativar(id);
    }

    public void desativarPorVaga(Long idVaga) {
        candidaturaRepository.desativarPorVaga(idVaga);
    }

    public void desativarPorCandidato(Long idCandidato) {
        candidaturaRepository.desativarPorCandidato(idCandidato);
    }

    public boolean vinculoCandidato(Long idCandidato) {
        return candidaturaRepository.vinculoCandidato(idCandidato);
    }

    public boolean vinculoVaga(Long idVaga) {
        return candidaturaRepository.vinculoVaga(idVaga);
    }
}
