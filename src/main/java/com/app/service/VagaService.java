package com.app.service;

import java.util.ArrayList;

import com.app.enums.StatusVinculos;
import com.app.exception.DadosInvalidosException;
import com.app.model.Candidato;
import com.app.model.Vaga;
import com.app.repository.CandidaturaRepository;
import com.app.repository.FuncionarioRepository;
import com.app.repository.VagaRepository;
import com.app.util.Validar;

public class VagaService {
    private final VagaRepository vagaRepository = new VagaRepository();
    private final CandidaturaService candidaturaService = new CandidaturaService();
    private final FuncionarioService funcionarioService = new FuncionarioService();

    public void salvar(Vaga vaga) {
        Validar.validar(vaga);
        validarCargo(vaga);
        vagaRepository.salvar(vaga);
    }

    public void validarCargo(Vaga vaga) {
        if (vaga.getCargo().equals("estagiario")
                || vaga.getCargo().equals("auxiliar")
                || vaga.getCargo().equals("assistente")
                || vaga.getCargo().equals("tecnico")
                || vaga.getCargo().equals("analista")
                || vaga.getCargo().equals("consultor")
                || vaga.getCargo().equals("especialista")
                || vaga.getCargo().equals("supervisor")
                || vaga.getCargo().equals("coordenador")
                || vaga.getCargo().equals("gerente")
                || vaga.getCargo().equals("diretor")
                || vaga.getCargo().equals("presidente")) {
            return;
        } else {
            throw new DadosInvalidosException("Erro! Este cargo não existe!");
        }
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
