package com.app.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosBancarios {
    private Long idDadosBancarios;

    @NotNull(message = "O número da conta é obrigatório")
    @Positive(message = "O número da conta deve ser maior que zero")
    private Integer numeroConta;

    @NotBlank(message = "A instituição bancária é obrigatória")
    @Size(max = 100, message = "A instituição bancária deve possuir no máximo 100 caracteres")
    private String instituicaoBancaria;

    @NotBlank(message = "A agência bancária é obrigatória")
    @Pattern(
            regexp = "^\\d{4}-?\\d$",
            message = "A agência bancária deve estar no formato 1234 ou 1234-5"
    )
    private String agenciaBancaria;

    @NotNull(message = "O funcionário é obrigatório")
    private Funcionario funcionario;

    @NotNull(message = "O status de ativo é obrigatório")
    private Boolean ativo;

    private Integer version = 1;

    public DadosBancarios(Integer numeroConta, String instituicaoBancaria, String agenciaBancaria, Funcionario funcionario, Boolean ativo) {
        this.numeroConta = numeroConta;
        this.instituicaoBancaria = instituicaoBancaria;
        this.agenciaBancaria = agenciaBancaria;
        this.funcionario = funcionario;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "\n> ID: " + idDadosBancarios +
                "\n> ID FUNCIONÁRIO: " + funcionario.getIdFuncionario() +
                "\n> NÚMERO DA CONTA: " + numeroConta +
                "\n> INSTITUIÇÃO BANCÁRIA: " + instituicaoBancaria +
                "\n> AGÊNCIA BANCÁRIA: " + agenciaBancaria;
    }
}
