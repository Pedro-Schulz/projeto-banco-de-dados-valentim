package com.app.service;

import com.app.enums.StatusVinculos;
import com.app.model.Funcionario;
import com.app.repository.ContratoRepository;
import com.app.repository.DadosBancariosRepository;
import com.app.repository.FolhaDePagamentoRepository;
import com.app.repository.FuncionarioRepository;
import com.app.util.Validar;

import java.util.ArrayList;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private final ContratoService contratoService = new ContratoService();
    private final DadosBancariosService dadosBancariosService = new DadosBancariosService();
    private final FolhaDePagamentoService folhaDePagamentoService = new FolhaDePagamentoService();

    public void salvar(Funcionario funcionario) {
        Validar.validar(funcionario);
        funcionarioRepository.salvar(funcionario);
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.buscarPorId(id);
    }

    public Funcionario buscarPorCpf(String cpf) {
        return funcionarioRepository.buscarPorCpf(cpf);
    }

    public void atualizar(Funcionario funcionario) {
        funcionarioRepository.atualizar(funcionario);
    }

    public ArrayList<Funcionario> listarTodos() {
        return funcionarioRepository.listarTodos();
    }

    public StatusVinculos desativar(Long id) {
        if(dadosBancariosService.vinculoFuncionario(id) || contratoService.vinculoFuncionario(id) || folhaDePagamentoService.vinculoFuncionario(id)) {
            return StatusVinculos.POSSUI_VINCULOS;
        }
        funcionarioRepository.desativar(id);
        return StatusVinculos.SUCESSO;
    }

    public void desativarPorVaga(Long idVaga) {
        funcionarioRepository.desativarPorVaga(idVaga);
    }

    public boolean vinculoVaga(Long idVaga) {
        return funcionarioRepository.vinculoVaga(idVaga);
    }
}
