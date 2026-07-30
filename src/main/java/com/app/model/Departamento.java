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
    private Integer version = 1;

    // Construtor utilitário para busca/vínculo por ID
    public Departamento(Long idDepartamento, String nome, double gastos, double retorno, boolean ativo, int version) {
        this.idDepartamento = idDepartamento;
        this.nome = nome;
        this.gastos = gastos;
        this.retorno = retorno;
        this.ativo = ativo;
        this.version = version;
    }

    // Construtor para cadastro (sem id nem version no parâmetro)
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

    public String getNome() {
        return nome;
    }

    public double getGastos() {
        return gastos;
    }

    public double getRetorno() {
        return retorno;
    }
    
    public int getVersion() {
        return version;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setIdDepartamento(Long id) {
        this.idDepartamento = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGastos(Double gastos) {
        this.gastos = gastos;
    }

    public void setRetorno(Double retorno) {
        this.retorno = retorno;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
