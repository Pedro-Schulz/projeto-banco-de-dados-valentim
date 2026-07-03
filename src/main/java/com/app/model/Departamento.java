package com.app.model;

public class Departamento {
    private Long idDepartamento;
    private String nome;
    private Double gastos;
    private Double retorno;
    private Boolean ativo;

    public Departamento() {}

    public Departamento(String nome, Double gastos, Double retorno, Boolean ativo) {
        this.nome = nome;
        this.gastos = gastos;
        this.retorno = retorno;
        this.ativo = ativo;
    }

    public Departamento(Long idDepartamento, String nome, Double gastos, Double retorno, Boolean ativo) {
        this.idDepartamento = idDepartamento;
        this.nome = nome;
        this.gastos = gastos;
        this.retorno = retorno;
        this.ativo = ativo;
    }

    public Long getIdDepartamento() { return idDepartamento; }

    public void setIdDepartamento(Long idDepartamento) { this.idDepartamento = idDepartamento; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Double getGastos() { return gastos; }

    public void setGastos(Double gastos) { this.gastos = gastos; }

    public Double getRetorno() { return retorno; }

    public void setRetorno(Double retorno) { this.retorno = retorno; }

    @Override
    public String toString() {
        return "\n> ID: " + idDepartamento +
                "\n> NOME: " + nome +
                "\n> GASTOS: " + gastos +
                "\n> RETORNO FINANCEIRO: " + retorno;
    }
}
