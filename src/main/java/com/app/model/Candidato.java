package com.app.model;

import jakarta.validation.constraints.*;
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

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve possuir entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(
            regexp = "^\\d{11}$",
            message = "O CPF deve conter exatamente 11 dígitos"
    )
    private String cpf;

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(
            regexp = "^\\d{8}$",
            message = "O CEP deve conter exatamente 8 dígitos"
    )
    private String cep;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 100, message = "O e-mail deve possuir no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
            regexp = "^\\d{10,11}$",
            message = "O telefone deve conter 10 ou 11 dígitos"
    )
    private String telefone;

    @NotBlank(message = "O gênero é obrigatório")
    @Pattern(
            regexp = "^[MFOmfo]$",
            message = "O gênero deve ser M, F ou O"
    )
    private String genero;

    @NotBlank(message = "O estado civil é obrigatório")
    @Size(max = 30, message = "O estado civil deve possuir no máximo 30 caracteres")
    private String estadoCivil;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser anterior à data atual")
    private LocalDate dataNascimento;

    @NotNull(message = "O status do candidato é obrigatório")
    private Boolean ativo;

    @NotNull
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