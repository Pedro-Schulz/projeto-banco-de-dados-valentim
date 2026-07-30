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
    private boolean ativo;
    private String perfil; // "ADM" ou "USER"
    private String senha;  // Armazena a senha (texto puro ou Hash BCrypt)
    private int version = 1;

    // Construtor Padrão
    public Funcionario() {
    }

    public Funcionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    // Construtor auxiliar rápido
    public Funcionario(Long idFuncionario, boolean ativo, Vaga vaga) {
        this.idFuncionario = idFuncionario;
        this.ativo = ativo;
        this.vaga = vaga;
    }

    // Construtor Completo sem ID (Útil para novos cadastros)
    public Funcionario(String nome, LocalDate dataNascimento, String cpf, String cep, String email,
                       String telefone, String estadoCivil, String genero, Vaga vaga, boolean ativo,
                       String perfil, String senha) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.vaga = vaga;
        this.ativo = ativo;
        this.perfil = perfil;
        this.senha = senha;
    }

    // Construtor Completo com ID (Usado ao buscar do Banco de Dados)
    public Funcionario(Long idFuncionario, String nome, LocalDate dataNascimento, String cpf, String cep,
                       String email, String telefone, String estadoCivil, String genero, Vaga vaga,
                       boolean ativo, String perfil, String senha) {
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
        this.ativo = ativo;
        this.perfil = perfil;
        this.senha = senha;
    }

    // Getters e Setters
    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}