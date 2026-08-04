# Sistema RH

Sistema de gestão de Recursos Humanos desenvolvido em **Java**, com persistência em **MySQL**, executado via terminal (CLI). O projeto cobre todo o ciclo de RH: departamentos, vagas, candidatos, candidaturas, contratação, funcionários, contratos, dados bancários e folha de pagamento — além de um módulo de autenticação com controle de acesso por perfil.

Projeto acadêmico focado em modelagem de banco de dados relacional (modelo conceitual e lógico), com implementação em Java aplicando o padrão em camadas (Model / Repository / Service / Controller).

---

## Índice

- [Sobre o projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Arquitetura e tecnologias](#arquitetura-e-tecnologias)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Modelagem do banco de dados](#modelagem-do-banco-de-dados)
- [Perfis de acesso](#perfis-de-acesso)
- [Pré-requisitos](#pré-requisitos)
- [Como configurar e executar](#como-configurar-e-executar)
- [Views e relatórios SQL](#views-e-relatórios-sql)
- [Segurança do banco de dados (roles)](#segurança-do-banco-de-dados-roles)
- [Autor](#autor)

---

## Sobre o projeto

O **Sistema RH** simula a operação do setor de Recursos Humanos de uma empresa, permitindo:

- Gerenciar **departamentos** e **vagas** vinculadas a eles;
- Registrar **candidatos** e suas **candidaturas** às vagas, acompanhando a etapa do processo seletivo;
- Efetivar candidatos aprovados como **funcionários**;
- Gerenciar **contratos**, **dados bancários** e **folhas de pagamento** dos funcionários;
- Controlar o acesso ao sistema por meio de **login/cadastro de usuário** e **perfis de permissão** (Admin, User, Viewer).

A aplicação roda inteiramente no terminal (`System.in` / `System.out`), com menus numéricos para cada entidade do sistema.

## Funcionalidades

- **Autenticação**
    - Cadastro de usuário (CPF + senha, com hash via BCrypt)
    - Login com validação de credenciais
    - Redirecionamento para o menu de acordo com o perfil do usuário logado (Admin, User ou Viewer)
- **Gestão de Departamentos** — CRUD completo (cadastrar, listar, buscar, atualizar, desativar)
- **Gestão de Vagas** — vinculadas a um departamento
- **Gestão de Candidatos** — CRUD com dados pessoais
- **Gestão de Candidaturas** — vínculo candidato ↔ vaga, com etapa e prazo do processo seletivo
- **Gestão de Funcionários** — efetivação vinculada a uma vaga
- **Gestão de Contratos** — vinculados a um funcionário, com status e prazo
- **Gestão de Dados Bancários** — vinculados a um funcionário
- **Gestão de Folha de Pagamento** — horas trabalhadas, horas extras e descontos por funcionário
- **Exclusão lógica (soft delete)** — todas as entidades possuem coluna `ativo`, e o sistema impede a desativação de registros que possuam vínculos ativos com outras entidades (ex.: não é possível desativar um funcionário que ainda possui contrato/dados bancários/folha ativos)
- **Controle de concorrência otimista** — todas as tabelas possuem coluna `version`, usada para evitar sobrescrita de dados em atualizações concorrentes

## Arquitetura e tecnologias

| Tecnologia | Uso |
|---|---|
| **Java 21** | Linguagem principal da aplicação |
| **Maven** | Gerenciamento de dependências e build |
| **MySQL** (via `mysql-connector-j`) | Banco de dados relacional / driver JDBC |
| **Lombok** | Geração de getters, setters e construtores |
| **jBCrypt** | Hash de senhas dos usuários |
| **Lanterna** | Dependência disponível para interface em terminal (TUI) |

**Padrão arquitetural:** camadas separadas por responsabilidade, seguindo o pacote `com.app`:

```
Controller  →  Service  →  Repository  →  ConnectionFactory (JDBC)
   ↑                                              ↓
 Model  ←────────────────────────────────  MySQL Database
```

- **`model`** — classes de domínio (POJOs anotados com Lombok: `Candidato`, `Candidatura`, `Contrato`, `DadosBancarios`, `Departamento`, `FolhaDePagamento`, `Funcionario`, `Usuario`, `Vaga`)
- **`repository`** — acesso a dados via JDBC puro (`PreparedStatement`), um repositório por entidade
- **`service`** — regras de negócio (ex.: validação de vínculos antes de desativar um registro)
- **`controller`** — `MainTerminal`, responsável pelos menus e pela interação via terminal
- **`config`** — `ConnectionFactory`, responsável por abrir conexões JDBC a partir de `db.properties`
- **`enums`** — `Perfis` (ADMIN, USER, VIEWER) e `StatusVinculos` (SUCESSO, POSSUI_VINCULOS)
- **`exception`** — `RepositoryException`, exceção customizada lançada pela camada de repositório

## Estrutura do projeto

**`pom.xml`** — configuração do projeto Maven (dependências e build)

**`src/main/java/com/app/`** — código-fonte da aplicação, organizado por camada:

- `config/` — `ConnectionFactory` (conexão JDBC)
- `controller/` — `MainTerminal` (menus / ponto de entrada da aplicação)
- `enums/` — `Perfis`, `StatusVinculos`
- `exception/` — `RepositoryException`
- `model/` — entidades de domínio
- `repository/` — acesso a dados (JDBC)
- `service/` — regras de negócio

**`src/resources/`** — arquivos de configuração e banco de dados:

- `db_example.properties` — modelo de configuração de conexão com o banco
- `db/diagrams/` — modelo conceitual e lógico (imagens PNG)
- `db/mysql/` — scripts MySQL
    - `create_db.sql` — criação do banco
    - `schema.sql` — criação das tabelas
    - `data.sql` — dados de exemplo (seed)
    - `views.sql` — views/relatórios (14 consultas)
    - `security-config.sql` — roles e usuários do banco
    - `queries.sql` — consultas auxiliares
    - `migrations/` — scripts de evolução do schema
- `db/postgresql/` — equivalente dos scripts acima, para PostgreSQL

**`README.md`** — este documento

## Modelagem do banco de dados

O banco `sistema_rh_db` é composto pelas seguintes tabelas (relacionamentos principais):

| Tabela | Descrição | Relacionamentos |
|---|---|---|
| `departamentos` | Departamentos da empresa (nome, gastos, retorno) | 1:N com `vagas` |
| `vagas` | Vagas abertas (turno, cargo, salário/hora) | N:1 com `departamentos`; 1:N com `funcionarios` e `candidaturas` |
| `candidatos` | Pessoas que se candidatam a vagas | 1:N com `candidaturas` |
| `candidaturas` | Vínculo candidato ↔ vaga (etapa, prazo, status) | N:1 com `candidatos` e `vagas` |
| `funcionarios` | Pessoas efetivadas em uma vaga | N:1 com `vagas`; 1:1 com `contratos`, `dados_bancarios`, `usuarios`; 1:N com `folhas_de_pagamentos` |
| `contratos` | Contrato de trabalho do funcionário | N:1 com `funcionarios` |
| `dados_bancarios` | Conta bancária do funcionário para pagamento | N:1 com `funcionarios` |
| `folhas_de_pagamentos` | Fechamentos de folha (horas, descontos, extras) | N:1 com `funcionarios` |
| `usuarios` | Credenciais de acesso ao sistema | N:1 com `funcionarios` |

Todas as tabelas de negócio possuem as colunas de controle `ativo` (soft delete) e `version` (controle de concorrência otimista), adicionadas via migrations.

Os diagramas do **modelo conceitual** e do **modelo lógico** (em português e em inglês) estão disponíveis em `src/resources/db/diagrams/`:

- `modelo_conceitual.png` / `conceptual_model_en.png`
- `modelo_logico.png` / `logical_model_en.png`

> Os scripts de criação do schema estão em `src/resources/db/mysql/` (MySQL) e, de forma equivalente, em `src/resources/db/postgresql/` (PostgreSQL).

## Perfis de acesso

O sistema possui dois níveis de controle de acesso:

1. **Perfis de aplicação** (`enum Perfis`, tabela `usuarios`), usados no login do terminal:
    - `ADMIN` — acesso completo a todos os menus de gestão
    - `USER` — acesso operacional
    - `VIEWER` — acesso apenas de consulta

2. **Roles de banco de dados** (definidas em `security-config.sql`), simulando a hierarquia de um setor de RH real, cada uma com permissões `GRANT` específicas sobre as tabelas:
    - `estagiarios_rh` — apenas leitura (`SELECT`)
    - `assistentes_rh` — leitura e inserção (`SELECT`, `INSERT`)
    - `analistas_rh` — CRUD completo nas tabelas operacionais
    - `gerentes_rh` — leitura e acesso às views (`SELECT`, `SHOW VIEW`), incluindo dados sensíveis (bancários, contratos, folha)
    - `diretores_rh` / `CEOs_rh` — leitura ampla e acesso a todas as views de relatório

## Pré-requisitos

- **JDK 21** ou superior
- **Maven 3.8+**
- **MySQL 8+** em execução local ou remota
- (Opcional) Cliente SQL de sua preferência para rodar os scripts (MySQL Workbench, DBeaver, `mysql` CLI, etc.)

## Como configurar e executar

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
cd projeto-banco-de-dados-valentim
```

### 2. Criar o banco de dados

Crie o banco:

```bash
mysql -u <usuario> -p < src/resources/db/mysql/create_db.sql
```

Crie as tabelas:

```bash
mysql -u <usuario> -p sistema_rh_db < src/resources/db/mysql/schema.sql
```

*(Opcional)* Popule com dados de exemplo:

```bash
mysql -u <usuario> -p sistema_rh_db < src/resources/db/mysql/data.sql
```

*(Opcional)* Crie as views de relatório:

```bash
mysql -u <usuario> -p sistema_rh_db < src/resources/db/mysql/views.sql
```

Aplique as migrations, na ordem numérica, caso ainda não estejam incorporadas ao `schema.sql` utilizado:

```bash
mysql -u <usuario> -p sistema_rh_db < src/resources/db/mysql/migrations/001_add_ativo_column.sql
mysql -u <usuario> -p sistema_rh_db < src/resources/db/mysql/migrations/002_create_table_usuarios.sql
mysql -u <usuario> -p sistema_rh_db < src/resources/db/mysql/migrations/003_add_version_column.sql
```

> Uma versão equivalente dos scripts para **PostgreSQL** está disponível em `src/resources/db/postgresql/`.

### 3. Configurar a conexão da aplicação

Copie o arquivo de exemplo:

```bash
cp src/resources/db_example.properties src/resources/db.properties
```

Edite `src/resources/db.properties` com suas credenciais:

```properties
db.url=jdbc:mysql://localhost:3306/sistema_rh_db
db.user=seu_usuario
db.password=sua_senha
```

> ⚠️ O arquivo `db.properties` contém credenciais e **não deve ser versionado** (já é ignorado via `.gitignore`). Utilize sempre `db_example.properties` como modelo.

### 4. Compilar e executar

Com Maven:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.app.controller.MainTerminal"
```

Ou, pela sua IDE (IntelliJ IDEA, Eclipse, VS Code), execute diretamente a classe `com.app.controller.MainTerminal`.

### 5. Usando o sistema

Ao iniciar, o terminal exibirá o menu principal:

```
=================================
          SISTEMA DE RH
=================================
1 - Login
2 - Cadastro
0 - Sair
```

- Escolha **2** para criar um novo usuário (CPF + senha).
- Escolha **1** para efetuar login e acessar o menu correspondente ao seu perfil (Admin, User ou Viewer), onde é possível gerenciar candidatos, funcionários, vagas, departamentos, candidaturas, contratos, folhas de pagamento e dados bancários.

## Views e relatórios SQL

O arquivo `src/resources/db/mysql/views.sql` contém **14 views** que respondem a perguntas de negócio típicas de um setor de RH, entre elas:

- Funcionários por cargo e departamento
- Quantidade de funcionários por departamento
- Média salarial por departamento
- Funcionários com horas extras no último fechamento de folha
- Total de horas extras e de descontos por departamento
- Vagas ocupadas vs. disponíveis por departamento
- Candidaturas em andamento e quantidade de candidatos por etapa
- Estimativa de custo mensal da folha de pagamento por departamento
- Funcionários com remuneração acima da média salarial da empresa
- Ranking de departamentos por custo estimado de folha

## Segurança do banco de dados (roles)

O script `security-config.sql` cria roles nomeadas (`estagiarios_rh`, `assistentes_rh`, `analistas_rh`, `gerentes_rh`, `diretores_rh`, `CEOs_rh`) com permissões granulares por tabela, além de usuários MySQL de exemplo associados a cada role — demonstrando na prática o princípio de **menor privilégio** aplicado a um ambiente corporativo simulado.

> Esse script é de caráter didático/demonstrativo. Antes de aplicá-lo em qualquer ambiente real, revise as senhas, os nomes de usuário e as permissões concedidas.

## Autores

Projeto desenvolvido por **Pedro Emanuel, Thiago, Gustavo, Lorenzo, Caio e João Nascimento** como aplicação final da Situação de Aprendizagem de Implantação de Banco de Dados do professor Valentim.
