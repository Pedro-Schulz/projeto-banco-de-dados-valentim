package com.app.model;

import java.time.LocalDate;

public class Candidato {
    private Long idCandidato;
    private String nome;
    private String cpf;
    private String cep;
    private String email;
    private String telefone;
    private Character genero;
    private String estadoCivil;
    private LocalDate dataNascimento;
    private Boolean ativo;

    public Candidato() {}

    public Candidato(String nome, String cpf, String cep, String email, String telefone, Character genero, String estadoCivil, LocalDate dataNascimento, Boolean ativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.dataNascimento = dataNascimento;
        this.ativo = ativo;
    }

    public Candidato(Long idCandidato, String nome, String cpf, String cep, String email, String telefone, Character genero, String estadoCivil, LocalDate dataNascimento, Boolean ativo) {
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
    }

    public String getEstadoCivil() { return estadoCivil; }

    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public Character getGenero() { return genero; }

    public void setGenero(Character genero) { this.genero = genero; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCep() { return cep; }

    public void setCep(String cep) { this.cep = cep; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Long getIdCandidato() { return idCandidato; }

    public void setIdCandidato(Long idCandidato) { this.idCandidato = idCandidato; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

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