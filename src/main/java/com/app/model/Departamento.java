package com.app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {
    private Long idDepartamento;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve possuir entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "Os gastos são obrigatórios")
    @PositiveOrZero(message = "Os gastos não podem ser negativos")
    private Double gastos;

    @NotNull(message = "O retorno é obrigatório")
    @PositiveOrZero(message = "O retorno não pode ser negativo")
    private Double retorno;

    @NotNull(message = "O status de ativo é obrigatório")
    private Boolean ativo;

    private Integer version = 1;

    public Departamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Departamento(String nome, Double gastos, Double retorno, Boolean ativo) {
        this.nome = nome;
        this.gastos = gastos;
        this.retorno = retorno;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID: " + idDepartamento +
                "\n> NOME: " + nome +
                "\n> GASTOS: " + gastos +
                "\n> RETORNO FINANCEIRO: " + retorno;
    }
}
