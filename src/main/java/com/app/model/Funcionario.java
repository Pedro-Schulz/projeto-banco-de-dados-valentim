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
    private int cargo; // ou o tipo correspondente no seu projeto

    // Construtor Padrão
    public Funcionario() {
    }

    // Construtor auxiliar rápido (linha 68)
    public Funcionario(Long idFuncionario, boolean ativo, Vaga vaga) {
        this.idFuncionario = idFuncionario;
        this.ativo = ativo;
        this.vaga = vaga;
    }

    // Construtor Completo sem ID
    public Funcionario(String nome, LocalDate dataNascimento, String cpf, String cep, String email, String telefone, String estadoCivil, String genero, Vaga vaga, boolean ativo, int cargo) {
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
        this.cargo = cargo;
    }

    // Construtor Completo com ID (linhas 100 e 142)
    public Funcionario(Long idFuncionario, String nome, LocalDate dataNascimento, String cpf, String cep, String email, String telefone, String estadoCivil, String genero, Vaga vaga, boolean ativo, int cargo) {
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
        this.cargo = cargo;
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

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public boolean getAcessoValido(String senha) {
        if (this.cpf == null || senha == null || senha.isBlank()) {
            return false;
        }

        // Limpa pontos, traços e espaços de ambos os lados para comparar de forma segura
        String cpfLimpo = this.cpf.replaceAll("[^0-9]", "");
        String senhaLimpa = senha.replaceAll("[^0-9]", "");

        // Valida se a senha bate com o CPF completo ou com os primeiros 6 dígitos do CPF
        return cpfLimpo.equals(senhaLimpa) || (cpfLimpo.length() >= 6 && cpfLimpo.startsWith(senhaLimpa));
    }
}