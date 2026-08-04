package com.app.model;

import jakarta.validation.constraints.*;
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

    @NotNull(message = "O status da candidatura é obrigatório")
    private Boolean statusCandidatura;

    @NotNull(message = "A data da candidatura é obrigatória")
    @PastOrPresent(message = "A data da candidatura não pode ser futura")
    private LocalDate dataCandidatura;

    @NotNull(message = "O prazo é obrigatório")
    @FutureOrPresent(message = "O prazo deve ser hoje ou uma data futura")
    private LocalDate prazo;

    @NotBlank(message = "A etapa é obrigatória")
    @Size(max = 50, message = "A etapa deve possuir no máximo 50 caracteres")
    private String etapa;

    @NotNull(message = "A vaga é obrigatória")
    private Vaga vaga;

    @NotNull(message = "O candidato é obrigatório")
    private Candidato candidato;

    @NotNull(message = "O status de ativo é obrigatório")
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