package com.app.controller;

import com.app.enums.StatusVinculos;
import com.app.exception.*;
import com.app.model.*;
import com.app.repository.*;
import com.app.service.*;

import java.util.ArrayList;
import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();
        DadosBancariosService dadosBancariosService = new DadosBancariosService();
        FolhaDePagamentoService folhaDePagamentoService = new FolhaDePagamentoService();
        ContratoService contratoService = new ContratoService();
        VagaService vagaService = new VagaService();
        DepartamentoService departamentoService = new DepartamentoService();
        CandidatoService candidatoService = new CandidatoService();
        CandidaturaService candidaturaService = new CandidaturaService();
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;
        long id = 0;

        do {
            System.out.println("=============== MENU ===============\n1 - Criar funcionário\n2 - Desativar funcionário\n3 - Cadastrar vaga\n4 - Folha de pagamento\n5 - Departamento\n6 - Dados Bancarios\n7- Criar contrato\n8 - Candidatura\n>>>");
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
                case 3:
                    System.out.println("Informe o turno");
                    String turno = scanner.next();
                    System.out.println("Perfeito agora informe o cargo");
                    String cargo = scanner.next();
                    System.out.println("Certo, informe o salário por hora");
                    double salarioHora = scanner.nextDouble();
                    System.out.println("Agora informe o departamento");
                    String departamentos = scanner.next();
                    System.out.println("Essa vaga está ativa?");
                    boolean ativa = scanner.nextBoolean();
                    Vaga vaga = new Vaga(turno , cargo , salarioHora,  departamentos , ativa);

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
                        funcionarios.forEach(funcionario -> System.out.println(funcionario));
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
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 103:
                    try {
                        ArrayList<Departamento> departamentos = departamentoService.listarTodos();
                        departamentos.forEach(departamento -> System.out.println(departamento));
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 104:
                    try {
                        ArrayList<DadosBancarios> dadosBancariosList = dadosBancariosService.listarTodos();
                        for(DadosBancarios dadosBancarios : dadosBancariosList) {
                            if(dadosBancarios.getFuncionario() == null) {
                                System.out.println("Dados bancários ID:" + dadosBancarios.getIdDadosBancarios() + ", não possui funcionário vinculado!");
                            } else {
                                System.out.println(dadosBancarios);
                            }
                        }
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 105:
                    try {
                        ArrayList<Contrato> contratos = contratoService.listarTodos();
                        for(Contrato contrato : contratos) {
                            if(contrato.getFuncionario() == null) {
                                System.out.println("Contrato ID:" + contrato.getIdContrato() + ", não possui funcionário vinculado!");
                            } else {
                                System.out.println(contrato);
                            }
                        }
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 106:
                    try {
                        ArrayList<Candidato> candidatos = candidatoService.listarTodos();
                        candidatos.forEach(candidato -> System.out.println(candidato));
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 107:
                    try {
                        ArrayList<Candidatura> candidaturas = candidaturaService.listarTodos();
                        for(Candidatura candidatura : candidaturas) {
                            if(candidatura.getVaga() == null) {
                                System.out.println("Candidatura ID:" + candidatura.getIdCandidatura() + ", não possui vaga vinculada!");
                            } else if(candidatura.getCandidato() == null) {
                                System.out.println("Candidatura ID:" + candidatura.getIdCandidatura() + ", não possui candidato vinculado!");
                            } else {
                                System.out.println(candidatura);
                            }
                        }
                        candidaturas.forEach(candidatura -> System.out.println(candidatura));
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    break;

                case 4:
                    System.out.println("Fale as horas trabalhadas");
                    int horasTrabalhadas = scanner.nextInt();
                    System.out.println("Quando foi a data de emissão?");
                    int dataEmissao = scanner.nextInt();
                    System.out.println("Quantos descontos tem?");
                    int descontos = scanner.nextInt();
                    System.out.println("Qual a quantidade de horas-extras?");
                    int horasExtras = scanner.nextInt();
                    System.out.println("Informe o nome do funcionario");
                    String nomeFuncionario = scanner.next();
                    System.out.println("Está ativo?");
                    boolean funicionarioAtivo = scanner.nextBoolean();
            FolhaDePagamento folhadepagamento = new FolhaDePagamento(horasTrabalhadas , dataEmissao , descontos , horasExtras , nomeFuncionario , funicionarioAtivo);
            break;

            case 5:
                System.out.println("Informe o nome do departamento");
                String departamentoNome = scanner.next();
                System.out.println("Informe a quantidade de gastos que ele fez");
                double gastos = scanner.nextDouble();
                System.out.println("Informe o quanto de retorno ele fez");
                double retorno = scanner.nextDouble();
                System.out.println("Ele ainda está ativo?");
                boolean departamentoAtivo = scanner.nextBoolean();
                Departamento departamento = new Departamento(departamentoNome , gastos , retorno , departamentoAtivo);
                break;
                case 6:
                    System.out.println("Informe o número da conta");
                    Integer numeroConta = scanner.nextInt();
                    System.out.println("Informe a instituição bancaria");
                    String instituicaoBancaria = scanner.next();
                    System.out.println("Agora informe a Agencia bancaria");
                    String agenciaBancaria = scanner.next();
                    System.out.println("Informe o nome do funcionario");
                    String nomeFuncionarioBanco = scanner.next();
                    System.out.println("Esse agencia ainda está ativa");
                    Boolean contaAtiva = scanner.nextBoolean();
                    DadosBancarios dadoBanco = new DadosBancarios(numeroConta , instituicaoBancaria , agenciaBancaria , nomeFuncionarioBanco , contaAtiva);
                    break;
                case 7:
                System.out.println("Como está o status do contrato?");
                boolean statusContrato = scanner.nextBoolean();
                System.out.println("Quando foi a data do contrato?");
                int dataContrato = scanner.nextInt();
                    System.out.println("Quando foi o prazo do contrato?");
                    integer prazoContrato = scanner.nextInt();
                    System.out.println("Qual o nome do funcionario?");
                    String nomeFuncionarioContrato = scanner.next();
                    System.out.println("O contrato ainda está ativo");
                    boolean contratoAtivo = scanner.nextBoolean();

                    Contrato contratos = new Contrato(statusContrato , dataContrato , prazoContrato , nomeFuncionarioContrato , contratoAtivo);
                    break;

                case 8:
            System.out.println("Como está o status da candidatura;");
            Boolean statusCandidatura = scanner.nextBoolean();
            System.out.println("Quando será a data da candidatura?");
            int dataCandidatura = scanner.nextInt();
            System.out.println("Quando será o prazo?");
            int prazoCandidatura = scanner.nextInt();
            System.out.println("Qual etapa?");
            String etapa = scanner.next();
            System.out.println("Qual vaga está interessado?");
            String vagaInteresse = scanner.next();
            System.out.println("Qual nome do candidato?");
            String nomeCandidato = scanner.next();
            System.out.println("Ainda está ativo essa candidatura?");
            boolean candidaturaAtiva = scanner.nextBoolean();
            Candidatura candidatura = new Candidatura(statusCandidatura , dataCandidatura , prazoCandidatura , etapa , vagaInteresse , nomeCandidato , candidaturaAtiva);
            

            }
        } while(opcao != 10);
    }
}