package com.app.model;

import java.time.LocalDate;

public class Candidatura {

    private Long idCandidatura;
    private Funcionario funcionario;
    private Vaga vaga;
    private LocalDate dataCandidatura;
    private String status;
    private boolean ativo;

    public Candidatura() {
    }

    // Construtor completo com ID (linha 62 corrigida)
    public Candidatura(Long idCandidatura, Funcionario funcionario, Vaga vaga, LocalDate dataCandidatura, String status, boolean ativo) {
        this.idCandidatura = idCandidatura;
        this.funcionario = funcionario;
        this.vaga = vaga;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
        this.ativo = ativo;
    }

    // Construtor sem ID
    public Candidatura(Funcionario funcionario, Vaga vaga, LocalDate dataCandidatura, String status, boolean ativo) {
        this.funcionario = funcionario;
        this.vaga = vaga;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(Long idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public LocalDate getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(LocalDate dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}