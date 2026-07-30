package com.app.model;

import java.time.LocalDate;

public class Candidato {

    private Long idCandidato;
    private String nome;
    private String cpf;
    private String cep;
    private String email;
    private String telefone;
    private String genero;
    private String estadoCivil;
    private LocalDate dataNascimento;
    private Boolean ativo;
    private int version = 1;

    public Candidato() {
    }

    public Candidato(Long idCandidato, String nome, String cpf, String cep, String email, String telefone, String genero, String estadoCivil, LocalDate dataNascimento, Boolean ativo, int version) {
        this.idCandidato = idCandidato;
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.dataNascimento = dataNascimento;
        this.ativo = ativo;
        this.version = version;
    }

    public Long getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Long idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Candidato(Long idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int i) {
        this.version = i;
    }
}