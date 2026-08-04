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
public class Funcionario {
    private Long idFuncionario;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve possuir entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser anterior à data atual")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é obrigatório")
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
    @Email(message = "O e-mail é inválido")
    @Size(max = 100, message = "O e-mail deve possuir no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
            regexp = "^\\d{10,11}$",
            message = "O telefone deve conter 10 ou 11 dígitos"
    )
    private String telefone;

    @NotBlank(message = "O estado civil é obrigatório")
    @Size(max = 30, message = "O estado civil deve possuir no máximo 30 caracteres")
    private String estadoCivil;

    @NotBlank(message = "O gênero é obrigatório")
    @Pattern(
            regexp = "^[MFO]$",
            message = "O gênero deve ser M, F ou O"
    )
    private String genero;

    @NotNull(message = "A vaga é obrigatória")
    private Vaga vaga;

    @NotNull(message = "O status de ativo é obrigatório")
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