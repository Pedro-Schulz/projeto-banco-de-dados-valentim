package com.app.model;

public class DadosBancarios {
    private Integer idDadosBancarios;
    private Integer numeroConta;
    private String instituicaoBancaria;
    private String agenciaBancaria;
    private Funcionario funcionario;

    public DadosBancarios() {}

    public DadosBancarios(Integer numeroConta, String instituicaoBancaria, String agenciaBancaria, Funcionario funcionario) {
        this.numeroConta = numeroConta;
        this.instituicaoBancaria = instituicaoBancaria;
        this.agenciaBancaria = agenciaBancaria;
        this.funcionario = funcionario;
    }

    public Integer getIdDadosBancarios() { return idDadosBancarios; }

    public void setIdDadosBancarios(Integer idDadosBancarios) { this.idDadosBancarios = idDadosBancarios; }

    public Integer getNumeroConta() { return numeroConta; }

    public void setNumeroConta(Integer numeroConta) { this.numeroConta = numeroConta; }

    public String getInstituicaoBancaria() { return instituicaoBancaria; }

    public void setInstituicaoBancaria(String instituicaoBancaria) { this.instituicaoBancaria = instituicaoBancaria; }

    public String getAgenciaBancaria() { return agenciaBancaria; }

    public void setAgenciaBancaria(String agenciaBancaria) { this.agenciaBancaria = agenciaBancaria; }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "\n> ID: " + idDadosBancarios +
                "\n> FUNCIONÁRIO: " + funcionario +
                "\n> NÚMERO DA CONTA: " + numeroConta +
                "\n> INSTITUIÇÃO BANCÁRIA: " + instituicaoBancaria +
                "\n> AGÊNCIA BANCÁRIA: " + agenciaBancaria;
    }
}
