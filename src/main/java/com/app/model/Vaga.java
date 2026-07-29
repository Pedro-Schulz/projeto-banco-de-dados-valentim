package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {
    private Long idVaga;
    private String turno;
    private String cargo;
    private Double salarioHora;
    private Departamento departamento;
    private Boolean ativo;
    private Integer version = 1;

    public Vaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Vaga(String cargo) {
        this.cargo = cargo;
    }

    public Vaga(String turno, String cargo, Double salarioHora, Departamento departamento, Boolean ativo) {
        this.turno = turno;
        this.cargo = cargo;
        this.salarioHora = salarioHora;
        this.departamento = departamento;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID: " + idVaga +
                "\n> TURNO: " + turno +
                "\n> CARGO: " + cargo +
                "\n> ID DEPARTAMENTO: " + departamento.getIdDepartamento() +
                "\n> SALÁRIO P/ HORA: " + salarioHora;
    }
}