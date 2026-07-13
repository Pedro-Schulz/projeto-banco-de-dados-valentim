package com.app.model;

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
    private String nome;
    private Double gastos;
    private Double retorno;
    private Boolean ativo;

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
