package com.app.service;

import com.app.model.Funcionario;
import com.app.repository.FuncionarioRepository;

import java.util.ArrayList;

public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService() {
        this.funcionarioRepository = new FuncionarioRepository();
    }

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
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

    public void desativar(Long id) {
        funcionarioRepository.desativar(id);
    }

    public void desativarPorVaga(Long idVaga) {
        funcionarioRepository.desativarPorVaga(idVaga);
    }

    public boolean vinculoVaga(Long idVaga) {
        return funcionarioRepository.vinculoVaga(idVaga);
    }

    public Funcionario buscarPorEmail(String email) {
        return funcionarioRepository.buscarPorEmail(email);
    }

    public Funcionario buscarPorCpf(String cpf) {
        return funcionarioRepository.buscarPorCpf(cpf);
    }
}