package com.app.service;

import com.app.model.Contrato;
import com.app.repository.ContratoRepository;

import java.util.ArrayList;

public class ContratoService {

    private ContratoRepository contratoRepository;

    public ContratoService() {
        this.contratoRepository = new ContratoRepository();
    }

    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public void salvar(Contrato contrato) {
        contratoRepository.salvar(contrato);
    }

    public ArrayList<Contrato> listarTodos() {
        return contratoRepository.listarTodos();
    }

    public void desativar(Long id) {
        contratoRepository.desativar(id);
    }

    public void desativarPorFuncionario(Long idFuncionario) {
        contratoRepository.desativarPorFuncionario(idFuncionario);
    }

    public boolean vinculoFuncionario(Long idFuncionario) {
        return contratoRepository.vinculoFuncionario(idFuncionario);
    }
}