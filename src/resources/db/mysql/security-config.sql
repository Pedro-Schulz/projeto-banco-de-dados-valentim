CREATE ROLE estagiarios_rh , analistas_rh, assistentes_rh, gerentes_rh , diretores_rh, CEOs_rh;

-- Permissões da ROLE estagiarios_rh

GRANT SELECT ON departamentos TO estagiarios_rh;
GRANT SELECT ON vagas TO estagiarios_rh;
GRANT SELECT ON funcionarios TO estagiarios_rh;
GRANT SELECT ON candidatos TO estagiarios_rh;
GRANT SELECT ON candidaturas TO estagiarios_rh;

-- Permissões da ROLE assistentes_rh

GRANT INSERT, SELECT ON departamentos TO assistentes_rh;
GRANT INSERT, SELECT ON vagas TO assistentes_rh;
GRANT INSERT, SELECT ON funcionarios TO assistentes_rh;
GRANT INSERT, SELECT ON candidatos TO assistentes_rh;
GRANT INSERT, SELECT ON candidaturas TO assistentes_rh;

-- Permissões da ROLE analistas_rh

GRANT SELECT, INSERT, UPDATE, DELETE ON departamentos TO 'analistas_rh';
GRANT SELECT, INSERT, UPDATE, DELETE ON candidatos TO 'analistas_rh';
GRANT SELECT, INSERT, UPDATE, DELETE ON candidaturas TO 'analistas_rh';
GRANT SELECT, INSERT, UPDATE, DELETE ON funcionarios TO 'analistas_rh';
GRANT SELECT, INSERT, UPDATE, DELETE ON vagas TO 'analistas_rh';
GRANT SELECT, INSERT, UPDATE, DELETE ON dados_bancarios TO 'analistas_rh';
GRANT SELECT, INSERT, UPDATE, DELETE ON contratos TO 'analistas_rh';
GRANT SELECT, INSERT, UPDATE, DELETE ON folhas_de_pagamentos TO 'analistas_rh';

-- Permissões da ROLE gerentes_rh

GRANT SELECT, SHOW VIEW ON departamentos TO gerentes_rh;
GRANT SELECT, SHOW VIEW ON vagas TO gerentes_rh;
GRANT SELECT, SHOW VIEW ON funcionarios TO gerentes_rh;
GRANT SELECT, SHOW VIEW ON candidatos TO gerentes_rh;
GRANT SELECT, SHOW VIEW ON candidaturas TO gerentes_rh;
GRANT SELECT, INSERT, UPDATE, DELETE ON dados_bancarios TO gerentes_rh;
GRANT SELECT, INSERT, UPDATE, DELETE ON contratos TO gerentes_rh;
GRANT SELECT, INSERT, UPDATE, DELETE ON folhas_de_pagamentos TO gerentes_rh;

-- Permissões da ROLE diretores_rh

GRANT SELECT, SHOW VIEW ON departamentos TO 'diretores_rh';
GRANT SELECT, SHOW VIEW ON candidatos TO 'diretores_rh';
GRANT SELECT, SHOW VIEW ON candidaturas TO 'diretores_rh';
GRANT SELECT, SHOW VIEW ON contratos TO 'diretores_rh';
GRANT SELECT, SHOW VIEW ON dados_bancarios TO 'diretores_rh';
GRANT SELECT, SHOW VIEW ON departamentos TO 'diretores_rh';
GRANT SELECT, SHOW VIEW ON folhas_de_pagamentos TO 'diretores_rh';
GRANT SELECT, SHOW VIEW ON funcionarios TO 'diretores_rh';
GRANT SELECT, SHOW VIEW ON vagas TO 'diretores_rh';

-- Permissões da ROLE CEOs_rh

GRANT SELECT, SHOW VIEW ON departamentos TO 'CEOs_rh';
GRANT SELECT, SHOW VIEW ON candidatos TO 'CEOs_rh';
GRANT SELECT, SHOW VIEW ON candidaturas TO 'CEOs_rh';
GRANT SELECT, SHOW VIEW ON contratos TO 'CEOs_rh';
GRANT SELECT, SHOW VIEW ON dados_bancarios TO 'CEOs_rh';
GRANT SELECT, SHOW VIEW ON departamentos TO 'CEOs_rh';
GRANT SELECT, SHOW VIEW ON folhas_de_pagamentos TO 'CEOs_rh';
GRANT SELECT, SHOW VIEW ON funcionarios TO 'CEOs_rh';
GRANT SELECT, SHOW VIEW ON vagas TO 'CEOs_rh';

-- Criação dos usuários do grupo Estagiarios

CREATE USER 'João'@'%' IDENTIFIED BY 'estagiarios_senha';
GRANT 'estagiarios_rh' TO 'João'@'%';
SET DEFAULT ROLE estagiarios_rh TO 'João'@'%';

CREATE USER 'Matheus'@'%' IDENTIFIED BY 'estagiarios_senha';
GRANT 'estagiarios_rh' TO 'Matheus'@'%';
SET DEFAULT ROLE estagiarios_rh TO 'Matheus'@'%';

-- Criação dos usuários do grupo Assistentes

CREATE USER 'Lucas'@'%' IDENTIFIED BY 'assistentes_senha';
GRANT 'assistentes_rh' TO 'Lucas'@'%';
SET DEFAULT ROLE assistentes_rh TO 'Lucas'@'%';

CREATE USER 'Vinicius'@'%' IDENTIFIED BY 'assistentes_senha';
GRANT 'assistentes_rh' TO 'Vinicius'@'%';
SET DEFAULT ROLE assistentes_rh TO 'Vinicius'@'%';

-- Criação dos usuários do grupo Analistas

CREATE USER 'Ana'@'%' IDENTIFIED BY 'analistas_senha';
GRANT 'analistas_rh' TO 'Ana'@'%';
SET DEFAULT ROLE analistas_rh TO 'Ana'@'%';

CREATE USER 'Guilherme'@'%' IDENTIFIED BY 'analistas_senha';
GRANT 'analistas_rh' TO 'Guilherme'@'%';
SET DEFAULT ROLE analistas_rh TO 'Guilherme'@'%';

-- Criação dos usuários do grupo Gerentes

CREATE USER 'Cassiano'@'%' IDENTIFIED BY 'gerentes_senha';
GRANT 'gerentes_rh' TO 'Cassiano'@'%';
SET DEFAULT ROLE 'gerentes_rh' TO 'Cassiano'@'%';


CREATE USER 'Daniela'@'%' IDENTIFIED BY 'gerentes_senha';
GRANT 'gerentes_rh' TO 'Daniela'@'%';
SET DEFAULT ROLE 'gerentes_rh' TO 'Daniela'@'%';

-- Criação dos usuários do grupo Diretores

CREATE USER 'Alexandre'@'%' IDENTIFIED BY 'diretores_senha';
GRANT 'diretores_rh' TO 'Alexandre'@'%';
SET DEFAULT ROLE 'diretores_rh' TO 'Alexandre'@'%';

CREATE USER 'Betina'@'%' IDENTIFIED BY 'diretores_senha';
GRANT 'diretores_rh' TO 'Betina'@'%';
SET DEFAULT ROLE 'diretores_rh' TO 'Betina'@'%';

-- Criação dos usuários do grupo CEO

CREATE USER 'Kuba'@'%' IDENTIFIED BY 'ceo_senha';
GRANT 'CEOs_rh' TO 'Kuba'@'%';
SET DEFAULT ROLE 'CEOs_rh' TO 'Kuba'@'%';