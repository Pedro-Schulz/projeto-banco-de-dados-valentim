package com.app.model;

import java.time.LocalDate;

public class Funcionario {
    private Integer id_funcionario;
    private String nome;
    private LocalDate data_aniversario;
    private String cpf;
    private String cep;
    private String email;
    private String telefone;
    private String estado_civil;
    private String genero;

    public Funcionario() {};

    public Funcionario(Integer id_funcionario, String nome, LocalDate data_aniversario, String cpf, String cep, String email, String telefone, String estado_civil, String genero) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.data_aniversario = data_aniversario;
        this.cpf = cpf;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.estado_civil = estado_civil;
        this.genero = genero;
    }

    public Integer getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData_aniversario() {
        return data_aniversario;
    }

    public void setData_aniversario(LocalDate data_aniversario) {
        this.data_aniversario = data_aniversario;
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

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "\n> ID: " + id_funcionario +
                "\n> NOME: " + nome +
                "\n> DATA DE ANIVERSÁRIO: " + data_aniversario +
                "\n> CPF: " + cpf +
                "\n> CEP: " + cep +
                "\n> E-MAIL: " + email +
                "\n> TELEFONE: " + telefone +
                "\n> ESTADO CIVIL: " + estado_civil +
                "\n> GÊNERO: " + genero;
    }
}