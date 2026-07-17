package com.app.controller;

import com.app.repository.UsuarioRepository;
import com.app.service.UsuarioService;

import java.util.Scanner;

public class MainTeste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        System.out.println("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite a senha: ");
        String senha = scanner.nextLine();

        if(usuarioService.verificarSenha(senha, cpf)) {
            System.out.println("LOGIN REALIZADO COM SUCESSO!");
        } else {
            System.out.println("CREDENCIAIS INVÁLIDAS!");
        }
    }
}
