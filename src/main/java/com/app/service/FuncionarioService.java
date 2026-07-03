package com.app.service;

import com.app.repository.ContratoRepository;
import com.app.repository.DadosBancariosRepository;
import com.app.repository.FuncionarioRepository;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private final ContratoRepository contratoRepository = new ContratoRepository();
    private final DadosBancariosRepository dadosBancariosRepository = new DadosBancariosRepository();

    public String deletar(Long id_funcionario) {
        if(dadosBancariosRepository.vinculoFuncionario(id_funcionario)) {
            return "Vinculado a dados bancários";
        } else if(contratoRepository.vinculoFuncionario(id_funcionario)) {
            return "Vinculado a um contrato";
        } else {
            funcionarioRepository.desativar(id_funcionario);
            return "Funcionário desativado com sucesso!";
        }
    }
}
