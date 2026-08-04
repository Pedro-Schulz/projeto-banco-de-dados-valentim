package com.app.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {
    private Long idCandidato;
    @NotEmpty
    private String nome;
    private String cpf;
    private String cep;
    @Email
    private String email;
    private String telefone;
    @Size(min = 1, max = 1)
    private String genero;
    @NotEmpty
    private String estadoCivil;
    private LocalDate dataNascimento;
    private Boolean ativo;
    private Integer version = 1;

    public Candidato(Long idCandidato) { this.idCandidato = idCandidato; }

    public Candidato(String nome, String cpf, String cep, String email, String telefone, String genero, String estadoCivil, LocalDate dataNascimento, Boolean ativo) {
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