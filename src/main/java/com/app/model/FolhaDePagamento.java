package com.app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolhaDePagamento {
    private Long idFolha;

    @NotNull(message = "As horas trabalhadas são obrigatórias")
    @PositiveOrZero(message = "As horas trabalhadas não podem ser negativas")
    private Integer horasTrabalhadas;

    @NotNull(message = "A data de emissão é obrigatória")
    @PastOrPresent(message = "A data de emissão não pode ser futura")
    private LocalDate dataEmissao;

    @NotNull(message = "Os descontos são obrigatórios")
    @PositiveOrZero(message = "Os descontos não podem ser negativos")
    private Double descontos;

    @NotNull(message = "As horas extras são obrigatórias")
    @PositiveOrZero(message = "As horas extras não podem ser negativas")
    private Integer horasExtras;

    @NotNull(message = "O funcionário é obrigatório")
    private Funcionario funcionario;

    @NotNull(message = "O status de ativo é obrigatório")
    private Boolean ativo;

    private Integer version = 1;

    public FolhaDePagamento(Integer horasTrabalhadas, LocalDate dataEmissao, Double descontos, Integer horasExtras, Funcionario funcionario, Boolean ativo) {
        this.horasTrabalhadas = horasTrabalhadas;
        this.dataEmissao = dataEmissao;
        this.descontos = descontos;
        this.horasExtras = horasExtras;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID: " + this.idFolha +
                "\n> ID FUNCIONÁRIO: " + this.funcionario.getIdFuncionario() +
                "\n> HORAS TRABALHADAS: " + this.horasTrabalhadas +
                "\n> DATA DE EMISSÃO: " + this.dataEmissao +
                "\n> DESCONTOS R$ : " + this.descontos +
                "\n> HORAS EXTRAS: R$ " + this.horasExtras;
    }
}