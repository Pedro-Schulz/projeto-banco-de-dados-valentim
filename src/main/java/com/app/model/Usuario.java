package com.app.model;

import com.app.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String cpf;
    private String senha;
    private String perfil;
    private Boolean ativo;
    private Long idFuncionario;

    public Usuario(String cpf, String senha) {
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        Funcionario funcionario = funcionarioRepository.buscarPorCpf(cpf);
        this.cpf = cpf;
        this.senha = senha;
        this.idFuncionario = funcionario.getIdFuncionario();
        this.ativo = funcionario.getAtivo();
    }
}
