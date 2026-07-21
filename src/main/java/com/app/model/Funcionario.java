package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Boolean ativo;
    private Integer version = 1;

    public Funcionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    };

    public Funcionario(Long idFuncionario, Boolean ativo, Vaga vaga) {
        this.idFuncionario = idFuncionario;
        this.ativo = ativo;
        this.vaga = vaga;
    }

    public Funcionario(String nome, LocalDate dataNascimento, String cpf, String cep, String email, String telefone, String estadoCivil, String genero, Vaga vaga, Boolean ativo) {
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
                "\n> ID VAGA: " + this.vaga.getIdVaga();
    }
}