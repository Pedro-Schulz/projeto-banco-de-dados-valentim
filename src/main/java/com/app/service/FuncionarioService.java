package com.app.service;

import com.app.model.DadosBancarios;
import com.app.model.Funcionario;
import com.app.repository.ContratoRepository;
import com.app.repository.DadosBancariosRepository;
import com.app.repository.FuncionarioRepository;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private final ContratoRepository contratoRepository = new ContratoRepository();
    private final DadosBancariosRepository dadosBancariosRepository = new DadosBancariosRepository();

    public boolean possuiContrato(Long id_funcionario) {
        return contratoRepository.vinculoFuncionario(id_funcionario);
    }

    public boolean possuiDadosBancarios(Long id_funcionario) {

    }
}
