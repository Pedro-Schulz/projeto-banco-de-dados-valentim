package com.app.controller;

import com.app.enums.StatusVinculos;
import com.app.exception.*;
import com.app.model.*;
import com.app.repository.*;
import com.app.service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();
        DadosBancariosService dadosBancariosService = new DadosBancariosService();
        FolhaDePagamentoService folhaDePagamentoService = new FolhaDePagamentoService();
        ContratoService contratoService = new ContratoService();
        VagaService vagaService = new VagaService();
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;
        long id = 0;

        do {
            System.out.println("=============== MENU ===============\n1 - Criar funcionário\n2 - Desativar funcionário\n3 - Criar vaga\n>>>");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    break;

                case 2:
                    System.out.println("ID do funcionário a ser desativado: ");
                    id = Integer.parseInt(scanner.nextLine());

                    StatusVinculos status = funcionarioService.desativar(id);

                    if(status == StatusVinculos.SUCESSO) {
                        System.out.println("Funcionário desativado com sucesso!");
                        break;
                    }

                    System.out.println("O funcionário informado possui vínculos, deseja desativá-los também? (S/N) ");
                    String resposta = scanner.nextLine();

                    if(resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")) {
                        dadosBancariosService.desativarPorFuncionario(id);
                        contratoService.desativarPorFuncionario(id);
                        folhaDePagamentoService.desativarPorFuncionario(id);

                        status = funcionarioService.desativar(id);
                        if(status == StatusVinculos.POSSUI_VINCULOS) {
                            System.out.println("A");
                        }
                        System.out.println("Funcionário desativado com sucesso!");
                    } else {
                        System.out.println("Desativação cancelada!");
                    }
                    break;

                case 100:
                    try {
                        ArrayList<Funcionario> funcionarios = funcionarioService.listarTodos();
                        for(Funcionario funcionario : funcionarios) {
                            if(funcionario.getVaga() == null) {
                                System.out.println("Funcionário ID:" + funcionario.getIdFuncionario() + ", não possui vaga vinculada!");
                            } else {
                                System.out.println(funcionario);
                            }
                        }
                        funcionarios.forEach(funcionario -> System.out.println(funcionario.toString()));
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 101:
                    try {
                        ArrayList<Vaga> vagas = vagaService.listarTodos();
                        for(Vaga vaga : vagas) {
                            System.out.println(vaga);
                        }
                    } catch(NullPointerException e) {
                        System.out.println("Vaga com departamento null(inexistente)" + e);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 102:
                    try {
                        ArrayList<FolhaDePagamento> folhasDePagamento = folhaDePagamentoService.listarTodos();
                        for(FolhaDePagamento folhaDePagamento : folhasDePagamento) {
                            if(folhaDePagamento.getFuncionario() == null) {
                                System.out.println("Folha de pagamento ID:" + folhaDePagamento.getIdFolha() + ", não possui funcionário vinculado!");
                            } else {
                                System.out.println(folhaDePagamento);
                            }
                        }
                    } catch(NullPointerException e) {
                        System.out.println("Folha de pagamento com funcionário null(inexistente)" + e);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    break;
            }
        } while(opcao != 10);
    }
}