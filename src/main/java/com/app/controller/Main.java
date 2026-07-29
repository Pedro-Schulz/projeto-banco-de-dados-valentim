package com.app.controller;

import com.app.model.Funcionario;
import com.app.model.Vaga;
import com.app.repository.UsuarioRepository;
import com.app.service.CandidaturaService;
import com.app.service.DepartamentoService;
import com.app.service.FuncionarioService;
import com.app.service.VagaService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final FuncionarioService funcionarioService = new FuncionarioService();
    private static final VagaService vagaService = new VagaService();
    private static final CandidaturaService candidaturaService = new CandidaturaService();
    private static final DepartamentoService departamentoService = new DepartamentoService();

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("         SISTEMA DE GESTÃO DE RH       ");
        System.out.println("=======================================");

        if (autenticar()) {
            menuPrincipal();
        } else {
            System.out.println("\nEncerrando aplicação. Até logo!");
        }

        scanner.close();
    }

    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();

    private static boolean autenticar() {
        while (true) {
            // MENU DE AUTENTICAÇÃO
            System.out.println("\n=======================================");
            System.out.println("            AUTENTICAÇÃO               ");
            System.out.println("=======================================");
            System.out.println("1. Entrar no Sistema (Login)");
            System.out.println("2. Cadastrar Novo Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcaoInt();

            switch (opcao) {
                case 1:
                    // LOGIN EXISTENTE
                    System.out.println("\n--- LOGIN ---");
                    System.out.print("Digite seu CPF: ");
                    String cpfLogin = scanner.nextLine().trim();
                    System.out.print("Digite sua Senha: ");
                    String senhaLogin = scanner.nextLine().trim();

                    String perfil = usuarioRepository.autenticarEObterPerfil(cpfLogin, senhaLogin);

                    if (perfil != null) {
                        System.out.println("\n Login realizado com sucesso!");

                        if ("ADM".equalsIgnoreCase(perfil)) {
                            System.out.println(">>> Nível de Acesso: ADMINISTRADOR <<<");
                            exibirMenuAdmin();
                        } else {
                            System.out.println(">>> Nível de Acesso: FUNCIONÁRIO <<<");
                            exibirMenuFuncionario(cpfLogin);
                        }
                        return false;
                    } else {
                        System.out.println("\n [ERRO]: CPF/Senha incorretos ou usuário desativado!");
                    }
                    break;

                case 2:
                    // CADASTRO DE NOVO USUÁRIO
                    System.out.println("\n--- CADASTRO DE USUÁRIO ---");
                    System.out.print("Digite o CPF do Funcionário: ");
                    String cpfNovo = scanner.nextLine().trim();

                    if (!usuarioRepository.existeFuncionarioPorCpf(cpfNovo)) {
                        System.out.println("\n [ERRO]: CPF não encontrado na base de funcionários!");
                        break;
                    }

                    System.out.print("Crie uma Senha: ");
                    String senhaNova = scanner.nextLine().trim();

                    System.out.print("Informe o Perfil (ADM / USER) [Padrão: USER]: ");
                    String perfilNovo = scanner.nextLine().trim();

                    if (usuarioRepository.cadastrarUsuario(cpfNovo, senhaNova, perfilNovo)) {
                        System.out.println("\n Usuário cadastrado com sucesso! Faça login para continuar.");
                    } else {
                        System.out.println("\n [ERRO]: Falha ao cadastrar usuário.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    return false;

                default:
                    System.out.println("\n Opção inválida!");
            }
        }
    }

    // --- PAINEL DO ADMINISTRADOR ---
    private static void exibirMenuAdmin() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=======================================");
            System.out.println("       PAINEL DO ADMINISTRADOR         ");
            System.out.println("=======================================");
            System.out.println("1. Gestão de Funcionários");
            System.out.println("2. Gestão de Vagas");
            System.out.println("3. Gestão de Candidaturas");
            System.out.println("4. Cadastrar Novo Usuário no Sistema");
            System.out.println("0. Sair / Logoff");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcaoInt();

            switch (opcao) {
                case 1:
                    menuFuncionarios();
                    break;

                case 2:
                    menuVagas();
                    break;

                case 3:
                    menuCandidaturas();
                    break;

                case 4:
                    cadastrarUsuarioAdmin();
                    break;

                case 0:
                    System.out.println("\nSaindo do Painel Administrativo...");
                    rodando = false;
                    break;

                default:
                    System.out.println("\n [ERRO]: Opção inválida!");
            }
        }
    }

    // --- FUNÇÃO PARA CADASTRAR NOVO USUÁRIO ---
    private static void cadastrarUsuarioAdmin() {
        System.out.println("\n--- CADASTRAR NOVO USUÁRIO ---");
        System.out.print("Digite o CPF do Funcionário: ");
        String cpfNovo = scanner.nextLine().trim();

        if (!usuarioRepository.existeFuncionarioPorCpf(cpfNovo)) {
            System.out.println("\n [ERRO]: CPF não encontrado na base de funcionários!");
            return;
        }

        System.out.print("Crie uma Senha: ");
        String senhaNova = scanner.nextLine().trim();

        System.out.print("Informe o Perfil (ADM / USER) [Padrão: USER]: ");
        String perfilNovo = scanner.nextLine().trim();

        if (usuarioRepository.cadastrarUsuario(cpfNovo, senhaNova, perfilNovo)) {
            System.out.println("\n Usuário cadastrado com sucesso!");
        } else {
            System.out.println("\n [ERRO]: Falha ao cadastrar usuário.");
        }
    }

    private static void exibirMenuFuncionario(String cpf) {
        boolean rodando = true;

        while (rodando) {
            // SUB-MENU DO FUNCIONÁRIO
            System.out.println("\n=== PAINEL DO FUNCIONÁRIO ===");
            System.out.println("1. Visualizar Meus Dados");
            System.out.println("2. Consultar Vagas Abertas");
            System.out.println("0. Sair / Logoff");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcaoInt();

            switch (opcao) {
                case 1:
                    // Exibe os dados do funcionário logado
                    System.out.println("\n--- MEUS DADOS ---");
                    // Busca os dados do funcionário logado pelo CPF
                    Funcionario f = funcionarioService.buscarPorCpf(cpf);
                    if (f != null) {
                        System.out.println("ID: " + f.getIdFuncionario());
                        System.out.println("Nome: " + f.getNome());
                        System.out.println("CPF: " + f.getCpf());
                        System.out.println("E-mail: " + f.getEmail());
                    } else {
                        System.out.println("Não foi possível carregar os dados deste funcionário.");
                    }
                    break;

                case 2:
                    // Consulta e exibe todas as vagas abertas
                    System.out.println("\n--- VAGAS ABERTAS ---");
                    ArrayList<Vaga> vagas = vagaService.listarTodos();
                    if (vagas.isEmpty()) {
                        System.out.println("Nenhuma vaga aberta no momento.");
                    } else {
                        for (Vaga v : vagas) {
                            System.out.println("ID: " + v.getIdVaga() + " | Vaga: " + v.getTituloVaga() + " | Salário: R$ " + v.getSalario());
                        }
                    }
                    break;

                case 0:
                    System.out.println("\nSaindo do painel...");
                    rodando = false;
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }
        }
    }

    private static void menuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            // MENU PRINCIPAL
            System.out.println("\n=======================================");
            System.out.println("            MENU PRINCIPAL             ");
            System.out.println("=======================================");
            System.out.println("1. Gerenciar Funcionários");
            System.out.println("2. Gerenciar Vagas");
            System.out.println("3. Gerenciar Candidaturas");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcaoInt();

            switch (opcao) {
                case 1:
                    menuFuncionarios();
                    break;
                case 2:
                    menuVagas();
                    break;
                case 3:
                    menuCandidaturas();
                    break;
                case 0:
                    System.out.println("\nSaindo do sistema...");
                    break;
                default:
                    System.out.println("\n Opção inválida!");
            }
        }
    }

    // --- 3. SUB-MENU DE FUNCIONÁRIOS ---
    private static void menuFuncionarios() {
        System.out.println("\n--- GESTÃO DE FUNCIONÁRIOS ---");
        System.out.println("1. Listar todos os funcionários");
        System.out.println("2. Buscar funcionário por ID");
        System.out.println("3. Desativar funcionário");
        System.out.println("0. Voltar ao menu anterior");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();

        switch (opcao) {
            case 1:
                System.out.println("\n--- LISTA DE FUNCIONÁRIOS ---");
                ArrayList<Funcionario> lista = funcionarioService.listarTodos();
                for (Funcionario f : lista) {
                    System.out.println("ID: " + f.getIdFuncionario() + " | Nome: " + f.getNome() + " | CPF: " + f.getCpf());
                }
                break;
            case 2:
                System.out.print("Digite o ID do funcionário: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                Funcionario f = funcionarioService.buscarPorId(id);
                if (f != null) {
                    System.out.println("\nEncontrado: " + f.getNome() + " - E-mail: " + f.getEmail());
                } else {
                    System.out.println("\nFuncionário não encontrado.");
                }
                break;
            case 3:
                System.out.print("Digite o ID para desativar: ");
                long idDesativar = scanner.nextLong();
                scanner.nextLine();
                funcionarioService.desativar(idDesativar);
                System.out.println("\nFuncionário desativado com sucesso!");
                break;
            case 0:
                break;
            default:
                System.out.println("\nOpção inválida!");
        }
    }

    // --- 4. SUB-MENU DE VAGAS ---
    private static void menuVagas() {
        System.out.println("\n--- GESTÃO DE VAGAS ---");
        System.out.println("1. Listar todas as vagas");
        System.out.println("2. Desativar vaga por ID");
        System.out.println("0. Voltar ao menu anterior");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();

        switch (opcao) {
            case 1:
                System.out.println("\n--- LISTA DE VAGAS ---");
                ArrayList<Vaga> vagas = vagaService.listarTodos();
                for (Vaga v : vagas) {
                    System.out.println("ID: " + v.getIdVaga() + " | Cargo: " + v.getTituloVaga() + " | Salário: R$ " + v.getSalario());
                }
                break;
            case 2:
                System.out.print("Digite o ID da vaga para desativar: ");
                long idVaga = scanner.nextLong();
                scanner.nextLine();
                vagaService.desativar(idVaga);
                System.out.println("\nVaga desativada com sucesso!");
                break;
            case 0:
                break;
            default:
                System.out.println("\nOpção inválida!");
        }
    }

    // --- 5. SUB-MENU DE CANDIDATURAS ---
    private static void menuCandidaturas() {
        System.out.println("\n--- GESTÃO DE CANDIDATURAS ---");
        System.out.println("1. Desativar candidatura por ID");
        System.out.println("0. Voltar ao menu anterior");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcaoInt();

        switch (opcao) {
            case 1:
                System.out.print("Digite o ID da candidatura para desativar: ");
                long idCandidatura = scanner.nextLong();
                scanner.nextLine();
                candidaturaService.desativar(idCandidatura);
                System.out.println("\nCandidatura desativada com sucesso!");
                break;
            case 0:
                break;
            default:
                System.out.println("\nOpção inválida!");
        }
    }

    private static int lerOpcaoInt() {
        try {
            int val = scanner.nextInt();
            scanner.nextLine();
            return val;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}