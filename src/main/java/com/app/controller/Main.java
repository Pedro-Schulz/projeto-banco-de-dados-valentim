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
        FolhaDePagamentoService folhaDePagamentoService = new FolhaDePagamentoService();
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
                    System.out.println("ID do funcionário a ser desativado: ");
                    id = Integer.parseInt(scanner.nextLine());

                    while(true) {
                        try {
                            funcionarioService.desativar(id);
                            break;
                        } catch(DadosBancariosVinculadosException e) {
                            System.out.println(e.getMessage() + "\nDeseja excluí-los? (S/N) ");

                            if(scanner.nextLine().equalsIgnoreCase("s") || scanner.nextLine().equalsIgnoreCase("sim")) {
                                dadosBancariosService.desativar(id);
                            } else if(scanner.nextLine().equalsIgnoreCase("n") || scanner.nextLine().equalsIgnoreCase("nao")) {
                                System.out.println("Para um funcionário ser desativado, seu grafo também precisa ser desativado!");
                            } else {
                                System.out.println("Comando inválido!");
                            }
                        } catch(ContratoVinculadoException e) {
                            System.out.println(e.getMessage() + "\nDeseja excluí-lo? (S/N) ");

                            if(scanner.nextLine().equalsIgnoreCase("s") || scanner.nextLine().equalsIgnoreCase("sim")) {
                                contratoService.desativar(id);
                            } else if(scanner.nextLine().equalsIgnoreCase("n") || scanner.nextLine().equalsIgnoreCase("nao")) {
                                System.out.println("Para um funcionário ser desativado, seu grafo também precisa ser desativado!");
                            } else {
                                System.out.println("Comando inválido!");
                            }
                        } catch(FolhaDePagamentoVinculadaException e) {
                            System.out.println(e.getMessage() + "\nDeseja excluí-la? (S/N) ");

                            if(scanner.nextLine().equalsIgnoreCase("s") || scanner.nextLine().equalsIgnoreCase("sim")) {
                                folhaDePagamentoService.desativar(id);
                            } else if(scanner.nextLine().equalsIgnoreCase("n") || scanner.nextLine().equalsIgnoreCase("nao")) {
                                System.out.println("Para um funcionário ser desativado, seu grafo também precisa ser desativado!");
                            } else {
                                System.out.println("Comando inválido!");
                            }
                        }
                    }

                    break;

                default:
                    break;
            }
        } while(opcao != 10);
    }
}