package com.app.model;

import com.app.enums.Perfis;
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
    private Perfis perfil;
    private Boolean ativo;
    private Long idFuncionario;
    private Integer version = 1;

    public Usuario(String cpf, String senhaHash, Perfis perfil, Boolean ativo, Long idFuncionario) {
        this.perfil = perfil;
        this.cpf = cpf;
        this.senhaHash = senhaHash;
        this.idFuncionario = idFuncionario;
        this.ativo = ativo;
    }
}
