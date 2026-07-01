package com.app.model;

import java.time.LocalDate;

public class Funcionario {
    private Long idFuncionario;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String cep;
    private String email;
    private String telefone;
    private String estadoCivil;
    private String genero;
    private Vaga vaga;

    public Funcionario() {};

    public Funcionario(String nome, LocalDate dataNascimento, String cpf, String cep, String email, String telefone, String estadoCivil, String genero, Vaga vaga) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.vaga = vaga;
    }

    public Funcionario(Long idFuncionario, String nome, LocalDate dataNascimento, String cpf, String cep, String email, String telefone, String estadoCivil, String genero, Vaga vaga) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.vaga = vaga;
    }

    public Long getIdFuncionario() { return idFuncionario; }

    public void setIdFuncionario(Long idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCep() { return cep; }

    public void setCep(String cep) { this.cep = cep; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEstadoCivil() { return estadoCivil; }

    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getGenero() { return genero; }

    public void setGenero(String genero) { this.genero = genero; }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    @Override
    public String toString() {
        return "\n> ID: " + idFuncionario +
                "\n> NOME: " + nome +
                "\n> DATA DE NASCIMENTO: " + dataNascimento +
                "\n> CPF: " + cpf +
                "\n> CEP: " + cep +
                "\n> E-MAIL: " + email +
                "\n> TELEFONE: " + telefone +
                "\n> ESTADO CIVIL: " + estadoCivil +
                "\n> GÊNERO: " + genero +
                "\n> VAGA: " + this.vaga.getIdVaga();
    }
}