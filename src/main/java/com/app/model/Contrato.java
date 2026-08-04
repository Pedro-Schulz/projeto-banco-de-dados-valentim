package com.app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {
    private Long idContrato;

    @NotNull(message = "O status do contrato é obrigatório")
    private Boolean statusContrato;

    @NotNull(message = "A data de emissão é obrigatória")
    @PastOrPresent(message = "A data de emissão não pode ser futura")
    private LocalDate dataEmissao;

    @NotNull(message = "O prazo é obrigatório")
    @Positive(message = "O prazo deve ser maior que zero")
    private Integer prazo;

    @NotNull(message = "O funcionário é obrigatório")
    private Funcionario funcionario;

    @NotNull(message = "O status de ativo é obrigatório")
    private Boolean ativo;

    private Integer version = 1;

    public Contrato(Boolean statusContrato, LocalDate dataEmissao, Integer prazo, Funcionario funcionario, Boolean ativo) {
        this.statusContrato = statusContrato;
        this.dataEmissao = dataEmissao;
        this.prazo = prazo;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID: " + this.idContrato +
                "\n> ID FUNCIONÁRIO: " + this.funcionario.getIdFuncionario() +
                "\n> STATUS DO CONTRATO: " + this.statusContrato +
                "\n> DATA DE EMISSÃO: " + this.dataEmissao +
                "\n> PRAZO: " + this.prazo;
    }
}