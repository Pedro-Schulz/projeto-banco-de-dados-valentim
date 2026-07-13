package com.app.service;

import com.app.model.Candidato;
import com.app.model.Contrato;
import com.app.repository.ContratoRepository;
import java.util.ArrayList;

public class ContratoService {
    private final ContratoRepository contratoRepository;

    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public void salvar(Contrato contrato) {
        contratoRepository.salvar(contrato);
    }

    public Contrato buscarPorId(Long id) {
        return contratoRepository.buscarPorId(id);
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
}
