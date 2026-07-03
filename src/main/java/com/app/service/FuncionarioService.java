package com.app.service;

import com.app.exception.ContratoVinculadoException;
import com.app.exception.DadosBancariosVinculadosException;
import com.app.repository.ContratoRepository;
import com.app.repository.DadosBancariosRepository;
import com.app.repository.FuncionarioRepository;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private final ContratoRepository contratoRepository = new ContratoRepository();
    private final DadosBancariosRepository dadosBancariosRepository = new DadosBancariosRepository();

    public void desativar(Long id) throws RuntimeException {
        if(dadosBancariosRepository.vinculoFuncionario(id)) {
            throw new DadosBancariosVinculadosException();
        } else if(contratoRepository.vinculoFuncionario(id)) {
            throw new ContratoVinculadoException();
        } else {
            funcionarioRepository.desativar(id);
        }
    }
}
