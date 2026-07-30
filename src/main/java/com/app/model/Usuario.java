package com.app.model;

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
        this.cpf = cpf;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
        this.ativo = ativo;
        this.idFuncionario = idFuncionario;
        this.version = version;
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

    public String getPerfil() {
        return perfil;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int i) {
        this.version = i;
    }
}
