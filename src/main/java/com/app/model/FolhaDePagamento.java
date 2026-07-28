package com.app.model;

import java.time.LocalDate;

public class FolhaDePagamento {

    private Long idFolhaDePagamento;
    private int horasTrabalhadas;
    private LocalDate dataEmissao;
    private double descontos;
    private int horasExtras;
    private Funcionario funcionario;
    private boolean ativo;

    public FolhaDePagamento() {
    }

    public FolhaDePagamento(int horasTrabalhadas, LocalDate dataEmissao, double descontos, int horasExtras, Funcionario funcionario, boolean ativo) {
        this.horasTrabalhadas = horasTrabalhadas;
        this.dataEmissao = dataEmissao;
        this.descontos = descontos;
        this.horasExtras = horasExtras;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    public FolhaDePagamento(Long idFolhaDePagamento, int horasTrabalhadas, LocalDate dataEmissao, double descontos, int horasExtras, Funcionario funcionario, boolean ativo) {
        this.idFolhaDePagamento = idFolhaDePagamento;
        this.horasTrabalhadas = horasTrabalhadas;
        this.dataEmissao = dataEmissao;
        this.descontos = descontos;
        this.horasExtras = horasExtras;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    public Long getIdFolhaDePagamento() {
        return idFolhaDePagamento;
    }

    public void setIdFolhaDePagamento(Long idFolhaDePagamento) {
        this.idFolhaDePagamento = idFolhaDePagamento;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public double getDescontos() {
        return descontos;
    }

    public void setDescontos(double descontos) {
        this.descontos = descontos;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}