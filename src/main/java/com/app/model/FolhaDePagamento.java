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
public class FolhaDePagamento {
    private Long idFolha;
    private Integer horasTrabalhadas;
    private LocalDate dataEmissao;
    private Double descontos;
    private Integer horasExtras;
    private Funcionario funcionario;
    private Boolean ativo;
    private Integer version = 1;

    public FolhaDePagamento(Integer horasTrabalhadas, LocalDate dataEmissao, Double descontos, Integer horasExtras, Funcionario funcionario, Boolean ativo) {
        this.horasTrabalhadas = horasTrabalhadas;
        this.dataEmissao = dataEmissao;
        this.descontos = descontos;
        this.horasExtras = horasExtras;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID: " + this.idFolha +
                "\n> ID FUNCIONÁRIO: " + this.funcionario.getIdFuncionario() +
                "\n> HORAS TRABALHADAS: " + this.horasTrabalhadas +
                "\n> DATA DE EMISSÃO: " + this.dataEmissao +
                "\n> DESCONTOS R$ : " + this.descontos +
                "\n> HORAS EXTRAS: R$ " + this.horasExtras;
    }
}