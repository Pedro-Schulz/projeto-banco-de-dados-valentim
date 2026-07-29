package com.app.service;

import java.util.ArrayList;

import com.app.enums.StatusVinculos;
import com.app.model.Candidato;
import com.app.model.Vaga;
import com.app.repository.CandidaturaRepository;
import com.app.repository.FuncionarioRepository;
import com.app.repository.VagaRepository;

public class VagaService {
    private final VagaRepository vagaRepository = new VagaRepository();
    private final CandidaturaService candidaturaService = new CandidaturaService();
    private final FuncionarioService funcionarioService = new FuncionarioService();

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
        if(candidaturaService.vinculoVaga(id) || funcionarioService.vinculoVaga(id)) {
            return StatusVinculos.POSSUI_VINCULOS;
        } else {
            return  StatusVinculos.SUCESSO;
        }
    }

    public void desativarPorDepartamento(Long idDepartamento) {
        vagaRepository.desativarPorDepartamento(idDepartamento);
    }

    public boolean vinculoDepartamento(Long idDepartamento) {
        return vagaRepository.vinculoDepartamento(idDepartamento);
    }
}
