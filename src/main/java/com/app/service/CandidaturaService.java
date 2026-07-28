package com.app.service;

import com.app.model.Candidatura;
import com.app.repository.CandidaturaRepository;

import java.util.ArrayList;

public class CandidaturaService {

    private CandidaturaRepository candidaturaRepository;

    public CandidaturaService() {
        this.candidaturaRepository = new CandidaturaRepository();
    }

    public CandidaturaService(CandidaturaRepository candidaturaRepository) {
        this.candidaturaRepository = candidaturaRepository;
    }

    public void salvar(Candidatura candidatura) {
        candidaturaRepository.salvar(candidatura);
    }

    public Candidatura buscarPorId(Long id) {
        return candidaturaRepository.buscarPorId(id);
    }

    public ArrayList<Candidatura> listarTodos() {
        return candidaturaRepository.listarTodos();
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