package com.app.model;

public class DadosBancarios {

    private Long idDadosBancarios;
    private Integer numeroConta;
    private String instituicaoBancaria;
    private String agenciaBancaria;
    private Funcionario funcionario;
    private Boolean contaAtiva;
    private int version = 1;

    public DadosBancarios() {
    }

    public DadosBancarios(Integer numeroConta, String instituicaoBancaria, String agenciaBancaria, Funcionario funcionario, Boolean contaAtiva) {
        this.numeroConta = numeroConta;
        this.instituicaoBancaria = instituicaoBancaria;
        this.agenciaBancaria = agenciaBancaria;
        this.funcionario = funcionario;
        this.contaAtiva = contaAtiva;
    }

    public DadosBancarios(Long idDadosBancarios, Integer numeroConta, String instituicaoBancaria, String agenciaBancaria, Funcionario funcionario, Boolean contaAtiva) {
        this.idDadosBancarios = idDadosBancarios;
        this.numeroConta = numeroConta;
        this.instituicaoBancaria = instituicaoBancaria;
        this.agenciaBancaria = agenciaBancaria;
        this.funcionario = funcionario;
        this.contaAtiva = contaAtiva;
    }

    // Construtor com version, usado ao carregar o registro já existente do banco
    public DadosBancarios(Long idDadosBancarios, Integer numeroConta, String instituicaoBancaria, String agenciaBancaria, Funcionario funcionario, Boolean contaAtiva, int version) {
        this.idDadosBancarios = idDadosBancarios;
        this.numeroConta = numeroConta;
        this.instituicaoBancaria = instituicaoBancaria;
        this.agenciaBancaria = agenciaBancaria;
        this.funcionario = funcionario;
        this.contaAtiva = contaAtiva;
        this.version = version;
    }

    public Long getIdDadosBancarios() {
        return idDadosBancarios;
    }

    public void setIdDadosBancarios(Long idDadosBancarios) {
        this.idDadosBancarios = idDadosBancarios;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getInstituicaoBancaria() {
        return instituicaoBancaria;
    }

    public void setInstituicaoBancaria(String instituicaoBancaria) {
        this.instituicaoBancaria = instituicaoBancaria;
    }

    public String getAgenciaBancaria() {
        return agenciaBancaria;
    }

    public void setAgenciaBancaria(String agenciaBancaria) {
        this.agenciaBancaria = agenciaBancaria;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Boolean getContaAtiva() {
        return contaAtiva;
    }

    public void setContaAtiva(Boolean contaAtiva) {
        this.contaAtiva = contaAtiva;
    }

    public Boolean getAtivo() {
        return contaAtiva;
    }

    public void setAtivo(Boolean ativo) {
        this.contaAtiva = ativo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int i) {
        this.version = i;
    }
}