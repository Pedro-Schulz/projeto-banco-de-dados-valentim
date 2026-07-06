package com.app.model;

public class Vaga {
    private Long idVaga;
    private String turno;
    private String cargo;
    private Double salarioHora;
    private Departamento departamento;
    private Boolean ativo;

    public Vaga() {}

    public Vaga(String turno, String cargo, Double salarioHora, Departamento departamento) {
        this.turno = turno;
        this.cargo = cargo;
        this.salarioHora = salarioHora;
        this.departamento = departamento;
        this.ativo = true;
    }

    public Vaga(Long idVaga, String turno, String cargo, Double salarioHora, Departamento departamento, Boolean ativo) {
        this.idVaga = idVaga;
        this.turno = turno;
        this.cargo = cargo;
        this.salarioHora = salarioHora;
        this.departamento = departamento;
        this.ativo = ativo;
    }

    public Long getIdVaga() { return idVaga; }

    public void setIdVaga(Long idVaga) { this.idVaga = idVaga; }

    public String getTurno() { return turno; }

    public void setTurno(String turno) { this.turno = turno; }

    public String getCargo() { return cargo; }

    public void setCargo(String cargo) { this.cargo = cargo; }

    public Double getSalarioHora() { return salarioHora; }

    public void setSalarioHora(Double salarioHora) { this.salarioHora = salarioHora; }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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