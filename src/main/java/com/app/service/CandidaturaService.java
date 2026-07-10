package com.app.service;

import com.app.model.Candidato;
import com.app.model.Candidatura;
import com.app.repository.CandidaturaRepository;

import java.util.ArrayList;

public class CandidaturaService {
    private static final CandidaturaRepository candidaturaRepository = new CandidaturaRepository();

    public void salvar(Candidatura candidatura) {
        candidaturaRepository.salvar(candidatura);
    }

    public Candidatura buscarPorId(Long id) {
        return candidaturaRepository.buscarPorId(id);
    }

    public ArrayList<Candidatura> listarTodos() {
        return candidaturaRepository.listarTodos();
    }
}
