package com.app.controller;

import com.app.model.Candidatura;
import com.app.model.Funcionario;
import com.app.model.Vaga;
import com.app.repository.CandidaturaRepository;
import com.app.service.FuncionarioService;
import com.app.service.VagaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Ferramentas auxiliares para leitura de teclado, datas e serviços do sistema
    private static final Scanner scanner = new Scanner(System.in);
    private static final FuncionarioService funcionarioService = new FuncionarioService();
    private static final VagaService vagaService = new VagaService();
    private static final CandidaturaRepository candidaturaRepository = new CandidaturaRepository();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        // Ponto de entrada: inicia o fluxo pela tela de autenticação
        autenticar();
    }

    // =========================================================================
    // TELA DE AUTENTICAÇÃO (LOGIN / CADASTRO / SAIR)
    // =========================================================================
    private static void autenticar() {
        while (true) {
            System.out.println("\n=======================================");
            System.out.println("            AUTENTICAÇÃO               ");
            System.out.println("=======================================");
            System.out.println("1. Entrar no Sistema (Login)");
            System.out.println("2. Cadastrar Novo Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> realizarLogin();
                    case 2 -> cadastrarFuncionario();
                    case 0 -> {
                        System.out.println("\nEncerrando a aplicação... Até mais!");
                        return;
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Ops, ocorreu um erro de digitação: " + e.getMessage());
            }
        }
    }

    // Processo de Login via CPF + Senha (suporta BCrypt e Texto Puro)
    private static void realizarLogin() {
        System.out.println("\n--- TELA DE LOGIN ---");
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite sua Senha: ");
        String senhaDigitada = scanner.nextLine();

        // 1. Busca o funcionário pelo CPF
        Funcionario f = funcionarioService.buscarPorCpf(cpf);

        // 2. Valida se encontrou e se a conta está ativa
        if (f != null && Boolean.TRUE.equals(f.getAtivo())) {

            // 3. Validação da Senha (BCrypt ou Texto Puro)
            boolean senhaValida = validarSenha(senhaDigitada, f.getSenha());

            if (senhaValida) {
                System.out.println("\n Login realizado com sucesso! Bem-vindo(a), " + f.getNome() + "!");

                // 4. Redirecionamento com base na coluna "perfil" (ADM ou USER)
                if ("ADM".equalsIgnoreCase(f.getPerfil())) {
                    exibirMenuAdmin();
                } else {
                    exibirMenuFuncionario(f);
                }
            } else {
                System.out.println("\n Senha incorreta. Tente novamente!");
            }

        } else {
            System.out.println("\n CPF não encontrado ou conta inativa. Tente novamente!");
        }
    }

    // Auxiliar para checar a senha digitada contra a senha do banco (criptografada ou não)
    private static boolean validarSenha(String senhaDigitada, String senhaBanco) {
        if (senhaBanco == null) return false;

        // Se a senha no banco for um Hash de BCrypt (começa com "$2a$")
        if (senhaBanco.startsWith("$2a$")) {
            try {
                // Opção 1: org.mindrot.jbcrypt.BCrypt
                // return org.mindrot.jbcrypt.BCrypt.checkpw(senhaDigitada, senhaBanco);

                // Opção 2: Spring Security Crypto
                // return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().matches(senhaDigitada, senhaBanco);

                // Retorno temporário para caso as dependências acima não estejam importadas:
                return false;
            } catch (Exception e) {
                return false;
            }
        }

        // Se a senha estiver em texto puro (ex: "123" ou "teste1234")
        return senhaDigitada.equals(senhaBanco);
    }

    // =========================================================================
    // PAINEL DO FUNCIONÁRIO (Visão comum)
    // =========================================================================
    public static void exibirMenuFuncionario(Funcionario funcionario) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=================================");
            System.out.println("   PAINEL DO FUNCIONÁRIO");
            System.out.println("   Olá, " + funcionario.getNome());
            System.out.println("=================================");
            System.out.println("1. Ver Minhas Informações");
            System.out.println("2. Ver Vagas Disponíveis");
            System.out.println("0. Deslogar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> System.out.println("\nID: " + funcionario.getIdFuncionario() + " | Nome: " + funcionario.getNome() + " | E-mail: " + funcionario.getEmail() + " | CPF: " + funcionario.getCpf());
                    case 2 -> listarVagas(true);
                    case 0 -> System.out.println("Deslogando do sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // =========================================================================
    // PAINEL ADMINISTRATIVO
    // =========================================================================
    public static void exibirMenuAdmin() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=================================");
            System.out.println("   PAINEL ADMINISTRATIVO");
            System.out.println("=================================");
            System.out.println("1. Gestão de Funcionários");
            System.out.println("2. Gestão de Vagas");
            System.out.println("3. Gestão de Candidaturas");
            System.out.println("0. Deslogar (Voltar ao Login)");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> menuFuncionarios();
                    case 2 -> menuVagas();
                    case 3 -> menuCandidaturas();
                    case 0 -> System.out.println("Deslogando da conta do Administrador...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // =========================================================================
    // 1. GESTÃO DE FUNCIONÁRIOS (Menu + Funções)
    // =========================================================================
    private static void menuFuncionarios() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE FUNCIONÁRIOS ---");
            System.out.println("1. Cadastrar novo Funcionário");
            System.out.println("2. Listar todos os Funcionários");
            System.out.println("3. Buscar Funcionário por ID");
            System.out.println("4. Buscar Funcionário por CPF");
            System.out.println("5. Buscar Funcionário por E-mail");
            System.out.println("6. Atualizar Funcionário");
            System.out.println("7. Desativar Funcionário");
            System.out.println("8. Ativar Funcionário");
            System.out.println("9. Desativar Funcionários por Vaga");
            System.out.println("10. Verificar vínculo do Funcionário com Vaga");
            System.out.println("0. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> cadastrarFuncionario();
                    case 2 -> listarFuncionarios();
                    case 3 -> buscarFuncionarioPorId();
                    case 4 -> buscarFuncionarioPorCpf();
                    case 5 -> buscarFuncionarioPorEmail();
                    case 6 -> atualizarFuncionario();
                    case 7 -> desativarFuncionario();
                    case 8 -> ativarFuncionario();
                    case 9 -> desativarFuncionariosPorVaga();
                    case 10 -> verificarVinculoFuncionarioVaga();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // Pergunta as informações no terminal e salva um novo funcionário/usuário
    private static void cadastrarFuncionario() {
        System.out.println("\n--- Cadastrar Novo Usuário/Funcionário ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite a Senha: ");
        String senha = scanner.nextLine();

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Estado Civil: ");
        String estadoCivil = scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        System.out.print("ID da Vaga associada (0 se não houver): ");
        Long idVaga = Long.parseLong(scanner.nextLine());

        System.out.print("Perfil (ADM ou USER): ");
        String perfil = scanner.nextLine().toUpperCase();

        Vaga vaga = idVaga != 0 ? vagaService.buscarPorId(idVaga) : null;

        // Se tiver uma biblioteca de BCrypt ativa, aplique o hash no campo senha:
        // String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

        Funcionario f = new Funcionario(null, nome, dataNascimento, cpf, cep, email, telefone, estadoCivil, genero, vaga, true, perfil, senha);
        funcionarioService.cadastrarFuncionario(f);
        System.out.println(" Usuário cadastrado com sucesso! ID: " + f.getIdFuncionario());
    }

    private static void listarFuncionarios() {
        System.out.println("\n--- Todos os Funcionários ---");
        ArrayList<Funcionario> lista = funcionarioService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
        } else {
            lista.forEach(f -> System.out.printf("ID: %d | Nome: %s | CPF: %s | E-mail: %s | Ativo: %b | Perfil: %s\n",
                    f.getIdFuncionario(), f.getNome(), f.getCpf(), f.getEmail(), f.getAtivo(), f.getPerfil()));
        }
    }

    private static void buscarFuncionarioPorId() {
        System.out.print("Digite o ID do Funcionário: ");
        Long id = Long.parseLong(scanner.nextLine());
        Funcionario f = funcionarioService.buscarPorId(id);
        if (f != null) {
            System.out.println("Encontrado: " + f.getNome() + " - E-mail: " + f.getEmail() + " - Ativo: " + f.getAtivo());
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void buscarFuncionarioPorCpf() {
        System.out.print("Digite o CPF do Funcionário: ");
        String cpf = scanner.nextLine();
        Funcionario f = funcionarioService.buscarPorCpf(cpf);
        if (f != null) {
            System.out.println("Encontrado ID: " + f.getIdFuncionario() + " - Ativo: " + f.getAtivo());
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void buscarFuncionarioPorEmail() {
        System.out.print("Digite o E-mail do Funcionário: ");
        String email = scanner.nextLine();
        Funcionario f = funcionarioService.buscarPorEmail(email);
        if (f != null) {
            System.out.println("Encontrado: " + f.getNome() + " - CPF: " + f.getCpf());
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void atualizarFuncionario() {
        System.out.print("Digite o ID do Funcionário para atualizar: ");
        Long id = Long.parseLong(scanner.nextLine());
        Funcionario f = funcionarioService.buscarPorId(id);
        if (f != null) {
            System.out.print("Novo Nome (" + f.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isBlank()) f.setNome(nome);

            System.out.print("Novo E-mail (" + f.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) f.setEmail(email);

            System.out.print("Novo Telefone (" + f.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.isBlank()) f.setTelefone(telefone);

            funcionarioService.atualizar(f);
            System.out.println("Funcionário atualizado com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void desativarFuncionario() {
        System.out.print("Digite o ID do Funcionário para desativar: ");
        Long id = Long.parseLong(scanner.nextLine());
        funcionarioService.desativar(id);
        System.out.println("Funcionário desativado!");
    }

    private static void ativarFuncionario() {
        System.out.print("Digite o ID do Funcionário para ativar: ");
        Long id = Long.parseLong(scanner.nextLine());
        funcionarioService.ativarFuncionario(id);
        System.out.println("Funcionário ativado!");
    }

    private static void desativarFuncionariosPorVaga() {
        System.out.print("Digite o ID da Vaga para desativar todos os funcionários vinculados: ");
        Long idVaga = Long.parseLong(scanner.nextLine());
        funcionarioService.desativarPorVaga(idVaga);
        System.out.println("Funcionários vinculados à vaga foram desativados!");
    }

    private static void verificarVinculoFuncionarioVaga() {
        System.out.print("Digite o ID da Vaga: ");
        Long idVaga = Long.parseLong(scanner.nextLine());

        ArrayList<Funcionario> vinculados = funcionarioService.buscarPorVaga(idVaga);

        if (vinculados.isEmpty()) {
            System.out.println("\nNenhum funcionário ativo vinculado a esta vaga.");
        } else {
            System.out.println("\n--- Funcionários Ativos Vinculados à Vaga (ID " + idVaga + ") ---");
            vinculados.forEach(f -> System.out.printf("ID: %d | Nome: %s | E-mail: %s | CPF: %s\n",
                    f.getIdFuncionario(), f.getNome(), f.getEmail(), f.getCpf()));
        }
    }

    // =========================================================================
    // 2. GESTÃO DE VAGAS (Menu + Funções)
    // =========================================================================
    private static void menuVagas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE VAGAS ---");
            System.out.println("1. Cadastrar nova Vaga");
            System.out.println("2. Listar todas as Vagas");
            System.out.println("3. Listar apenas Vagas Ativas");
            System.out.println("4. Buscar Vaga por ID");
            System.out.println("5. Desativar Vaga");
            System.out.println("6. Desativar Vagas por Departamento");
            System.out.println("7. Verificar vínculo com Departamento");
            System.out.println("0. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> cadastrarVaga();
                    case 2 -> listarVagas(false);
                    case 3 -> listarVagas(true);
                    case 4 -> buscarVagaPorId();
                    case 5 -> desativarVaga();
                    case 6 -> desativarVagasPorDepartamento();
                    case 7 -> verificarVinculoDepartamento();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void cadastrarVaga() {
        System.out.println("\n--- Cadastrar Vaga ---");
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Turno (ex: Matutino/Noturno): ");
        String turno = scanner.nextLine();
        System.out.print("Salário por Hora: ");
        double salarioHora = Double.parseDouble(scanner.nextLine());
        System.out.print("ID do Departamento: ");
        Long idDepartamento = Long.parseLong(scanner.nextLine());

        Vaga vaga = new Vaga(null, turno, salarioHora, cargo, idDepartamento, true, 1);
        vagaService.salvar(vaga);
        System.out.println("Vaga cadastrada com sucesso! ID: " + vaga.getIdVaga());
    }

    private static void listarVagas(boolean apenasAtivas) {
        System.out.println(apenasAtivas ? "\n--- Vagas Ativas ---" : "\n--- Todas as Vagas ---");
        ArrayList<Vaga> lista = apenasAtivas ? vagaService.listarTodasAtivas() : vagaService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma vaga encontrada.");
        } else {
            lista.forEach(v -> System.out.printf("ID: %d | Cargo: %s | Turno: %s | Salário/H: R$%.2f | Ativa: %b\n",
                    v.getIdVaga(), v.getCargo(), v.getTurno(), v.getSalarioHora(), v.isAtivo()));
        }
    }

    private static void buscarVagaPorId() {
        System.out.print("Digite o ID da Vaga: ");
        Long id = Long.parseLong(scanner.nextLine());
        Vaga v = vagaService.buscarPorId(id);
        if (v != null) {
            System.out.println("Encontrada: " + v.getCargo() + " - Turno: " + v.getTurno() + " - Salário/H: R$" + v.getSalarioHora());
        } else {
            System.out.println("Vaga não encontrada.");
        }
    }

    private static void desativarVaga() {
        System.out.print("Digite o ID da Vaga para desativar: ");
        Long id = Long.parseLong(scanner.nextLine());
        vagaService.desativar(id);
        System.out.println("Vaga desativada com sucesso!");
    }

    private static void desativarVagasPorDepartamento() {
        System.out.print("Digite o ID do Departamento: ");
        Long idDep = Long.parseLong(scanner.nextLine());
        vagaService.desativarPorDepartamento(idDep);
        System.out.println("Vagas do departamento desativadas!");
    }

    private static void verificarVinculoDepartamento() {
        System.out.print("Digite o ID do Departamento: ");
        Long idDep = Long.parseLong(scanner.nextLine());

        ArrayList<Vaga> vagas = vagaService.buscarPorDepartamento(idDep);

        if (vagas.isEmpty()) {
            System.out.println("\nNenhuma vaga ativa vinculada a este departamento.");
        } else {
            System.out.println("\n--- Vagas Ativas no Departamento (ID " + idDep + ") ---");
            vagas.forEach(v -> System.out.printf("ID Vaga: %d | Cargo: %s | Turno: %s | Salário/H: R$%.2f\n",
                    v.getIdVaga(), v.getCargo(), v.getTurno(), v.getSalarioHora()));
        }
    }

    // =========================================================================
    // 3. GESTÃO DE CANDIDATURAS (Menu + Funções)
    // =========================================================================
    private static void menuCandidaturas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE CANDIDATURAS ---");
            System.out.println("1. Criar nova Candidatura");
            System.out.println("2. Listar Candidaturas Ativas");
            System.out.println("3. Buscar Candidatura por ID");
            System.out.println("4. Atualizar Status da Candidatura");
            System.out.println("5. Desativar Candidatura");
            System.out.println("6. Desativar Candidaturas por Vaga");
            System.out.println("7. Desativar Candidaturas por Candidato");
            System.out.println("8. Verificar vínculo com Candidato");
            System.out.println("9. Verificar vínculo com Vaga");
            System.out.println("0. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> criarCandidatura();
                    case 2 -> listarCandidaturas();
                    case 3 -> buscarCandidaturaPorId();
                    case 4 -> atualizarCandidatura();
                    case 5 -> desativarCandidatura();
                    case 6 -> candidaturaRepository.desativarPorVaga(pedirId("Vaga"));
                    case 7 -> candidaturaRepository.desativarPorCandidato(pedirId("Candidato/Funcionário"));
                    case 8 -> verificarVinculoCandidato();
                    case 9 -> verificarVinculoVagaCandidatura();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void verificarVinculoCandidato() {
        Long idFunc = pedirId("Candidato/Funcionário");
        ArrayList<Candidatura> candidaturas = candidaturaRepository.buscarPorCandidato(idFunc);

        if (candidaturas.isEmpty()) {
            System.out.println("\nNenhuma candidatura ativa encontrada para este candidato.");
        } else {
            System.out.println("\n--- Candidaturas Ativas do Candidato (ID " + idFunc + ") ---");
            candidaturas.forEach(c -> System.out.printf("ID Candidatura: %d | Vaga ID: %d | Status: %s | Data: %s\n",
                    c.getIdCandidatura(), c.getVaga().getIdVaga(), c.getStatus(), c.getDataCandidatura()));
        }
    }

    private static void verificarVinculoVagaCandidatura() {
        Long idVaga = pedirId("Vaga");
        ArrayList<Candidatura> candidaturas = candidaturaRepository.buscarPorVaga(idVaga);

        if (candidaturas.isEmpty()) {
            System.out.println("\nNenhuma candidatura ativa vinculada a esta vaga.");
        } else {
            System.out.println("\n--- Candidaturas Ativas para a Vaga (ID " + idVaga + ") ---");
            candidaturas.forEach(c -> System.out.printf("ID Candidatura: %d | Funcionário ID: %d | Status: %s | Data: %s\n",
                    c.getIdCandidatura(), c.getFuncionario().getIdFuncionario(), c.getStatus(), c.getDataCandidatura()));
        }
    }

    private static void criarCandidatura() {
        System.out.println("\n--- Nova Candidatura ---");
        Long idFunc = pedirId("Funcionário/Candidato");
        Long idVaga = pedirId("Vaga");

        Funcionario f = funcionarioService.buscarPorId(idFunc);
        Vaga v = vagaService.buscarPorId(idVaga);

        if (f == null || v == null) {
            System.out.println("Funcionário ou Vaga não encontrados.");
            return;
        }

        Candidatura c = new Candidatura(null, f, v, LocalDate.now(), "EM_ANALISE", true);
        candidaturaRepository.salvar(c);
        System.out.println("Candidatura salva com sucesso! ID: " + c.getIdCandidatura());
    }

    private static void listarCandidaturas() {
        System.out.println("\n--- Candidaturas Ativas ---");
        ArrayList<Candidatura> lista = candidaturaRepository.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma candidatura ativa.");
        } else {
            lista.forEach(c -> System.out.printf("ID: %d | Funcionario ID: %d | Vaga ID: %d | Data: %s | Status: %s\n",
                    c.getIdCandidatura(), c.getFuncionario().getIdFuncionario(), c.getVaga().getIdVaga(), c.getDataCandidatura(), c.getStatus()));
        }
    }

    private static void buscarCandidaturaPorId() {
        Long id = pedirId("Candidatura");
        Candidatura c = candidaturaRepository.buscarPorId(id);
        if (c != null) {
            System.out.println("Encontrada Candidatura ID: " + c.getIdCandidatura() + " - Status: " + c.getStatus());
        } else {
            System.out.println("Candidatura não encontrada.");
        }
    }

    private static void atualizarCandidatura() {
        Long id = pedirId("Candidatura");
        Candidatura c = candidaturaRepository.buscarPorId(id);
        if (c != null) {
            System.out.print("Novo Status (atual: " + c.getStatus() + "): ");
            String status = scanner.nextLine();
            c.setStatus(status);
            candidaturaRepository.atualizar(c);
            System.out.println("Candidatura atualizada!");
        } else {
            System.out.println("Candidatura não encontrada.");
        }
    }

    private static void desativarCandidatura() {
        Long id = pedirId("Candidatura");
        candidaturaRepository.desativar(id);
        System.out.println("Candidatura desativada!");
    }

    private static Long pedirId(String entidade) {
        System.out.print("Digite o ID do(a) " + entidade + ": ");
        return Long.parseLong(scanner.nextLine());
    }
}