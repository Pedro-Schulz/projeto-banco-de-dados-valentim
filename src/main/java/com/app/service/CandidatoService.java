package com.app.service;

import com.app.model.Candidato;
import com.app.repository.CandidatoRepository;

import java.util.ArrayList;

public class CandidatoService {
    private static final CandidatoRepository candidatoRepository = new CandidatoRepository();

    public ArrayList<Candidato> listarTodos() {
        return candidatoRepository.listarTodos();
    }
}
