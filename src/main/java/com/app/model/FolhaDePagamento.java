package com.app.model;

import java.time.LocalDate;

public class FolhaDePagamento {
    private Long idFolha;
    private Integer horasTrabalhadas;
    private LocalDate dataEmissao;
    private Double descontos;
    private Integer horasExtras;
    private Funcionario funcionario;

    public FolhaDePagamento() {};

    public FolhaDePagamento(Integer horasTrabalhadas, LocalDate dataEmissao, Double descontos, Integer horasExtras, Funcionario funcionario) {
        this.horasTrabalhadas = horasTrabalhadas;
        this.dataEmissao = dataEmissao;
        this.descontos = descontos;
        this.horasExtras = horasExtras;
        this.funcionario = funcionario;
    }

    public FolhaDePagamento(Long idFolha, Integer horasTrabalhadas, LocalDate dataEmissao, Double descontos, Integer horasExtras, Funcionario funcionario) {
        this.idFolha = idFolha;
        this.horasTrabalhadas = horasTrabalhadas;
        this.dataEmissao = dataEmissao;
        this.descontos = descontos;
        this.horasExtras = horasExtras;
        this.funcionario = funcionario;
    }

    public Integer getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(Integer horasExtras) {
        this.horasExtras = horasExtras;
    }

    public Double getDescontos() {
        return descontos;
    }

    public void setDescontos(Double descontos) {
        this.descontos = descontos;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Integer getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(Integer horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Long getIdFolha() {
        return idFolha;
    }

    public void setIdFolha(Long idFolha) {
        this.idFolha = idFolha;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "\n> ID: " + this.idFolha +
                "\n> FUNCIONÁRIO: " + this.funcionario +
                "\n> HORAS TRABALHADAS: " + this.horasTrabalhadas +
                "\n> DATA DE EMISSÃO: " + this.dataEmissao +
                "\n> DESCONTOS R$ : " + this.descontos +
                "\n> HORAS EXTRAS: R$ " + this.horasExtras;
    }
}