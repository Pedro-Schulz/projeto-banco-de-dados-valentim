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

        Vaga v = new Vaga("Matutino", "Diretor", 120.0, null);

        VagaRepository vf = new VagaRepository();

        vf.salvar(v);
    }
}