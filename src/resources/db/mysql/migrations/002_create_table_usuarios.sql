CREATE TABLE usuarios (
	cpf INT PRIMARY KEY AUTO_INCREMENT,
    senha VARCHAR(60) NOT NULL,
    perfil VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL,
    id_funcionario INT NOT NULL,

    FOREIGN KEY(id_funcionario) REFERENCES funcionarios(id_funcionario)
);