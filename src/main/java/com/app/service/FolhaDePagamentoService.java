package com.app.service;

import com.app.repository.DadosBancariosRepository;
import com.app.repository.FolhaDePagamentoRepository;

public class FolhaDePagamentoService {
    private final FolhaDePagamentoRepository folhaDePagamentoRepository = new FolhaDePagamentoRepository();

    public void desativar(Long id) {
        folhaDePagamentoRepository.desativar(id);
    }

    public void desativarPorFuncionario(Long idFuncionario) {
        folhaDePagamentoRepository.desativar(idFuncionario);
    }
}
