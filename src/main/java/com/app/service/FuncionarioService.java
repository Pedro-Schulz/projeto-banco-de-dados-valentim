package com.app.service;

import com.app.enums.StatusVinculos;
import com.app.model.Funcionario;
import com.app.repository.ContratoRepository;
import com.app.repository.DadosBancariosRepository;
import com.app.repository.FolhaDePagamentoRepository;
import com.app.repository.FuncionarioRepository;
import java.util.ArrayList;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final ContratoRepository contratoRepository;
    private final DadosBancariosRepository dadosBancariosRepository;
    private final FolhaDePagamentoRepository folhaDePagamentoRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, ContratoRepository contratoRepository, DadosBancariosRepository dadosBancariosRepository, FolhaDePagamentoRepository folhaDePagamentoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.contratoRepository = contratoRepository;
        this.dadosBancariosRepository = dadosBancariosRepository;
        this.folhaDePagamentoRepository = folhaDePagamentoRepository;
    }

    public void salvar(Funcionario funcionario) {
        funcionarioRepository.salvar(funcionario);
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.buscarPorId(id);
    }

    public ArrayList<Funcionario> listarTodos() {
        return funcionarioRepository.listarTodos();
    }

    public StatusVinculos desativar(Long id) {
        if(dadosBancariosRepository.vinculoFuncionario(id) || contratoRepository.vinculoFuncionario(id) || folhaDePagamentoRepository.vinculoFuncionario(id)) {
            return StatusVinculos.POSSUI_VINCULOS;
        }
        funcionarioRepository.desativar(id);
        return StatusVinculos.SUCESSO;
    }

    public void desativarPorVaga(Long idVaga) {
        funcionarioRepository.desativarPorVaga(idVaga);
    }
}
