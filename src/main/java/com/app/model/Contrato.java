package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {
    private Long idContrato;
    private Boolean statusContrato;
    private LocalDate dataEmissao;
    private Integer prazo;
    private Funcionario funcionario;
    private Boolean ativo;

    public Contrato(Boolean statusContrato, LocalDate dataEmissao, Integer prazo, Funcionario funcionario, Boolean ativo) {
        this.statusContrato = statusContrato;
        this.dataEmissao = dataEmissao;
        this.prazo = prazo;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID: " + this.idContrato +
                "\n> ID FUNCIONÁRIO: " + this.funcionario.getIdFuncionario() +
                "\n> STATUS DO CONTRATO: " + this.statusContrato +
                "\n> DATA DE EMISSÃO: " + this.dataEmissao +
                "\n> PRAZO: " + this.prazo;
    }
}