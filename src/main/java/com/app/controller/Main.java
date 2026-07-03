package com.app.controller;

import com.app.exception.*;
import com.app.model.*;
import com.app.repository.*;
import com.app.service.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();
        DadosBancariosService dadosBancariosService = new DadosBancariosService();
        ContratoService contratoService = new ContratoService();
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;
        long id = 0;

        do {
            System.out.println("=============== MENU ===============\n1 - Criar funcionário\n2 - Desativar funcionário\n>>>");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    break;

                case 2:
                    System.out.println("ID do funcionários a ser desativado: ");
                    id = Integer.parseInt(scanner.nextLine());

                    try {
                        funcionarioService.desativar(id);
                    } catch(DadosBancariosVinculadosException e) {
                        System.out.println("Deseja excluí-los? (S/N) ");

                        if(scanner.nextLine().equalsIgnoreCase("s") || scanner.nextLine().equalsIgnoreCase("sim")) {
                            dadosBancariosService.desativar(id);
                        } else if(scanner.nextLine().equalsIgnoreCase("n") || scanner.nextLine().equalsIgnoreCase("nao")) {
                            System.out.println("Para um funcionário ser desativado, seu grafo também precisa ser desativado!");
                        } else {
                            System.out.println("Comando inválido!");
                        }
                    } catch(ContratoVinculadoException e) {
                        System.out.println("Deseja excluí-lo? (S/N) ");

                        if(scanner.nextLine().equalsIgnoreCase("s") || scanner.nextLine().equalsIgnoreCase("sim")) {
                            contratoService.desativar(id);
                        } else if(scanner.nextLine().equalsIgnoreCase("n") || scanner.nextLine().equalsIgnoreCase("nao")) {
                            System.out.println("Para um funcionário ser desativado, seu grafo também precisa ser desativado!");
                        } else {
                            System.out.println("Comando inválido!");
                        }
                    }

                    break;

                default:
                    break;
            }
        } while(opcao != 10);
    }
}