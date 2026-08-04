package com.app.controller;

import com.app.enums.Perfis;
import com.app.enums.StatusVinculos;
import com.app.exception.ControllerException;
import com.app.model.Usuario;
import com.app.service.UsuarioService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import com.app.model.*;
import com.app.service.*;

public class MainTerminal {
    private static final Scanner scanner = new Scanner(System.in);

    private static final UsuarioService usuarioService = new UsuarioService();
    private static final FuncionarioService funcionarioService = new FuncionarioService();
    private static final CandidaturaService candidaturaService = new CandidaturaService();
    private static final CandidatoService candidatoService = new CandidatoService();
    private static final VagaService vagaService = new VagaService();
    private static final DepartamentoService departamentoService = new DepartamentoService();
    private static final ContratoService contratoService = new ContratoService();
    private static final DadosBancariosService dadosBancariosService = new DadosBancariosService();
    private static final FolhaDePagamentoService folhaDePagamentoService = new FolhaDePagamentoService();

    private static Usuario usuarioLogado;


    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=================================");
            System.out.println("          SISTEMA DE RH");
            System.out.println("=================================");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> realizarLogin();
                    case 2 -> realizarCadastro();
                    case 0 -> {
                        System.out.println("Sistema encerrado.");
                        return;
                    }
                    default ->
                            System.out.println("Opção inválida!");

                }
            } catch (Exception e) {
                System.out.println("Entrada inválida!");
            }
        }
    }

    private static void realizarLogin() {
        System.out.println("\n========== LOGIN ==========");

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            Usuario usuario = usuarioService.buscarPorCpf(cpf);
            if (usuario == null) {
                System.out.println("Usuário não encontrado!");
                return;
            }

            if (!usuarioService.verificarSenha(senha, cpf)) {
                System.out.println("Senha incorreta!");
                return;
            }

            usuarioLogado = usuario;

            System.out.println("\nLogin realizado com sucesso!");
            System.out.println("Perfil: " + usuario.getPerfil());

            abrirMenuPorPerfil();

        } catch (Exception e) {
            System.out.println("Erro ao realizar login!");
            e.printStackTrace();
        }
    }



    private static void realizarCadastro() {
        System.out.println("\n========== CADASTRO ==========");

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            usuarioService.criarUsuario(cpf, senha);
            System.out.println("Usuário criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário!");
        }
    }

    private static void abrirMenuPorPerfil() {
        if (usuarioLogado == null) {
            return;
        }

        Perfis perfil = usuarioLogado.getPerfil();
        switch (perfil) {
            case ADMIN -> menuAdmin();
            case USER -> menuUser();
            case VIEWER -> menuViewer();
        }
    }

    private static void menuAdmin() {
        while (true) {
            System.out.println("\n=================================");
            System.out.println("            MENU ADMIN");
            System.out.println("=================================");

            System.out.println("1 - Gerenciar Candidatos");
            System.out.println("2 - Gerenciar Funcionários");
            System.out.println("3 - Gerenciar Vagas");
            System.out.println("4 - Gerenciar Departamentos");
            System.out.println("5 - Gerenciar Candidaturas");
            System.out.println("6 - Gerenciar Contratos");
            System.out.println("7 - Gerenciar Folhas");
            System.out.println("8 - Gerenciar Dados Bancários");
            System.out.println("0 - Logout");

            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> menuCandidatos();
                case 2 -> menuFuncionarios();
                case 3 -> menuVagas();
                case 4 -> menuDepartamentos();
                case 5 -> menuCandidaturas();
                case 6 -> menuContratos();
                case 7 -> menuFolhasPagamento();
                case 8 -> menuDadosBancarios();
                case 0 -> {
                    usuarioLogado = null;
                    return;
                }
                default ->
                        System.out.println("Opção inválida!");

            }
        }
    }

    private static void menuUser() {
        while (true) {
            System.out.println("\n=================================");
            System.out.println("             MENU USER");
            System.out.println("=================================");

            System.out.println("1 - Consultar candidatos");
            System.out.println("2 - Consultar funcionários");
            System.out.println("3 - Consultar vagas");
            System.out.println("4 - Consultar candidaturas");
            System.out.println("0 - Logout");

            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> listarCandidatos();
                case 2 -> listarFuncionarios();
                case 3 -> listarVagas();
                case 4 -> listarCandidaturas();
                case 0 -> {
                    usuarioLogado = null;
                    return;
                }
                default ->
                        System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuViewer() {
        while (true) {
            System.out.println("\n=================================");
            System.out.println("            MENU VIEWER");
            System.out.println("=================================");

            System.out.println("1 - Visualizar candidatos");
            System.out.println("2 - Visualizar funcionários");
            System.out.println("3 - Visualizar vagas");
            System.out.println("4 - Visualizar departamentos");
            System.out.println("5 - Visualizar contratos");
            System.out.println("6 - Visualizar folhas");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> listarCandidatos();
                case 2 -> listarFuncionarios();
                case 3 -> listarVagas();
                case 4 -> listarDepartamentos();
                case 5 -> listarContratos();
                case 6 -> listarFolhasPagamento();
                case 0 -> {
                    usuarioLogado = null;
                    return;
                }
                default ->
                        System.out.println("Opção inválida!");

            }
        }
    }

    private static void menuCandidatos() {
        while(true) {
            System.out.println("\n====== CANDIDATOS ======");
            System.out.println("1 - Cadastrar candidato");
            System.out.println("2 - Listar candidatos");
            System.out.println("3 - Buscar candidato");
            System.out.println("4 - Excluir candidato");
            System.out.println("0 - Voltar");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch(opcao) {
                case 1 -> {
                    try {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();

                        System.out.print("CEP: ");
                        String cep = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();

                        System.out.print("Gênero: ");
                        String genero = scanner.nextLine();

                        System.out.print("Estado civil: ");
                        String estadoCivil = scanner.nextLine();

                        System.out.print("Data de nascimento (AAAA-MM-DD): ");
                        LocalDate nascimento = LocalDate.parse(scanner.nextLine());

                        Candidato candidato = new Candidato(
                                nome,
                                cpf,
                                cep,
                                email,
                                telefone,
                                genero,
                                estadoCivil,
                                nascimento,
                                true
                        );

                        candidatoService.salvar(candidato);

                        System.out.println("Candidato cadastrado com sucesso!");

                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ControllerException("Erro ao cadastrar candidato");
                    }
                }
                case 2 -> listarCandidatos();
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    Candidato candidato = candidatoService.buscarPorId(id);
                    if(candidato != null)
                        System.out.println(candidato);
                    else
                        System.out.println("Não encontrado!");
                }
                case 4 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    StatusVinculos status = candidatoService.desativar(id);

                    if(status == StatusVinculos.SUCESSO)
                        System.out.println("Candidato removido!");
                    else
                        System.out.println(
                                "Não foi possível remover: possui vínculos.");
                }
                case 0 -> {
                    return;
                }
                default ->
                        System.out.println("Opção inválida!");

            }
        }
    }

    private static void listarCandidatos() {
        ArrayList<Candidato> lista = candidatoService.listarTodos();

        System.out.println("\n===== CANDIDATOS =====");

        if(lista.isEmpty()) {
            System.out.println("Nenhum candidato encontrado.");
            return;
        }
        lista.forEach(e -> System.out.println(e));
    }


    private static void menuFuncionarios() {
        while(true) {
            System.out.println("\n====== FUNCIONÁRIOS ======");
            System.out.println("1 - Cadastrar funcionários");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Buscar funcionário");
            System.out.println("4 - Excluir funcionário");
            System.out.println("0 - Voltar");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch(opcao) {
                case 1 -> {
                    try {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Data nascimento (AAAA-MM-DD): ");
                        LocalDate nascimento = LocalDate.parse(scanner.nextLine());

                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();

                        System.out.print("CEP: ");
                        String cep = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();

                        System.out.print("Estado civil: ");
                        String estadoCivil = scanner.nextLine();

                        System.out.print("Gênero: ");
                        String genero = scanner.nextLine();

                        System.out.print("ID da vaga: ");
                        Long idVaga = Long.parseLong(scanner.nextLine());

                        Vaga vaga = vagaService.buscarPorId(idVaga);

                        if (vaga == null) {
                            System.out.println("Vaga não encontrada.");
                            return;
                        }

                        Funcionario funcionario = new Funcionario(
                                nome,
                                nascimento,
                                cpf,
                                cep,
                                email,
                                telefone,
                                estadoCivil,
                                genero,
                                vaga,
                                true
                        );

                        funcionarioService.salvar(funcionario);
                        System.out.println("Funcionário cadastrado!");

                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ControllerException("Erro ao cadastrar funcionário!");
                    }
                }
                case 2 -> listarFuncionarios();
                case 3 -> {
                    System.out.print("ID: ");

                    Long id = Long.parseLong(scanner.nextLine());
                    Funcionario funcionario = funcionarioService.buscarPorId(id);

                    if(funcionario != null)
                        System.out.println(funcionario);
                    else
                        System.out.println("Não encontrado!");
                }

                case 4 -> {
                    System.out.print("ID: ");

                    Long id = Long.parseLong(scanner.nextLine());

                    StatusVinculos status = funcionarioService.desativar(id);

                    if(status == StatusVinculos.SUCESSO)
                        System.out.println("Funcionário desativado!");
                    else
                        System.out.println("Funcionário possui vínculos!");
                }
                case 0 -> {
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void listarFuncionarios() {
        ArrayList<Funcionario> lista = funcionarioService.listarTodos();

        System.out.println("\n===== FUNCIONÁRIOS =====");

        if(lista.isEmpty()) {
            System.out.println("Nenhum funcionário.");
            return;
        }

        lista.forEach(System.out::println);
    }

    private static void menuVagas() {
        while(true) {
            System.out.println("\n====== VAGAS ======");
            System.out.println("1 - Cadastrar vagas");
            System.out.println("2 - Listar vagas");
            System.out.println("3 - Buscar vaga");
            System.out.println("4 - Excluir vaga");
            System.out.println("0 - Voltar");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch(opcao) {
                case 1 -> {
                    try {

                        System.out.print("Turno: ");
                        String turno = scanner.nextLine();

                        System.out.print("Cargo: ");
                        String cargo = scanner.nextLine();

                        System.out.print("Salário por hora: ");
                        Double salario = Double.parseDouble(scanner.nextLine());

                        System.out.print("ID do departamento: ");
                        Long idDepartamento = Long.parseLong(scanner.nextLine());

                        Departamento departamento =
                                departamentoService.buscarPorId(idDepartamento);

                        if (departamento == null) {
                            System.out.println("Departamento não encontrado.");
                            return;
                        }

                        Vaga vaga = new Vaga(
                                turno,
                                cargo,
                                salario,
                                departamento,
                                true
                        );

                        vagaService.salvar(vaga);

                        System.out.println("Vaga cadastrada!");

                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ControllerException("Erro ao cadastrar vaga!");
                    }
                }
                case 2 -> listarVagas();
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    Vaga vaga = vagaService.buscarPorId(id);

                    if(vaga != null) {
                        System.out.println(vaga);
                    } else {
                        System.out.println("Vaga não encontrada!");
                    }
                }
                case 4 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    StatusVinculos status = vagaService.desativar(id);
                    if(status == StatusVinculos.SUCESSO)
                        System.out.println("Vaga removida!");
                    else
                        System.out.println("Vaga possui vínculos!");
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void listarVagas() {
        ArrayList<Vaga> lista = vagaService.listarTodos();

        System.out.println("\n===== VAGAS =====");

        if(lista.isEmpty()) {
            System.out.println("Nenhuma vaga.");
            return;
        }
        lista.forEach(e -> System.out.println(e));
    }

    private static void menuDepartamentos() {
        while(true) {
            System.out.println("\n====== DEPARTAMENTOS ======");
            System.out.println("1 - Cadastrar departamentos");
            System.out.println("2 - Listar departamentos");
            System.out.println("3 - Buscar departamento");
            System.out.println("4 - Excluir departamento");
            System.out.println("0 - Voltar");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch(opcao) {
                case 1 -> {
                    try {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Gastos: ");
                        Double gastos = Double.parseDouble(scanner.nextLine());

                        System.out.print("Retorno: ");
                        Double retorno = Double.parseDouble(scanner.nextLine());

                        Departamento departamento =
                                new Departamento(nome, gastos, retorno, true);

                        departamentoService.salvar(departamento);

                        System.out.println("Departamento cadastrado!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ControllerException("Erro ao cadastrar departamento!");
                    }
                }
                case 2 -> listarDepartamentos();

                case 3 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    Departamento departamento = departamentoService.buscarPorId(id);

                    if(departamento != null) {
                        System.out.println(departamento);
                    } else {
                        System.out.println("Departamento não encontrado!");
                    }
                }

                case 4 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    StatusVinculos status = departamentoService.desativar(id);

                    if(status == StatusVinculos.SUCESSO)
                        System.out.println("Departamento removido!");
                    else
                        System.out.println("Departamento possui vínculos!");
                }

                case 0 -> {
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }


    private static void listarDepartamentos() {
        ArrayList<Departamento> lista = departamentoService.listarTodos();

        System.out.println("\n===== DEPARTAMENTOS =====");

        if(lista.isEmpty()) {
            System.out.println("Nenhum departamento encontrado.");
            return;
        }

        lista.forEach(e -> System.out.println(e));
    }

    private static void menuCandidaturas() {
        while(true) {
            System.out.println("\n====== CANDIDATURAS ======");
            System.out.println("1 - Listar candidaturas");
            System.out.println("2 - Buscar candidatura");
            System.out.println("3 - Excluir candidatura");
            System.out.println("0 - Voltar");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch(opcao) {

                case 1 -> listarCandidaturas();

                case 2 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    Candidatura candidatura = candidaturaService.buscarPorId(id);

                    if(candidatura != null) {
                        System.out.println(candidatura);
                    } else {
                        System.out.println("Candidatura não encontrada!");
                    }
                }

                case 3 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    candidaturaService.desativar(id);

                    System.out.println("Candidatura removida!");
                }

                case 0 -> {
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }


    private static void listarCandidaturas() {
        ArrayList<Candidatura> lista = candidaturaService.listarTodos();

        System.out.println("\n===== CANDIDATURAS =====");

        if(lista.isEmpty()) {
            System.out.println("Nenhuma candidatura encontrada.");
            return;
        }

        lista.forEach(e -> System.out.println(e));
    }

    private static void menuContratos() {
        while(true) {
            System.out.println("\n====== CONTRATOS ======");
            System.out.println("1 - Listar contratos");
            System.out.println("2 - Buscar contrato");
            System.out.println("3 - Excluir contrato");
            System.out.println("0 - Voltar");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch(opcao) {

                case 1 -> listarContratos();

                case 2 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    Contrato contrato = contratoService.buscarPorId(id);

                    if(contrato != null) {
                        System.out.println(contrato);
                    } else {
                        System.out.println("Contrato não encontrado!");
                    }
                }

                case 3 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    contratoService.desativar(id);

                    System.out.println("Contrato removido!");
                }

                case 0 -> {
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }


    private static void listarContratos() {
        ArrayList<Contrato> lista = contratoService.listarTodos();

        System.out.println("\n===== CONTRATOS =====");

        if(lista.isEmpty()) {
            System.out.println("Nenhum contrato encontrado.");
            return;
        }

        lista.forEach(e -> System.out.println(e));
    }

    private static void menuDadosBancarios() {
        while(true) {
            System.out.println("\n====== DADOS BANCÁRIOS ======");
            System.out.println("1 - Listar dados bancários");
            System.out.println("2 - Buscar dados bancários");
            System.out.println("3 - Excluir dados bancários");
            System.out.println("0 - Voltar");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch(opcao) {

                case 1 -> listarDadosBancarios();

                case 2 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    DadosBancarios dados = dadosBancariosService.buscarPorId(id);

                    if(dados != null) {
                        System.out.println(dados);
                    } else {
                        System.out.println("Dados bancários não encontrados!");
                    }
                }

                case 3 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    dadosBancariosService.desativar(id);

                    System.out.println("Dados bancários removidos!");
                }

                case 0 -> {
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }


    private static void listarDadosBancarios() {
        ArrayList<DadosBancarios> lista = dadosBancariosService.listarTodos();

        System.out.println("\n===== DADOS BANCÁRIOS =====");

        if(lista.isEmpty()) {
            System.out.println("Nenhum dado bancário encontrado.");
            return;
        }

        lista.forEach(e -> System.out.println(e));
    }

    private static void menuFolhasPagamento() {
        while(true) {
            System.out.println("\n====== FOLHAS DE PAGAMENTO ======");
            System.out.println("1 - Listar folhas de pagamento");
            System.out.println("2 - Buscar folha de pagamento");
            System.out.println("3 - Excluir folha de pagamento");
            System.out.println("0 - Voltar");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch(opcao) {

                case 1 -> listarFolhasPagamento();

                case 2 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    FolhaDePagamento folha = folhaDePagamentoService.buscarPorId(id);

                    if(folha != null) {
                        System.out.println(folha);
                    } else {
                        System.out.println("Folha de pagamento não encontrada!");
                    }
                }

                case 3 -> {
                    System.out.print("ID: ");
                    Long id = Long.parseLong(scanner.nextLine());

                    folhaDePagamentoService.desativar(id);

                    System.out.println("Folha de pagamento removida!");
                }

                case 0 -> {
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }


    private static void listarFolhasPagamento() {
        ArrayList<FolhaDePagamento> lista = folhaDePagamentoService.listarTodos();

        System.out.println("\n===== FOLHAS DE PAGAMENTO =====");

        if(lista.isEmpty()) {
            System.out.println("Nenhuma folha de pagamento encontrada.");
            return;
        }

        lista.forEach(e -> System.out.println(e));
    }
}
