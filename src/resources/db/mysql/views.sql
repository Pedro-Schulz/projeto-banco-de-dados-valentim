-- 1 Elaborar um relatório exibindo o nome de cada funcionário, o cargo que ocupa e o departamento ao qual pertence.
CREATE VIEW vw_funcionarios AS
SELECT funcionarios.nome, vagas.cargo, departamentos.nome
FROM funcionarios
JOIN vagas
ON funcionarios.id_vaga = vagas.id_vaga
JOIN departamentos
ON vagas.id_departamento = departamentos.id_departamento;


-- 2 Elaborar um relatório apresentando a quantidade de funcionários existentes em cada departamento da empresa.
CREATE VIEW vw_funcionarios_departamento AS
SELECT
departamentos.nome,
COUNT(funcionarios.id_funcionario) AS
quantidade_funcionarios
FROM departamentos
JOIN vagas
ON departamentos.id_departamento = vagas.id_departamento
JOIN funcionarios
ON vagas.id_vaga = funcionarios.id_vaga
GROUP BY departamentos.id_departamento, departamentos.nome;


-- 3 Exibir o valor médio do salário por hora dos cargos pertencentes a cada departamento.
CREATE VIEW vw_media_salario_departamento AS
SELECT
departamentos.nome,
AVG(vagas.salario_hora) AS media_salario_hora
FROM departamentos
JOIN vagas
ON departamentos.id_departamento = vagas.id_departamento
GROUP BY departamentos.nome;


-- 4 Exibir os funcionários que registraram horas extras no último fechamento da folha de pagamento, juntamente com a quantidade de horas realizadas.
CREATE VIEW vw_funcionarios_horas_extras AS
SELECT
   funcionarios.nome,
   folhas_de_pagamentos.horas_extras
FROM funcionarios
JOIN folhas_de_pagamentos
   ON funcionarios.id_funcionario = folhas_de_pagamentos.id_funcionario
WHERE folhas_de_pagamentos.horas_extras <> 0
AND folhas_de_pagamentos.data_emissao = (
   SELECT MAX(data_emissao)
   FROM folhas_de_pagamentos
);


-- 5 Apresentar o total de horas extras realizadas por cada departamento.
CREATE VIEW vw_horas_extras_departamento AS
SELECT
   departamentos.nome,
   SUM(folhas_de_pagamentos.horas_extras) AS total_horas_extras
   FROM departamentos
   JOIN vagas
   ON departamentos.id_departamento = vagas.id_departamento
   JOIN funcionarios
   ON vagas.id_vaga = funcionarios.id_vaga
   JOIN folhas_de_pagamentos
   ON funcionarios.id_funcionario = folhas_de_pagamentos.id_funcionario
   GROUP BY departamentos.nome;




-- 6 Gerar um relatório contendo o total de descontos aplicados na folha de pagamento de cada departamento.
   CREATE VIEW vw_descontos_folha_departamento AS
   SELECT
       departamentos.nome,
       SUM(folhas_de_pagamentos.descontos) AS total_descontos
       FROM departamentos
       JOIN vagas
       ON departamentos.id_departamento = vagas.id_departamento
       JOIN funcionarios
       ON vagas.id_vaga = funcionarios.id_vaga
       JOIN folhas_de_pagamentos
       ON funcionarios.id_funcionario = folhas_de_pagamentos.id_funcionario
       GROUP BY departamentos.nome;




-- 7 Elaborar um relatório mostrando a quantidade de vagas existentes em cada departamento.
       CREATE VIEW vw_vagas_departamento AS
       SELECT
           d.nome AS Departamento,
           COUNT(v.id_vaga) AS Total_Vagas
       FROM
           departamentos d
       LEFT JOIN
           vagas v ON d.id_departamento = v.id_departamento
       GROUP BY
           d.nome;




-- 8 Gerar um relatório apresentando, para cada departamento, o número de vagas ocupadas e o número de vagas ainda disponíveis.
CREATE VIEW vw_vagas_ocupadas_disponiveis AS
SELECT
   d.nome AS Departamento,
   COUNT(v.id_vaga) AS Total_Vagas,
   SUM(CASE WHEN f.id_funcionario IS NOT NULL THEN 1 ELSE 0 END) AS Vagas_Ocupadas,
   SUM(CASE WHEN f.id_funcionario IS NULL THEN 1 ELSE 0 END) AS Vagas_Disponiveis
FROM
   departamentos d
INNER JOIN
   vagas v ON v.id_departamento = d.id_departamento
LEFT JOIN
   funcionarios f ON f.id_vaga = v.id_vaga
GROUP BY
   d.nome;




-- 9 Exibir todas as candidaturas que ainda estão em andamento, informando o candidato, a vaga e a etapa atual do processo seletivo.
CREATE VIEW vw_candidaturas_andamento AS
SELECT
   c.nome AS Candidato,
   v.cargo AS Vaga,
   ca.etapa AS Etapa_Atual,
   ca.data_candidatura AS Data_Candidatura,
   ca.prazo AS Prazo
FROM
   candidaturas ca
INNER JOIN
   candidatos c ON c.id_candidato = ca.id_candidato
INNER JOIN
   vagas v ON v.id_vaga = ca.id_vaga
WHERE
   ca.status_candidatura = 1;




-- 10 Elaborar um relatório apresentando a quantidade de candidatos em cada etapa do processo seletivo.
CREATE VIEW vw_candidatos_etapa AS
SELECT
   ca.etapa AS Etapa,
   COUNT(ca.id_candidatura) AS Quantidade_Candidatos
FROM
   candidaturas ca
GROUP BY
   ca.etapa;




-- 11 Gerar um relatório mostrando quantos candidatos participaram do processo seletivo de cada vaga aberta.
CREATE VIEW vw_candidatos_por_vaga AS
SELECT
   v.cargo AS Vaga,
   d.nome AS Departamento,
   COUNT(ca.id_candidatura) AS Total_Candidatos
FROM
   vagas v
INNER JOIN
   departamentos d ON d.id_departamento = v.id_departamento
INNER JOIN
   candidaturas ca ON ca.id_vaga = v.id_vaga
GROUP BY
   v.id_vaga, v.cargo, d.nome;




-- 12 Elaborar um relatório estimando o custo mensal da folha de pagamento de cada departamento, considerando salários e horas trabalhadas.
CREATE VIEW vw_custo_folha_departamento AS
SELECT
   d.nome AS Departamento,
   COUNT(DISTINCT f.id_funcionario) AS Total_Funcionarios,
   SUM(fp.horas_trabalhadas) AS Total_Horas_Mes,
   SUM(v.salario_hora * fp.horas_trabalhadas) AS Total_Salario_Base,
   SUM(fp.horas_extras * v.salario_hora * 1.5) AS Custo_Estimado_Horas_Extras,
   (SUM(v.salario_hora * fp.horas_trabalhadas)
       + SUM(fp.horas_extras * v.salario_hora * 1.5)) * 1.40 AS Custo_Mensal_Total_Estimado
FROM
   departamentos d
INNER JOIN
   vagas v ON d.id_departamento = v.id_departamento
INNER JOIN
   funcionarios f ON f.id_vaga = v.id_vaga
INNER JOIN
   contratos c ON c.id_funcionario = f.id_funcionario
INNER JOIN
   folhas_de_pagamentos fp ON fp.id_funcionario = f.id_funcionario
WHERE
   c.status_contrato = 1
GROUP BY
   d.nome;




-- 13 Exibir os funcionários cuja remuneração por hora esteja acima da média salarial da empresa.
CREATE VIEW vw_funcionarios_acima_media_salarial AS
SELECT
   f.id_funcionario,
   f.nome AS Funcionario,
   v.cargo AS Vaga,
   v.salario_hora AS Salario_Hora,
   d.nome AS Departamento
FROM
   funcionarios f
INNER JOIN
   vagas v ON v.id_vaga = f.id_vaga
INNER JOIN
   departamentos d ON d.id_departamento = v.id_departamento
WHERE
   v.salario_hora > (
       SELECT AVG(salario_hora) FROM vagas
   );


-- 14 Elaborar um ranking dos departamentos com maior custo estimado de folha de pagamento.
CREATE VIEW vw_maior_custo_departamento AS
SELECT
   d.nome AS Departamento,
   SUM(fp.horas_trabalhadas * v.salario_hora
       + fp.horas_extras * v.salario_hora * 1.5) AS Custo_Estimado_Folha
FROM
   departamentos d
INNER JOIN
   vagas v ON v.id_departamento = d.id_departamento
INNER JOIN
   funcionarios f ON f.id_vaga = v.id_vaga
INNER JOIN
   folhas_de_pagamentos fp ON fp.id_funcionario = f.id_funcionario
GROUP BY
   d.nome;

