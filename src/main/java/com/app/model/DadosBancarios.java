package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosBancarios {
    private Long idDadosBancarios;
    private Integer numeroConta;
    private String instituicaoBancaria;
    private String agenciaBancaria;
    private Funcionario funcionario;
    private Boolean ativo;
    private Integer version = 1;

    public DadosBancarios(Integer numeroConta, String instituicaoBancaria, String agenciaBancaria, Funcionario funcionario, Boolean ativo) {
        this.numeroConta = numeroConta;
        this.instituicaoBancaria = instituicaoBancaria;
        this.agenciaBancaria = agenciaBancaria;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID: " + idDadosBancarios +
                "\n> ID FUNCIONÁRIO: " + funcionario.getIdFuncionario() +
                "\n> NÚMERO DA CONTA: " + numeroConta +
                "\n> INSTITUIÇÃO BANCÁRIA: " + instituicaoBancaria +
                "\n> AGÊNCIA BANCÁRIA: " + agenciaBancaria;
    }
}
