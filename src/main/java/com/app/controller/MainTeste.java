package com.app.controller;

import com.app.model.FolhaDePagamento;
import com.app.repository.FolhaDePagamentoRepository;
import com.app.repository.UsuarioRepository;
import com.app.service.UsuarioService;

import java.util.Scanner;

public class MainTeste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FolhaDePagamentoRepository folhaDePagamentoRepository = new FolhaDePagamentoRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        /*System.out.println("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite a senha: ");
        String senha = scanner.nextLine();

        if(usuarioService.verificarSenha(senha, cpf)) {
            System.out.println("LOGIN REALIZADO COM SUCESSO!");
        } else {
            System.out.println("CREDENCIAIS INVÁLIDAS!");
        }*/

        FolhaDePagamento f1 = folhaDePagamentoRepository.buscarPorId(5L);
        FolhaDePagamento f2 = folhaDePagamentoRepository.buscarPorId(5L);

        f1.setHorasTrabalhadas(300);
        folhaDePagamentoRepository.atualizar(f1);

        f2.setHorasExtras(30);
        folhaDePagamentoRepository.atualizar(f2);
    }
}
