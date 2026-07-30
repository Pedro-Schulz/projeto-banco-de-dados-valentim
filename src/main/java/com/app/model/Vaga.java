package com.app.model;

public class Vaga {

    private Long idVaga;
    private String turno;
    private double salarioHora;
    private String cargo;
    private Long idDepartamento;
    private boolean ativo;
    private int version;

    // 1. Construtor Vazio
    public Vaga() {
    }

    // 2. Construtor só com Cargo (útil para consultas simples)
    public Vaga(String cargo) {
        this.cargo = cargo;
    }

    // 3. Construtor só com ID
    public Vaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    // 4. Construtor Completo
    public Vaga(Long idVaga, String turno, double salarioHora, String cargo, Long idDepartamento, boolean ativo, int version) {
        this.idVaga = idVaga;
        this.turno = turno;
        this.salarioHora = salarioHora;
        this.cargo = cargo;
        this.idDepartamento = idDepartamento;
        this.ativo = ativo;
        this.version = version;
    }

    // Getters e Setters Sincronizados com o Banco de Dados

    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        this.salarioHora = salarioHora;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSalario() {
        return "R$ " + String.format("%.2f", salarioHora);
    }
}