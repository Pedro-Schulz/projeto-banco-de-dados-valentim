package com.app.controller;

import com.app.model.Departamento;
import com.app.model.Funcionario;
import com.app.repository.DepartamentoRepository;
import com.app.repository.FuncionarioRepository;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Departamento d = new Departamento(676767, "abc", 100.0, 100.0);
        DepartamentoRepository r= new DepartamentoRepository();
        r.salvar(d);
    }
}