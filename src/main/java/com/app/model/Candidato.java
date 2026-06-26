package com.app.model;

import java.time.LocalDate;

public class Candidato {
    private Integer idCandidato;
    private String nome;
    private String cpf;
    private String cep;
    private String email;
    private String telefone;
    private Character genero;
    private String estadoCivil;
    private LocalDate dataNascimento;

    public Candidato() {};

    public Candidato(Integer idCandidato, String nome, String cpf, String cep, String email, String telefone, Character genero, String estadoCivil, LocalDate dataNascimento) {
        this.idCandidato = idCandidato;
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.dataNascimento = dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "\n> ID: " + this.idCandidato +
                "\n> NOME: " + this.nome +
                "\n> CPF: " + this.cpf +
                "\n> DATA DE NASCIMENTO: " + this.dataNascimento +
                "\n> CEP: " + this.cep +
                "\n> EMAIL: " + this.email +
                "\n> TELEFONE: " + this.telefone +
                "\n> GÊNERO: " + this.genero +
                "\n> ESTADO CIVIL: " + this.estadoCivil;
    }
}
