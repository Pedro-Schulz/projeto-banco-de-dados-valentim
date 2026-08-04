package com.app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O turno é obrigatório")
    @Size(max = 30, message = "O turno deve possuir no máximo 30 caracteres")
    private String turno;

    @NotBlank(message = "O cargo é obrigatório")
    @Size(max = 100, message = "O cargo deve possuir no máximo 100 caracteres")
    private String cargo;

    @NotNull(message = "O salário por hora é obrigatório")
    @Positive(message = "O salário por hora deve ser maior que zero")
    private Double salarioHora;

    @NotNull(message = "O departamento é obrigatório")
    private Departamento departamento;

    @NotNull(message = "O status de ativo é obrigatório")
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