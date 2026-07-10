package com.app.service;

import com.app.enums.StatusVinculos;
import com.app.model.Funcionario;
import com.app.repository.ContratoRepository;
import com.app.repository.DadosBancariosRepository;
import com.app.repository.FolhaDePagamentoRepository;
import com.app.repository.FuncionarioRepository;
import java.util.ArrayList;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private final ContratoRepository contratoRepository = new ContratoRepository();
    private final DadosBancariosRepository dadosBancariosRepository = new DadosBancariosRepository();
    private final FolhaDePagamentoRepository folhaDePagamentoRepository = new FolhaDePagamentoRepository();

    public StatusVinculos desativar(Long id) {
        if(dadosBancariosRepository.vinculoFuncionario(id) || contratoRepository.vinculoFuncionario(id) || folhaDePagamentoRepository.vinculoFuncionario(id)) {
            return StatusVinculos.POSSUI_VINCULOS;
        }
        funcionarioRepository.desativar(id);
        return StatusVinculos.SUCESSO;
    }

    public ArrayList<Funcionario> listarTodos() {
        return funcionarioRepository.listarTodos();
    }
}
