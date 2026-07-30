package com.app.model;

import java.time.LocalDate;

public class Contrato {

    private Long idContrato;
    private boolean statusContrato;
    private LocalDate dataContrato;
    private LocalDate prazoContrato;
    private Funcionario funcionario;
    private boolean contratoAtivo;
    private int version = 1;

    public Contrato() {
    }

    public Contrato(boolean statusContrato, LocalDate dataContrato, LocalDate prazoContrato, Funcionario funcionario, boolean contratoAtivo) {
        this.statusContrato = statusContrato;
        this.dataContrato = dataContrato;
        this.prazoContrato = prazoContrato;
        this.funcionario = funcionario;
        this.contratoAtivo = contratoAtivo;
    }

    public Contrato(Long idContrato, boolean statusContrato, LocalDate dataContrato, LocalDate prazoContrato, Funcionario funcionario, boolean contratoAtivo, int version) {
        this.idContrato = idContrato;
        this.statusContrato = statusContrato;
        this.dataContrato = dataContrato;
        this.prazoContrato = prazoContrato;
        this.funcionario = funcionario;
        this.contratoAtivo = contratoAtivo;
        this.version = version;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public boolean isStatusContrato() {
        return statusContrato;
    }

    public void setStatusContrato(boolean statusContrato) {
        this.statusContrato = statusContrato;
    }

    public LocalDate getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(LocalDate dataContrato) {
        this.dataContrato = dataContrato;
    }

    public LocalDate getPrazoContrato() {
        return prazoContrato;
    }

    public void setPrazoContrato(LocalDate prazoContrato) {
        this.prazoContrato = prazoContrato;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isContratoAtivo() {
        return contratoAtivo;
    }

    public void setContratoAtivo(boolean contratoAtivo) {
        this.contratoAtivo = contratoAtivo;
    }

    public boolean getAtivo() {
        return contratoAtivo;
    }

    public void setAtivo(boolean ativo) {
        this.contratoAtivo = ativo;
    }

    public boolean getStatusContrato() {
        return statusContrato;
    }

    public LocalDate getDataEmissao() {
        return dataContrato;
    }

    public int getPrazo() {
        if(dataContrato == null || prazoContrato == null) {
            return 0;
        }
        return (int) (prazoContrato.toEpochDay() - dataContrato.toEpochDay());
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int i) {
        this.version = i;
    }
}