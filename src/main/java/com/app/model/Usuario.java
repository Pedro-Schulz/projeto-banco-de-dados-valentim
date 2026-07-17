package com.app.model;

import com.app.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

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

    public Usuario(String cpf, String senhaHash) {
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        Funcionario funcionario = funcionarioRepository.buscarPorCpf(cpf);
        this.cpf = cpf;
        this.senhaHash = senhaHash;
        this.idFuncionario = funcionario.getIdFuncionario();
        this.ativo = funcionario.getAtivo();
    }
}
