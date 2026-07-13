package com.app.service;

import java.util.ArrayList;

import com.app.enums.StatusVinculos;
import com.app.model.Candidato;
import com.app.model.Vaga;
import com.app.repository.CandidaturaRepository;
import com.app.repository.FuncionarioRepository;
import com.app.repository.VagaRepository;

public class VagaService {
    private final VagaRepository vagaRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final FuncionarioRepository funcionarioRepository;

    public VagaService(VagaRepository vagaRepository, CandidaturaRepository candidaturaRepository, FuncionarioRepository funcionarioRepository) {
        this.vagaRepository = vagaRepository;
        this.candidaturaRepository = candidaturaRepository;
        this.funcionarioRepository = funcionarioRepository;
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

    public StatusVinculos desativar(Long id) {
        if(candidaturaRepository.vinculoVaga(id) || funcionarioRepository.vinculoVaga(id)) {
            return StatusVinculos.POSSUI_VINCULOS;
        } else {
            return  StatusVinculos.SUCESSO;
        }
    }

    public void desativarPorDepartamento(Long idDepartamento) {
        vagaRepository.desativarPorDepartamento(idDepartamento);
    }
}
