-- 1 Elaborar um relatório exibindo o nome de cada funcionário, o cargo que ocupa e o departamento ao qual pertence.
SELECT
    funcionarios.nome,
    vagas.cargo,
    departamentos.nome AS departamento
FROM funcionarios
         JOIN vagas
              ON funcionarios.id_vaga = vagas.id_vaga
         JOIN departamentos
              ON vagas.id_departamento = departamentos.id_departamento;

-- 2 Gerar uma lista contendo todas as vagas que ainda não foram preenchidas, informando o cargo e o departamento responsável pela contratação.
SELECT
    vagas.cargo,
    departamentos.nome AS departamento
FROM vagas
         JOIN departamentos
              ON vagas.id_departamento = departamentos.id_departamento
         LEFT JOIN funcionarios
                   ON vagas.id_vaga = funcionarios.id_vaga
WHERE funcionarios.id_funcionario IS NULL;

-- 3 Elaborar um relatório apresentando a quantidade de funcionários existentes em cada departamento da empresa.
SELECT
    departamentos.nome AS departamento,
    COUNT(funcionarios.id_funcionario) AS quantidade_funcionarios
FROM departamentos
         JOIN vagas
              ON departamentos.id_departamento = vagas.id_departamento
         JOIN funcionarios
              ON vagas.id_vaga = funcionarios.id_vaga
GROUP BY departamentos.id_departamento, departamentos.nome;

-- 4 Exibir o valor médio do salário por hora dos cargos pertencentes a cada departamento.
SELECT
    departamentos.nome AS departamento,
    AVG(vagas.salario_hora) AS media_salario_hora
FROM departamentos
         JOIN vagas
              ON departamentos.id_departamento = vagas.id_departamento
GROUP BY departamentos.nome;

-- 5 Listar os cargos cadastrados na empresa em ordem decrescente de salário por hora.
SELECT
    cargo,
    salario_hora
FROM vagas
ORDER BY salario_hora DESC;

-- 6 Apresentar todos os departamentos cujo valor de gastos ultrapasse R$ 100.000,00.
SELECT
    nome AS departamento,
    gastos
FROM departamentos
WHERE gastos > 100000.00;

-- 7 Gerar um relatório mostrando os departamentos cujo retorno financeiro seja superior aos seus gastos.
SELECT
    nome AS departamento,
    retorno,
    gastos
FROM departamentos
WHERE retorno > gastos;

-- 8 Funcionários com mais horas extras.
SELECT
    funcionarios.nome,
    folhas_de_pagamentos.horas_extras
FROM funcionarios
         JOIN folhas_de_pagamentos
              ON funcionarios.id_funcionario = folhas_de_pagamentos.id_funcionario
WHERE horas_extras <> 0
ORDER BY horas_extras DESC;

-- 9 Apresentar o total de horas extras realizadas por cada departamento.
SELECT
    departamentos.nome AS departamento,
    SUM(folhas_de_pagamentos.horas_extras) AS total_horas_extras
FROM departamentos
         JOIN vagas
              ON departamentos.id_departamento = vagas.id_departamento
         JOIN funcionarios
              ON vagas.id_vaga = funcionarios.id_vaga
         JOIN folhas_de_pagamentos
              ON funcionarios.id_funcionario = folhas_de_pagamentos.id_funcionario
GROUP BY departamentos.nome
ORDER BY total_horas_extras DESC;

-- 10 Gerar um relatório contendo o total de descontos aplicados na folha de pagamento de cada departamento.
SELECT
    departamentos.nome AS departamento,
    SUM(folhas_de_pagamentos.descontos) AS total_descontos
FROM departamentos
         JOIN vagas
              ON departamentos.id_departamento = vagas.id_departamento
         JOIN funcionarios
              ON vagas.id_vaga = funcionarios.id_vaga
         JOIN folhas_de_pagamentos
              ON funcionarios.id_funcionario = folhas_de_pagamentos.id_funcionario
GROUP BY departamentos.nome
ORDER BY total_descontos DESC;

-- 11 Exibir os funcionários que tiveram descontos superiores a R$ 300,00 em sua folha de pagamento.
SELECT
    funcionarios.nome,
    SUM(folhas_de_pagamentos.descontos) AS total_descontos
FROM funcionarios
         JOIN folhas_de_pagamentos
              ON funcionarios.id_funcionario = folhas_de_pagamentos.id_funcionario
GROUP BY funcionarios.id_funcionario, funcionarios.nome
HAVING total_descontos > 300.00
ORDER BY total_descontos DESC;

-- 12 Instituição bancária com maior número de contas.
SELECT
    instituicao_bancaria,
    COUNT(id_funcionario) AS total_funcionarios
FROM dados_bancarios
GROUP BY instituicao_bancaria
ORDER BY total_funcionarios DESC
    LIMIT 1;

-- 13 Contratos com vencimento nos próximos 24 meses.
SELECT id_contrato
FROM contratos
WHERE Prazo < 24;

-- 14 Total de vagas criadas por departamento.
SELECT
    d.nome AS Departamento,
    COUNT(v.id_vaga) AS Total_Vagas
FROM departamentos d
         LEFT JOIN vagas v
                   ON d.id_departamento = v.id_departamento
GROUP BY d.nome
ORDER BY Total_Vagas DESC;

-- 15 Vagas ocupadas vs. vagas disponíveis por departamento.
SELECT
    d.nome AS Departamento,
    COUNT(v.id_vaga) AS Total_Vagas,
    SUM(CASE WHEN f.id_funcionario IS NOT NULL THEN 1 ELSE 0 END) AS Vagas_Ocupadas,
    SUM(CASE WHEN f.id_funcionario IS NULL THEN 1 ELSE 0 END) AS Vagas_Disponiveis
FROM departamentos d
         INNER JOIN vagas v
                    ON v.id_departamento = d.id_departamento
         LEFT JOIN funcionarios f
                   ON f.id_vaga = v.id_vaga
GROUP BY d.nome
ORDER BY d.nome;

-- 16 Candidaturas ativas e suas etapas atuais.
SELECT
    c.nome AS Candidato,
    v.cargo AS Vaga,
    ca.etapa AS Etapa_Atual,
    ca.data_candidatura AS Data_Candidatura,
    ca.prazo AS Prazo
FROM candidaturas ca
         INNER JOIN candidatos c
                    ON c.id_candidato = ca.id_candidato
         INNER JOIN vagas v
                    ON v.id_vaga = ca.id_vaga
WHERE ca.status_candidatura = 1
ORDER BY ca.data_candidatura DESC;

-- 17 Total de candidatos por etapa do processo seletivo.
SELECT
    ca.etapa AS Etapa,
    COUNT(ca.id_candidatura) AS Quantidade_Candidatos
FROM candidaturas ca
GROUP BY ca.etapa
ORDER BY Quantidade_Candidatos DESC;

-- 18 Ranking de candidaturas por vaga.
SELECT
    v.cargo AS Vaga,
    d.nome AS Departamento,
    COUNT(ca.id_candidatura) AS Total_Candidatos
FROM vagas v
         INNER JOIN departamentos d
                    ON d.id_departamento = v.id_departamento
         INNER JOIN candidaturas ca
                    ON ca.id_vaga = v.id_vaga
GROUP BY v.id_vaga, v.cargo, d.nome
ORDER BY Total_Candidatos DESC;

-- 19 Candidaturas encerradas sem aprovação.
SELECT
    c.nome AS Candidato,
    v.cargo AS Vaga,
    ca.etapa AS Etapa_Final,
    ca.data_candidatura AS Data_Candidatura
FROM candidaturas ca
         INNER JOIN candidatos c
                    ON c.id_candidato = ca.id_candidato
         INNER JOIN vagas v
                    ON v.id_vaga = ca.id_vaga
WHERE ca.status_candidatura = 0
ORDER BY ca.data_candidatura DESC;

-- 20 Detalhamento e custo da folha por departamento.
SELECT
    d.nome AS Departamento,
    COUNT(DISTINCT f.id_funcionario) AS Total_Funcionarios,
    SUM(fp.horas_trabalhadas) AS Total_Horas_Mes,
    SUM(v.salario_hora * fp.horas_trabalhadas) AS Total_Salario_Base,
    SUM(fp.horas_extras * v.salario_hora * 1.5) AS Custo_Estimado_Horas_Extras,
    (SUM(v.salario_hora * fp.horas_trabalhadas)
        + SUM(fp.horas_extras * v.salario_hora * 1.5)) * 1.40 AS Custo_Mensal_Total_Estimado
FROM departamentos d
         INNER JOIN vagas v
                    ON d.id_departamento = v.id_departamento
         INNER JOIN funcionarios f
                    ON f.id_vaga = v.id_vaga
         INNER JOIN contratos c
                    ON c.id_funcionario = f.id_funcionario
         INNER JOIN folhas_de_pagamentos fp
                    ON fp.id_funcionario = f.id_funcionario
WHERE c.status_contrato = 1
GROUP BY d.nome
ORDER BY Custo_Mensal_Total_Estimado DESC;

-- 21 Custo total da folha de pagamento da empresa.
SELECT
    SUM(fp.horas_trabalhadas * v.salario_hora) AS Total_Salario_Base,
    SUM(fp.horas_extras * v.salario_hora * 1.5) AS Total_Horas_Extras,
    SUM(fp.descontos) AS Total_Descontos,
    (SUM(fp.horas_trabalhadas * v.salario_hora)
         + SUM(fp.horas_extras * v.salario_hora * 1.5)
        - SUM(fp.descontos)) AS Custo_Total_Folha
FROM folhas_de_pagamentos fp
         INNER JOIN funcionarios f
                    ON f.id_funcionario = fp.id_funcionario
         INNER JOIN vagas v
                    ON v.id_vaga = f.id_vaga;

-- 22 Funcionários com salário acima da média da empresa.
SELECT
    f.id_funcionario,
    f.nome AS Funcionario,
    v.cargo AS Vaga,
    v.salario_hora AS Salario_Hora,
    d.nome AS Departamento
FROM funcionarios f
         INNER JOIN vagas v
                    ON v.id_vaga = f.id_vaga
         INNER JOIN departamentos d
                    ON d.id_departamento = v.id_departamento
WHERE v.salario_hora > (
    SELECT AVG(salario_hora) FROM vagas
)
ORDER BY v.salario_hora DESC;

-- 23 Departamentos com mais de 10 funcionários ativos.
SELECT
    d.nome AS Departamento,
    COUNT(DISTINCT f.id_funcionario) AS Total_Funcionarios_Ativos
FROM departamentos d
         INNER JOIN vagas v
                    ON v.id_departamento = d.id_departamento
         INNER JOIN funcionarios f
                    ON f.id_vaga = v.id_vaga
         INNER JOIN contratos c
                    ON c.id_funcionario = f.id_funcionario
WHERE c.status_contrato = 1
GROUP BY d.nome
HAVING Total_Funcionarios_Ativos > 10
ORDER BY Total_Funcionarios_Ativos DESC;

-- 24 Departamentos com média salarial superior a R$ 40/hora.
SELECT
    d.nome AS Departamento,
    AVG(v.salario_hora) AS Media_Salarial_Hora
FROM departamentos d
         INNER JOIN vagas v
                    ON v.id_departamento = d.id_departamento
GROUP BY d.nome
HAVING Media_Salarial_Hora > 40.00
ORDER BY Media_Salarial_Hora DESC;

-- 25 Funcionário(s) com o maior salário por hora.
SELECT
    f.nome AS Funcionario,
    v.cargo AS Vaga,
    v.salario_hora AS Salario_Hora
FROM funcionarios f
         INNER JOIN vagas v
                    ON v.id_vaga = f.id_vaga
WHERE v.salario_hora = (
    SELECT MAX(salario_hora) FROM vagas
);

-- 26 Funcionários contratados nos últimos 12 meses.
SELECT
    f.nome AS Funcionario,
    v.cargo AS Vaga,
    d.nome AS Departamento,
    c.data_inicio AS Data_Contratacao
FROM funcionarios f
         JOIN vagas v
              ON f.id_vaga = v.id_vaga
         JOIN departamentos d
              ON v.id_departamento = d.id_departamento
         JOIN contratos c
              ON f.id_funcionario = c.id_funcionario
WHERE c.data_inicio >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
ORDER BY c.data_inicio DESC;

-- 27 Média de tempo (em dias) do processo seletivo por departamento.
SELECT
    d.nome AS Departamento,
    ROUND(AVG(DATEDIFF(ca.data_finalizacao, ca.data_candidatura))) AS Media_Dias_Contratacao
FROM candidaturas ca
         JOIN vagas v
              ON ca.id_vaga = v.id_vaga
         JOIN departamentos d
              ON v.id_departamento = d.id_departamento
WHERE ca.data_finalizacao IS NOT NULL
GROUP BY d.nome
ORDER BY Media_Dias_Contratacao DESC;

-- 28 Total investido em salários por cargo em cada setor.
SELECT
    d.nome AS Departamento,
    v.cargo AS Cargo,
    COUNT(f.id_funcionario) AS Qtd_Funcionarios,
    SUM(fp.horas_trabalhadas * v.salario_hora) AS Folha_Base_Cargo
FROM departamentos d
         JOIN vagas v
              ON d.id_departamento = v.id_departamento
         JOIN funcionarios f
              ON v.id_vaga = f.id_vaga
         JOIN folhas_de_pagamentos fp
              ON f.id_funcionario = fp.id_funcionario
GROUP BY d.nome, v.cargo
ORDER BY d.nome, Folha_Base_Cargo DESC;

-- 29 Taxa de conversão de candidatos por vaga.
SELECT
    v.cargo AS Vaga,
    COUNT(ca.id_candidatura) AS Total_Candidatos,
    SUM(CASE WHEN ca.status_candidatura = 2 THEN 1 ELSE 0 END) AS Aprovados,
    ROUND((SUM(CASE WHEN ca.status_candidatura = 2 THEN 1 ELSE 0 END) * 100.0) / COUNT(ca.id_candidatura), 2) AS Taxa_Aprovacao_Pct
FROM vagas v
         JOIN candidaturas ca
              ON v.id_vaga = ca.id_vaga
GROUP BY v.id_vaga, v.cargo
HAVING Total_Candidatos > 0
ORDER BY Taxa_Aprovacao_Pct DESC;

-- 30 Funcionários que não registraram horas extras nem descontos no último mês.
SELECT
    f.nome AS Funcionario,
    v.cargo AS Vaga,
    d.nome AS Departamento
FROM funcionarios f
         JOIN vagas v
              ON f.id_vaga = v.id_vaga
         JOIN departamentos d
              ON v.id_departamento = d.id_departamento
         JOIN folhas_de_pagamentos fp
              ON f.id_funcionario = fp.id_funcionario
WHERE fp.horas_extras = 0
  AND fp.descontos = 0;