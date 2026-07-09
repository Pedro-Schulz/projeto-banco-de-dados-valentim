package com.app.service;

import com.app.model.Contrato;
import com.app.model.DadosBancarios;
import com.app.repository.ContratoRepository;

import java.util.ArrayList;

public class ContratoService {
    private final ContratoRepository contratoRepository = new ContratoRepository();

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
