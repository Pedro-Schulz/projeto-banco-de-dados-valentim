package com.app.service;

import com.app.model.Candidato;
import com.app.model.Contrato;
import com.app.repository.ContratoRepository;
import com.app.util.Validar;

import java.util.ArrayList;

public class ContratoService {
    private final ContratoRepository contratoRepository = new ContratoRepository();

    public void salvar(Contrato contrato) {
        Validar.validar(contrato);
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

    public boolean vinculoFuncionario(Long idFuncionario) {
        return contratoRepository.vinculoFuncionario(idFuncionario);
    }
}
