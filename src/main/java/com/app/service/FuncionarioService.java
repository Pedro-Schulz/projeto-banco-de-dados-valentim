package com.app.service;

import com.app.model.Funcionario;
import com.app.model.Vaga;
import com.app.repository.FuncionarioRepository;
import com.app.repository.VagaRepository;

import java.util.ArrayList;

public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;
    private VagaRepository vagaRepository;

    public FuncionarioService() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.vagaRepository = new VagaRepository();
    }

    // 1. Cadastrar novo funcionário
    public void cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("O funcionário não pode ser nulo.");
        }
        if (funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do funcionário é obrigatório!");
        }
        if (funcionario.getCpf() == null || funcionario.getCpf().isBlank()) {
            throw new IllegalArgumentException("O CPF do funcionário é obrigatório!");
        }
        if (funcionario.getEmail() == null || funcionario.getEmail().isBlank()) {
            throw new IllegalArgumentException("O e-mail do funcionário é obrigatório!");
        }

        Funcionario existente = funcionarioRepository.buscarPorCpf(funcionario.getCpf());
        if (existente != null) {
            throw new IllegalArgumentException("Já existe um funcionário cadastrado com este CPF!");
        }

        funcionario.setAtivo(true);

        funcionarioRepository.salvar(funcionario);
    }

    // 2. Buscar funcionário por CPF
    public Funcionario buscarPorCpf(String cpf) {
        return funcionarioRepository.buscarPorCpf(cpf);
    }

    // 3. Buscar funcionário por ID
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.buscarPorId(id);
    }

    // 4. Listar todos os funcionários
    public ArrayList<Funcionario> listarTodos() {
        return funcionarioRepository.listarTodos();
    }

    public void desativar(long idDesativar) {
        funcionarioRepository.desativar(idDesativar);
    }

    public void ativarFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.buscarPorId(id);
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado!");
        }
        if (funcionario.getAtivo()) {
            throw new IllegalArgumentException("Este funcionário já está ativo!");
        }

        funcionarioRepository.ativar(id);
    }

    // 5. Buscar funcionário por E-mail
    public Funcionario buscarPorEmail(String email) {
        return funcionarioRepository.buscarPorEmail(email);
    }

    // 6. Atualizar dados do funcionário
    public void atualizar(Funcionario funcionario) {
        funcionarioRepository.atualizar(funcionario);
    }

    // 7. Desativar funcionários atrelados a uma Vaga
    public void desativarPorVaga(Long idVaga) {
        funcionarioRepository.desativarPorVaga(idVaga);
    }

    // 8. Verificar se existem funcionários ativos em uma Vaga
    public boolean vinculoVaga(Long idVaga) {
        return funcionarioRepository.vinculoVaga(idVaga);
    }

    public ArrayList<Funcionario> buscarPorVaga(Long idVaga) {
        return funcionarioRepository.buscarPorVaga(idVaga);
    }
}