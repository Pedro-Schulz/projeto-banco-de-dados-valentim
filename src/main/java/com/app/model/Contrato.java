package com.app.model;

import java.time.LocalDate;

public class Contrato {
    private Integer idContrato;
    private Boolean statusContrato;
    private LocalDate dataEmissao;
    private Integer prazo;
    private Funcionario funcionario;

    public Contrato() {};

    public Contrato(Integer idContrato, Boolean statusContrato, LocalDate dataEmissao, Integer prazo, Funcionario funcionario) {
        this.idContrato = idContrato;
        this.statusContrato = statusContrato;
        this.dataEmissao = dataEmissao;
        this.prazo = prazo;
        this.funcionario = funcionario;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Boolean getStatusContrato() {
        return statusContrato;
    }

    public void setStatusContrato(Boolean statusContrato) {
        this.statusContrato = statusContrato;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "\n> ID: " + this.idContrato +
                "\n> FUNCIONÁRIO: " + this.funcionario +
                "\n> STATUS DO CONTRATO: " + this.statusContrato +
                "\n> DATA DE EMISSÃO: " + this.dataEmissao +
                "\n> PRAZO: " + this.prazo;
    }
}