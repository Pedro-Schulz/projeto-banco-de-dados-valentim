package com.app.controller;

import com.app.model.*;

public class Main {
    public static void main(String[] args) {

        Candidato candidato = new Candidato();
        System.out.println(candidato.toString());

        Candidatura candidatura = new Candidatura();
        System.out.println(candidatura.toString());

        Contrato contrato = new Contrato();
        System.out.println(contrato.toString());

        DadosBancarios dadosBancarios = new DadosBancarios();
        System.out.println(dadosBancarios.toString());

        Departamento departamento = new Departamento();
        System.out.println(departamento.toString());

        FolhaDePagamento folhaDePagamento = new FolhaDePagamento();
        System.out.println(folhaDePagamento.toString());

        Funcionario funcionario = new Funcionario();
        System.out.println(funcionario.toString());

        Vaga vaga = new Vaga();
        System.out.println(vaga.toString());
    }
}