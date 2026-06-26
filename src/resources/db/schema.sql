CREATE TABLE funcionarios(
	id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    data_nascimento DATE,
    cpf CHAR(11),
    cep CHAR(8),
    email VARCHAR(50),
    telefone CHAR(11),
    estadoCivil VARCHAR(20),
    genero CHAR(1)

    id_vaga INT,

    FOREIGN KEY(id_vaga) REFERENCES vagas(id_vaga)
);

CREATE TABLE dados_bancarios(
	id_dados_bancarios INT AUTO_INCREMENT PRIMARY KEY,
    numero_conta INT NOT NULL,
    instituicao_bancaria VARCHAR(50) NOT NULL,
    agencia_bancaria INT NOT NULL,

    id_funcionario INT,

    FOREIGN KEY(id_funcionario) REFERENCES funcionarios(id_funcionario)
);

CREATE TABLE vagas(
	id_vaga INT AUTO_INCREMENT PRIMARY KEY,
    turno VARCHAR(50) NOT NULL,
    salario_hora DECIMAL(10, 2),
    cargo VARCHAR(50),

    id_departamento INT,

    FOREIGN KEY(id_departamento) REFERENCES departamentos(id_departamento)
);

CREATE TABLE departamentos(
	id_departamento INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    gastos DECIMAL(10, 2) NOT NULL,
    retorno DECIMAL(10, 2) NOT NULL
);

CREATE TABLE contratos(
	id_contrato INT AUTO_INCREMENT PRIMARY KEY,
    status_contrato BOOL NOT NULL,
    data_emissao DATE,
    prazo INT NOT NULL,

    id_funcionario INT,

    FOREIGN KEY(id_funcionario) REFERENCES funcionarios(id_funcionario)
);

CREATE TABLE folhas_de_pagamentos(
	id_folha INT AUTO_INCREMENT PRIMARY KEY,
    horas_trabalhadas INT NOT NULL,
    data_emissao DATE,
    descontos DECIMAL(10, 2),
    horas_extras INT NOT NULL,

    id_funcionario INT,

    FOREIGN KEY(id_funcionario) REFERENCES funcionarios(id_funcionario)
);

CREATE TABLE candidatos(
    id_candidato INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cpf CHAR(11),
    cep CHAR(8),
    email VARCHAR(100),
    telefone CHAR(11),
    genero CHAR(1),
    estado_civil VARCHAR(20),
    data_nascimento DATE
);

CREATE TABLE candidaturas(
    id_candidatura INT AUTO_INCREMENT PRIMARY KEY,
    status_candidatura BOOL NOT NULL,
    data_candidatura DATE NOT NULL,
    prazo DATE NOT NULL,
    etapa VARCHAR(50),

    id_vaga INT,
    id_candidato INT,

    FOREIGN KEY(id_vaga) REFERENCES vagas(id_vaga),
    FOREIGN KEY(id_candidato) REFERENCES candidatos(id_candidato)
);