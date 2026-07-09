package com.app.model;

import com.mysql.cj.log.Log;

public class DadosBancarios {
    private Long idDadosBancarios;
    private Integer numeroConta;
    private String instituicaoBancaria;
    private String agenciaBancaria;
    private Funcionario funcionario;
    private Boolean ativo;

    public DadosBancarios() {}

    public DadosBancarios(Integer numeroConta, String instituicaoBancaria, String agenciaBancaria, Funcionario funcionario, Boolean ativo) {
        this.numeroConta = numeroConta;
        this.instituicaoBancaria = instituicaoBancaria;
        this.agenciaBancaria = agenciaBancaria;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    public DadosBancarios(Long idDadosBancarios, Integer numeroConta, String instituicaoBancaria, String agenciaBancaria, Funcionario funcionario, Boolean ativo) {
        this.idDadosBancarios = idDadosBancarios;
        this.numeroConta = numeroConta;
        this.instituicaoBancaria = instituicaoBancaria;
        this.agenciaBancaria = agenciaBancaria;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    public Long getIdDadosBancarios() { return idDadosBancarios; }

    public void setIdDadosBancarios(Long idDadosBancarios) { this.idDadosBancarios = idDadosBancarios; }

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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID: " + idDadosBancarios +
                "\n> ID FUNCIONÁRIO: " + funcionario.getIdFuncionario() +
                "\n> NÚMERO DA CONTA: " + numeroConta +
                "\n> INSTITUIÇÃO BANCÁRIA: " + instituicaoBancaria +
                "\n> AGÊNCIA BANCÁRIA: " + agenciaBancaria;
    }
}
