package com.app.service;

import com.app.model.Candidato;
import com.app.model.DadosBancarios;
import com.app.repository.DadosBancariosRepository;

import java.util.ArrayList;

public class DadosBancariosService {
    private final DadosBancariosRepository dadosBancariosRepository = new DadosBancariosRepository();

    public void salvar(DadosBancarios dadosBancarios) {
        dadosBancariosRepository.salvar(dadosBancarios);
    }

    public DadosBancarios buscarPorId(Long id) {
        return dadosBancariosRepository.buscarPorId(id);
    }

    public ArrayList<DadosBancarios> listarTodos() {
        return dadosBancariosRepository.listarTodos();
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
