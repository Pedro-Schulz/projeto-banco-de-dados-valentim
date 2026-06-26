package com.app.model;

public class Vaga {
    private Integer idVaga;
    private String turno;
    private String cargo;
    private Double salarioHora;
    private Departamento departamento;

    public Vaga() {}

    public Vaga(Integer idVaga, String turno, String cargo, Double salarioHora, Departamento departamento) {
        this.idVaga = idVaga;
        this.turno = turno;
        this.cargo = cargo;
        this.salarioHora = salarioHora;
        this.departamento = departamento;
    }

    public Integer getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Integer idVaga) {
        this.idVaga = idVaga;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(Double salarioHora) {
        this.salarioHora = salarioHora;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "\n> ID: " + idVaga +
                "\n> TURNO: " + turno +
                "\n> CARGO: " + cargo +
                "\n> DEPARTAMENTO: " + departamento +
                "\n> SALÁRIO P/ HORA: " + salarioHora;
    }
}
