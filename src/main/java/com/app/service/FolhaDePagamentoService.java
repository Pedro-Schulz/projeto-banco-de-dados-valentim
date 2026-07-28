package com.app.service;

import com.app.model.FolhaDePagamento;
import com.app.repository.FolhaDePagamentoRepository;

import java.util.ArrayList;

public class FolhaDePagamentoService {

    private FolhaDePagamentoRepository folhaDePagamentoRepository;

    public FolhaDePagamentoService() {
        this.folhaDePagamentoRepository = new FolhaDePagamentoRepository();
    }

    public FolhaDePagamentoService(FolhaDePagamentoRepository folhaDePagamentoRepository) {
        this.folhaDePagamentoRepository = folhaDePagamentoRepository;
    }

    public void salvar(FolhaDePagamento folha) {
        folhaDePagamentoRepository.salvar(folha);
    }

    public FolhaDePagamento buscarPorId(Long id) {
        return folhaDePagamentoRepository.buscarPorId(id);
    }

    public ArrayList<FolhaDePagamento> listarTodos() {
        return folhaDePagamentoRepository.listarTodos();
    }

    public void desativar(Long id) {
        folhaDePagamentoRepository.desativar(id);
    }

    public void desativarPorFuncionario(Long idFuncionario) {
        folhaDePagamentoRepository.desativarPorFuncionario(idFuncionario);
    }

    public boolean vinculoFuncionario(Long idFuncionario) {
        return folhaDePagamentoRepository.vinculoFuncionario(idFuncionario);
    }
}