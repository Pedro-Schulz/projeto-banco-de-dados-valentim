package com.app.service;

import com.app.enums.StatusVinculos;
import com.app.model.Candidato;
import com.app.repository.CandidatoRepository;
import com.app.repository.CandidaturaRepository;

import java.util.ArrayList;

public class CandidatoService {
    private final CandidatoRepository candidatoRepository;
    private final CandidaturaRepository candidaturaRepository;

    public CandidatoService(CandidatoRepository candidatoRepository, CandidaturaRepository candidaturaRepository) {
        this.candidatoRepository = candidatoRepository;
        this.candidaturaRepository = candidaturaRepository;
    }

    public void salvar(Candidato candidato) {
        candidatoRepository.salvar(candidato);
    }

    public Candidato buscarPorId(Long id) {
        return candidatoRepository.buscarPorId(id);
    }

    public ArrayList<Candidato> listarTodos() {
        return candidatoRepository.listarTodos();
    }

    public StatusVinculos desativar(Long id) {
        if(candidaturaRepository.vinculoCandidato(id)) {
            return StatusVinculos.POSSUI_VINCULOS;
        } else {
            return StatusVinculos.SUCESSO;
        }
    }
}
