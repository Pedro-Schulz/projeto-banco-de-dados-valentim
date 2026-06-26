package com.app.model;

import java.time.LocalDate;

public class Contrato {
    private Integer idContrato;
    private Boolean statusContrato;
    private LocalDate dataEmissao;
    private Integer prazo;

    public Contrato() {};

    public Contrato(Integer idContrato, Boolean statusContrato, LocalDate dataEmissao, Integer prazo) {
        this.idContrato = idContrato;
        this.statusContrato = statusContrato;
        this.dataEmissao = dataEmissao;
        this.prazo = prazo;
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

    @Override
    public String toString() {
        return "\n> ID: " + this.idContrato +
                "\n> STATUS DO CONTRATO: " + this.statusContrato +
                "\n> DATA DE EMISSÃO: " + this.dataEmissao +
                "\n> PRAZO: " + this.prazo;
    }
}