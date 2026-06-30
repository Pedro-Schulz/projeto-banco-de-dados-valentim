package com.app.controller;

import com.app.model.Departamento;
import com.app.model.Funcionario;
import com.app.model.Vaga;
import com.app.repository.DepartamentoRepository;
import com.app.repository.FuncionarioRepository;
import com.app.repository.VagaRepository;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Vaga v = new Vaga("Noturno", "Gerente", 100.0, null);
        Funcionario f = new Funcionario("Pedro", null, "12345643543", "12487398", "emailfunc@gmail.com", "47999123421", "Solteiro", "M", v);
        VagaRepository vf = new VagaRepository();
        FuncionarioRepository ff = new FuncionarioRepository();
        ff.salvar(f);
        System.out.println(v.getIdVaga());
    }
}