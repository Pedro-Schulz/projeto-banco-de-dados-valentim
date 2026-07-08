package com.app.service;

import com.app.model.FolhaDePagamento;
import com.app.repository.FolhaDePagamentoRepository;

import java.util.ArrayList;

public class FolhaDePagamentoService {
    private final FolhaDePagamentoRepository folhaDePagamentoRepository = new FolhaDePagamentoRepository();

    public ArrayList<FolhaDePagamento> listarTodos() {
        return folhaDePagamentoRepository.listarTodos();
    }

    public void desativar(Long id) {
        folhaDePagamentoRepository.desativar(id);
    }

    public void desativarPorFuncionario(Long idFuncionario) {
        folhaDePagamentoRepository.desativar(idFuncionario);
    }
}
