package com.app.model;

import java.time.LocalDate;

public class FolhaDePagamento {
    private Integer idFolha;
    private Integer horasTrabalhadas;
    private LocalDate dataEmissao;
    private Double descontos;
    private Integer horasExtras;

    public FolhaDePagamento() {};

    public FolhaDePagamento(Integer idFolha, Integer horasTrabalhadas, LocalDate dataEmissao, Double descontos, Integer horasExtras) {
        this.idFolha = idFolha;
        this.horasTrabalhadas = horasTrabalhadas;
        this.dataEmissao = dataEmissao;
        this.descontos = descontos;
        this.horasExtras = horasExtras;
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

    public Integer getIdFolha() {
        return idFolha;
    }

    public void setIdFolha(Integer idFolha) {
        this.idFolha = idFolha;
    }

    @Override
    public String toString() {
        return "\n> ID: " + this.idFolha +
                "\n> HORAS TRABALHADAS: " + this.horasTrabalhadas +
                "\n> DATA DE EMISSÃO: " + this.dataEmissao +
                "\n> DESCONTOS R$ : " + this.descontos +
                "\n> HORAS EXTRAS: R$ " + this.horasExtras;
    }
}