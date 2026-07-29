package com.app.service;

import com.app.model.Vaga;
import com.app.repository.VagaRepository;

import java.util.ArrayList;

public class VagaService {

    private VagaRepository vagaRepository;

    public VagaService() {
        this.vagaRepository = new VagaRepository();
    }

    public VagaService(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    public void salvar(Vaga vaga) {
        vagaRepository.salvar(vaga);
    }

    public Vaga buscarPorId(Long id) {
        return vagaRepository.buscarPorId(id);
    }

    public ArrayList<Vaga> listarTodos() {
        return vagaRepository.listarTodos();
    }

    public void desativar(Long id) {
        vagaRepository.desativar(id);
    }

    public void desativarPorDepartamento(Long idDepartamento) {
        vagaRepository.desativarPorDepartamento(idDepartamento);
    }

    public boolean vinculoDepartamento(Long idDepartamento) {
        return vagaRepository.vinculoDepartamento(idDepartamento);
    }

    public void listarVagasAbertas() {
    }
}