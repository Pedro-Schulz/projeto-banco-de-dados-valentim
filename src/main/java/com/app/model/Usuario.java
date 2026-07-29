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
    private String senhaHash;
    private String perfil;
    private Boolean ativo;
    private Long idFuncionario;
    private Integer version = 1;

    public Usuario(String cpf, String senhaHash, String perfil, boolean ativo, long idFuncionario, int version) {
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        Funcionario funcionario = funcionarioRepository.buscarPorCpf(cpf);
        this.cpf = cpf;
        this.senhaHash = senhaHash;
        this.idFuncionario = Long.valueOf(funcionario.getIdFuncionario());
        this.ativo = funcionario.getAtivo();
    }

    public boolean getAtivo() {
        return ativo;
    }

    public String getCpf() {
        return cpf;
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public String getSenhaHash() {
        return senhaHash;
    }
}
