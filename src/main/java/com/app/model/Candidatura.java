package com.app.model;

import java.time.LocalDate;

public class Candidatura {
    private Long idCandidatura;
    private Boolean statusCandidatura;
    private LocalDate dataCandidatura;
    private LocalDate prazo;
    private String etapa;
    private Vaga vaga;
    private Candidato candidato;
    private Boolean ativo;

    public Candidatura() {};

    public Candidatura(Boolean statusCandidatura, LocalDate dataCandidatura, LocalDate prazo, String etapa, Vaga vaga, Candidato candidato) {
        this.statusCandidatura = statusCandidatura;
        this.dataCandidatura = dataCandidatura;
        this.prazo = prazo;
        this.etapa = etapa;
        this.vaga = vaga;
        this.candidato = candidato;
        this.ativo = true;
    }

    public Candidatura(Long idCandidatura, Boolean statusCandidatura, LocalDate dataCandidatura, LocalDate prazo, String etapa, Vaga vaga, Candidato candidato, Boolean ativo) {
        this.idCandidatura = idCandidatura;
        this.statusCandidatura = statusCandidatura;
        this.dataCandidatura = dataCandidatura;
        this.prazo = prazo;
        this.etapa = etapa;
        this.vaga = vaga;
        this.candidato = candidato;
        this.ativo = ativo;
    }

    public Long getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(Long idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public Boolean getStatusCandidatura() {
        return statusCandidatura;
    }

    public void setStatusCandidatura(Boolean statusCandidatura) {
        this.statusCandidatura = statusCandidatura;
    }

    public LocalDate getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(LocalDate dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID DA CANDIDATURA: " + this.idCandidatura +
                "\n> VAGA: " + this.vaga +
                "\n> CANDIDATO: " + this.candidato +
                "\n> STATUS DA CANDIDATURA: " + this.statusCandidatura +
                "\n> DATA DA CANDIDATURA: " + this.dataCandidatura +
                "\n> PRAZO DA CANDIDATURA: " + this.prazo +
                "\n> ETAPA DA CANDIDATURA: " + this.etapa;
    }
}