package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidatura {
    private Long idCandidatura;
    private Boolean statusCandidatura;
    private LocalDate dataCandidatura;
    private LocalDate prazo;
    private String etapa;
    private Vaga vaga;
    private Candidato candidato;
    private Boolean ativo;
    private Integer version = 1;

    public Candidatura(Boolean statusCandidatura, LocalDate dataCandidatura, LocalDate prazo, String etapa, Vaga vaga, Candidato candidato) {
        this.statusCandidatura = statusCandidatura;
        this.dataCandidatura = dataCandidatura;
        this.prazo = prazo;
        this.etapa = etapa;
        this.vaga = vaga;
        this.candidato = candidato;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID DA CANDIDATURA: " + this.idCandidatura +
                "\n> ID VAGA: " + this.vaga.getIdVaga() +
                "\n> ID CANDIDATO: " + this.candidato.getIdCandidato() +
                "\n> STATUS DA CANDIDATURA: " + this.statusCandidatura +
                "\n> DATA DA CANDIDATURA: " + this.dataCandidatura +
                "\n> PRAZO DA CANDIDATURA: " + this.prazo +
                "\n> ETAPA DA CANDIDATURA: " + this.etapa;
    }
}