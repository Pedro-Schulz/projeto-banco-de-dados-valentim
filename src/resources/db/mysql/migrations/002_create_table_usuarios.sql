-- 002_create_table_usuarios.sql
-- Cria a tabela 'usuarios', usada para login/autenticação.
-- OBS: esta migration só deve ser aplicada em bancos criados ANTES da tabela
-- 'usuarios' existir no schema.sql. Se você já rodou o schema.sql atualizado,
-- a tabela já existe (com a coluna cpf) e não deve rodar esta migration.
CREATE TABLE usuarios (
	id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    cpf CHAR(11) NOT NULL UNIQUE,
    senha VARCHAR(60) NOT NULL,
    perfil VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL,
    id_funcionario INT NOT NULL,

    FOREIGN KEY(id_funcionario) REFERENCES funcionarios(id_funcionario)
);