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

    // Salvar nova vaga
    public void salvar(Vaga vaga) {
        if (vaga == null || vaga.getCargo() == null || vaga.getCargo().isBlank()) {
            throw new IllegalArgumentException("O cargo da vaga não pode ser vazio!");
        }
        vagaRepository.salvar(vaga);
    }

    // Buscar vaga por ID
    public Vaga buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID da vaga inválido!");
        }
        return vagaRepository.buscarPorId(id);
    }

    // Retorna todas as vagas, independentemente de estarem ativas ou não
    public ArrayList<Vaga> listarTodos() {
        return vagaRepository.listarTodos();
    }

    // Retorna apenas as vagas disponíveis e ativas
    public ArrayList<Vaga> listarVagasAbertas() {
        return vagaRepository.listarTodasAtivas();
    }

    // Retorna apenas as vagas disponíveis e ativas para um departamento específico
    public void desativar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID da vaga inválido!");
        }
        vagaRepository.desativar(id);
    }

    // Desativa todas as vagas vinculadas a um departamento específico
    public void desativarPorDepartamento(Long idDepartamento) {
        if (idDepartamento == null) return;
        vagaRepository.desativarPorDepartamento(idDepartamento);
    }

    // Verifica se existe vínculo de vagas com o departamento
    public boolean vinculoDepartamento(Long idDepartamento) {
        if (idDepartamento == null) return false;
        return vagaRepository.vinculoDepartamento(idDepartamento);
    }

    // Listar apenas vagas que estão ativas
    public ArrayList<Vaga> listarTodasAtivas() {

        return vagaRepository.listarTodasAtivas();
    }

    public ArrayList<Vaga> buscarPorDepartamento(Long idDepartamento) {
        return vagaRepository.buscarPorDepartamento(idDepartamento);
    }
}