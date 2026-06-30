package com.app.controller;

import com.app.model.Funcionario;
import com.app.repository.FuncionarioRepository;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Funcionario f = new Funcionario("Pedro");
        FuncionarioRepository repository = new FuncionarioRepository();
        repository.salvar(f);
    }
}