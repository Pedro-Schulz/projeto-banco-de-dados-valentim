package com.app.service;

import com.app.exception.ContratoVinculadoException;
import com.app.exception.DadosBancariosVinculadosException;
import com.app.exception.EntidadeVinculadoException;
import com.app.repository.ContratoRepository;
import com.app.repository.DadosBancariosRepository;
import com.app.repository.FuncionarioRepository;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private final ContratoRepository contratoRepository = new ContratoRepository();
    private final DadosBancariosRepository dadosBancariosRepository = new DadosBancariosRepository();

    public void desativar(Long id_funcionario) throws RuntimeException {
        if(dadosBancariosRepository.vinculoFuncionario(id_funcionario)) {
            throw new DadosBancariosVinculadosException();
        } else if(contratoRepository.vinculoFuncionario(id_funcionario)) {
            throw new ContratoVinculadoException();
        } else {
            funcionarioRepository.desativar(id_funcionario);
        }
    }
}
