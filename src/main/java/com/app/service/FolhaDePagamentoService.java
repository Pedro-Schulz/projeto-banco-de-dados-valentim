package com.app.service;

import com.app.model.Candidato;
import com.app.model.FolhaDePagamento;
import com.app.repository.FolhaDePagamentoRepository;
import com.app.util.Validar;

import java.util.ArrayList;

public class FolhaDePagamentoService {
    private final FolhaDePagamentoRepository folhaDePagamentoRepository = new FolhaDePagamentoRepository();

    public void salvar(FolhaDePagamento folhaDePagamento) {
        Validar.validar(folhaDePagamento);
        folhaDePagamentoRepository.salvar(folhaDePagamento);
    }

    public FolhaDePagamento buscarPorId(Long id) {
        return folhaDePagamentoRepository.buscarPorId(id);
    }

    public ArrayList<FolhaDePagamento> listarTodos() {
        return folhaDePagamentoRepository.listarTodos();
    }

    public void atualizar(FolhaDePagamento folhaDePagamento) {
        folhaDePagamentoRepository.atualizar(folhaDePagamento);
    }

    public void desativar(Long id) {
        folhaDePagamentoRepository.desativar(id);
    }

    public void desativarPorFuncionario(Long idFuncionario) {
        folhaDePagamentoRepository.desativar(idFuncionario);
    }

    public boolean vinculoFuncionario(Long idFuncionario) {
        return folhaDePagamentoRepository.vinculoFuncionario(idFuncionario);
    }
}
