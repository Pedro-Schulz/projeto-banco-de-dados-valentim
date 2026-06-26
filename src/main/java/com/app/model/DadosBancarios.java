package com.app.model;

public class DadosBancarios {
    private Integer idDadosBancarios;
    private Integer numeroConta;
    private String instituicaoBancaria;
    private String agenciaBancaria;

    public DadosBancarios() {}

    public DadosBancarios(Integer idDadosBancarios, Integer numeroConta, String instituicaoBancaria, String agenciaBancaria) {
        this.idDadosBancarios = idDadosBancarios;
        this.numeroConta = numeroConta;
        this.instituicaoBancaria = instituicaoBancaria;
        this.agenciaBancaria = agenciaBancaria;
    }

    public Integer getIdDadosBancarios() { return idDadosBancarios; }

    public void setIdDadosBancarios(Integer idDadosBancarios) { this.idDadosBancarios = idDadosBancarios; }

    public Integer getNumeroConta() { return numeroConta; }

    public void setNumeroConta(Integer numeroConta) { this.numeroConta = numeroConta; }

    public String getInstituicaoBancaria() { return instituicaoBancaria; }

    public void setInstituicaoBancaria(String instituicaoBancaria) { this.instituicaoBancaria = instituicaoBancaria; }

    public String getAgenciaBancaria() { return agenciaBancaria; }

    public void setAgenciaBancaria(String agenciaBancaria) { this.agenciaBancaria = agenciaBancaria; }

    @Override
    public String toString() {
        return "\n> ID: " + idDadosBancarios +
                "\n> NÚMERO DA CONTA: " + numeroConta +
                "\n> INSTITUIÇÃO BANCÁRIA: " + instituicaoBancaria +
                "\n> AGÊNCIA BANCÁRIA: " + agenciaBancaria;
    }
}
