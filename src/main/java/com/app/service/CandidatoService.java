package com.app.service;

import com.app.enums.StatusVinculos;
import com.app.model.Candidato;
import com.app.repository.CandidatoRepository;
import com.app.repository.CandidaturaRepository;
import com.app.util.Validar;

import java.util.ArrayList;

public class CandidatoService {
    private final CandidatoRepository candidatoRepository = new CandidatoRepository();
    private final CandidaturaService candidaturaService = new CandidaturaService();

    public void salvar(Candidato candidato) {
        Validar.validar(candidato);
        candidatoRepository.salvar(candidato);
    }

    public Candidato buscarPorId(Long id) {
        return candidatoRepository.buscarPorId(id);
    }

    public ArrayList<Candidato> listarTodos() {
        return candidatoRepository.listarTodos();
    }

    public StatusVinculos desativar(Long id) {
        if(candidaturaService.vinculoCandidato(id)) {
            return StatusVinculos.POSSUI_VINCULOS;
        }
        candidatoRepository.desativar(id);
        return StatusVinculos.SUCESSO;
    }
}
