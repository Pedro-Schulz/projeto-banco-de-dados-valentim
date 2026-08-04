package com.app.service;

import com.app.model.Candidato;
import com.app.model.DadosBancarios;
import com.app.repository.DadosBancariosRepository;
import com.app.util.Validar;

import java.util.ArrayList;

public class DadosBancariosService {
    private final DadosBancariosRepository dadosBancariosRepository = new DadosBancariosRepository();

    public void salvar(DadosBancarios dadosBancarios) {
        Validar.validar(dadosBancarios);
        dadosBancariosRepository.salvar(dadosBancarios);
    }

    public DadosBancarios buscarPorId(Long id) {
        return dadosBancariosRepository.buscarPorId(id);
    }

    public ArrayList<DadosBancarios> listarTodos() {
        return dadosBancariosRepository.listarTodos();
    }

    public void atualizar(DadosBancarios dadosBancarios) {
        dadosBancariosRepository.atualizar(dadosBancarios);
    }

    public void desativar(Long id) {
        dadosBancariosRepository.desativar(id);
    }

    public void desativarPorFuncionario(Long idFuncionario) {
        dadosBancariosRepository.desativarPorFuncionario(idFuncionario);
    }

    public boolean vinculoFuncionario(Long idFuncionario) {
        return dadosBancariosRepository.vinculoFuncionario(idFuncionario);
    }
}
