package com.app.service;

import java.util.ArrayList;

import com.app.model.Candidato;
import com.app.model.Vaga;
import com.app.repository.VagaRepository;

public class VagaService {
    private VagaRepository vagaRepository = new VagaRepository();

    public void salvar(Vaga vaga) {
        vagaRepository.salvar(vaga);
    }

    public Vaga buscarPorId(Long id) {
        return vagaRepository.buscarPorId(id);
    }

    public ArrayList<Vaga> listarTodos() {
        return vagaRepository.listarTodos();
    }
}
