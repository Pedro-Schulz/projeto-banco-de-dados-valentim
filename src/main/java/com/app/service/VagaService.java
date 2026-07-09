package com.app.service;

import java.util.ArrayList;
import com.app.model.Vaga;
import com.app.repository.VagaRepository;

public class VagaService {
    private VagaRepository vagaRepository = new VagaRepository();

    public ArrayList<Vaga> listarTodos() {
        return vagaRepository.listarTodos();
    }
}
