package com.app.controller;

import com.app.repository.FuncionarioRepository;
import com.app.model.Funcionario;

public class Main {
    public static void main(String[] args) {

        Funcionario f = FuncionarioRepository.buscarPorId(1L);
        System.out.println(f.toString());
    }
}