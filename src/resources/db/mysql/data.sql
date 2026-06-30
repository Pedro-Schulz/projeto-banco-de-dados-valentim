-- 1. DEPARTAMENTOS (6 Departamentos)
INSERT INTO departamentos (nome, gastos, retorno) VALUES
('Tecnologia da Informação', 180000.00, 450000.00),
('Recursos Humanos', 45000.00, 0.00),
('Comercial e Vendas', 150000.00, 850000.00),
('Financeiro e Jurídico', 55000.00, 0.00),
('Marketing e Growth', 70000.00, 200000.00),
('Operações e Suporte', 120000.00, 150000.00);

-- 2. VAGAS (70 Vagas - 65 Ocupadas e 5 Abertas)
INSERT INTO vagas (turno, salario_hora, cargo, id_departamento) VALUES

-- TI (IDs 1 a 10)
('Integral', 85.00, 'Tech Lead', 1), ('Integral', 65.00, 'Dev Backend Sênior', 1), ('Integral', 65.00, 'Dev Frontend Sênior', 1),
('Integral', 45.00, 'Dev Fullstack Pleno', 1), ('Integral', 45.00, 'Dev Fullstack Pleno', 1), ('Integral', 45.00, 'QA Analyst Pleno', 1),
('Integral', 30.00, 'Dev Frontend Jr', 1), ('Integral', 30.00, 'Dev Backend Jr', 1), ('Integral', 55.00, 'DevOps Engineer', 1),
('Integral', 70.00, 'Data Engineer', 1),

-- RH (IDs 11 a 14)
('Integral', 60.00, 'HR Business Partner', 2), ('Integral', 35.00, 'Analista de R&S Sênior', 2),
('Integral', 25.00, 'Analista de DP Pleno', 2), ('Integral', 20.00, 'Assistente de RH', 2),

-- Comercial (IDs 15 a 30)
('Integral', 75.00, 'Gerente Comercial', 3), ('Integral', 45.00, 'Coordenador de Vendas', 3),
('Integral', 30.00, 'Executivo de Contas', 3), ('Integral', 30.00, 'Executivo de Contas', 3), ('Integral', 30.00, 'Executivo de Contas', 3),
('Integral', 30.00, 'Executivo de Contas', 3), ('Integral', 30.00, 'Executivo de Contas', 3), ('Integral', 30.00, 'Executivo de Contas', 3),
('Integral', 20.00, 'SDR', 3), ('Integral', 20.00, 'SDR', 3), ('Integral', 20.00, 'SDR', 3),
('Integral', 20.00, 'SDR', 3), ('Integral', 20.00, 'SDR', 3), ('Integral', 20.00, 'SDR', 3),
('Integral', 25.00, 'Sales Ops', 3), ('Integral', 25.00, 'Customer Success Sênior', 3),

-- Financeiro (IDs 31 a 35)
('Integral', 70.00, 'Controller Financeiro', 4), ('Integral', 40.00, 'Analista Financeiro Sênior', 4),
('Integral', 30.00, 'Analista Fiscal Pleno', 4), ('Integral', 25.00, 'Assistente de Faturamento', 4), ('Integral', 25.00, 'Assistente de Cobrança', 4),

-- Marketing (IDs 36 a 42)
('Integral', 65.00, 'Gerente de Marketing', 5), ('Integral', 40.00, 'Especialista em SEO', 5), ('Integral', 40.00, 'Analista de Tráfego Sênior', 5),
('Integral', 30.00, 'Copywriter Pleno', 5), ('Integral', 35.00, 'Designer Sênior', 5), ('Integral', 25.00, 'Designer Pleno', 5),
('Vespertino', 15.00, 'Estagiário de Redes Sociais', 5),

-- Operações e Suporte (IDs 43 a 65)
('Integral', 50.00, 'Gerente de Operações', 6), ('Integral', 35.00, 'Supervisor de Atendimento', 6), ('Integral', 35.00, 'Supervisor de Atendimento', 6),
('Integral', 18.00, 'Agente de Suporte N1', 6), ('Integral', 18.00, 'Agente de Suporte N1', 6), ('Integral', 18.00, 'Agente de Suporte N1', 6),
('Integral', 18.00, 'Agente de Suporte N1', 6), ('Integral', 18.00, 'Agente de Suporte N1', 6), ('Integral', 18.00, 'Agente de Suporte N1', 6),
('Integral', 18.00, 'Agente de Suporte N1', 6), ('Integral', 18.00, 'Agente de Suporte N1', 6), ('Integral', 18.00, 'Agente de Suporte N1', 6),
('Integral', 18.00, 'Agente de Suporte N1', 6), ('Noturno', 20.00, 'Agente de Suporte N1', 6), ('Noturno', 20.00, 'Agente de Suporte N1', 6),
('Noturno', 20.00, 'Agente de Suporte N1', 6), ('Noturno', 20.00, 'Agente de Suporte N1', 6), ('Integral', 25.00, 'Agente de Suporte N2', 6),
('Integral', 25.00, 'Agente de Suporte N2', 6), ('Integral', 25.00, 'Agente de Suporte N2', 6), ('Integral', 25.00, 'Agente de Suporte N2', 6),
('Noturno', 28.00, 'Agente de Suporte N2', 6), ('Noturno', 28.00, 'Agente de Suporte N2', 6),

-- VAGAS ABERTAS (IDs 66 a 70)
('Integral', 30.00, 'Executivo de Contas', 3), ('Integral', 20.00, 'SDR', 3),
('Integral', 18.00, 'Agente de Suporte N1', 6), ('Noturno', 20.00, 'Agente de Suporte N1', 6), ('Integral', 45.00, 'Dev Fullstack Pleno', 1);

-- 3. FUNCIONÁRIOS (65 Colaboradores)
INSERT INTO funcionarios (nome, data_nascimento, cpf, cep, email, telefone, estadoCivil, genero, id_vaga) VALUES
('Carlos Silva', '1985-04-12', '10120230340', '01001000', 'carlos.silva@emp.com', '11988881001', 'Casado', 'M', 1),
('Ana Souza', '1990-08-22', '10120230341', '01001001', 'ana.souza@emp.com', '11988881002', 'Solteiro', 'F', 2),
('Bruno Oliveira', '1988-11-05', '10120230342', '01001002', 'bruno.oli@emp.com', '11988881003', 'Casado', 'M', 3),
('Mariana Costa', '1992-02-15', '10120230343', '01001003', 'mari.costa@emp.com', '11988881004', 'Solteiro', 'F', 4),
('Felipe Santos', '1995-07-30', '10120230344', '01001004', 'felipe.santos@emp.com', '11988881005', 'Solteiro', 'M', 5),
('Juliana Lima', '1993-12-10', '10120230345', '01001005', 'ju.lima@emp.com', '11988881006', 'Casado', 'F', 6),
('Lucas Pereira', '1998-03-25', '10120230346', '01001006', 'lucas.pereira@emp.com', '11988881007', 'Solteiro', 'M', 7),
('Fernanda Alves', '1999-09-08', '10120230347', '01001007', 'fe.alves@emp.com', '11988881008', 'Solteiro', 'F', 8),
('Roberto Rocha', '1987-05-20', '10120230348', '01001008', 'roberto.rocha@emp.com', '11988881009', 'Divorciado', 'M', 9),
('Camila Gomes', '1991-01-18', '10120230349', '01001009', 'camila.gomes@emp.com', '11988881010', 'Casado', 'F', 10),
('Eduardo Martins', '1980-06-14', '10120230350', '01001010', 'edu.martins@emp.com', '11988881011', 'Casado', 'M', 11),
('Patricia Ribeiro', '1986-10-02', '10120230351', '01001011', 'patty.ribeiro@emp.com', '11988881012', 'Divorciado', 'F', 12),
('Diego Mendes', '1994-04-28', '10120230352', '01001012', 'diego.mendes@emp.com', '11988881013', 'Solteiro', 'M', 13),
('Leticia Carvalho', '2000-11-12', '10120230353', '01001013', 'leticia.car@emp.com', '11988881014', 'Solteiro', 'F', 14),
('Ricardo Azevedo', '1978-08-05', '10120230354', '01001014', 'ricardo.azevedo@emp.com', '11988881015', 'Casado', 'M', 15),
('Aline Freitas', '1985-12-20', '10120230355', '01001015', 'aline.freitas@emp.com', '11988881016', 'Casado', 'F', 16),
('Marcelo Nogueira', '1990-03-15', '10120230356', '01001016', 'marcelo.nog@emp.com', '11988881017', 'Solteiro', 'M', 17),
('Beatriz Barros', '1992-07-22', '10120230357', '01001017', 'beatriz.barros@emp.com', '11988881018', 'Solteiro', 'F', 18),
('Thiago Moraes', '1989-09-10', '10120230358', '01001018', 'thiago.moraes@emp.com', '11988881019', 'Casado', 'M', 19),
('Amanda Castro', '1995-01-05', '10120230359', '01001019', 'amanda.castro@emp.com', '11988881020', 'Solteiro', 'F', 20),
('Gustavo Pires', '1991-06-18', '10120230360', '01001020', 'gustavo.pires@emp.com', '11988881021', 'Solteiro', 'M', 21),
('Natalia Silva', '1994-11-25', '10120230361', '01001021', 'natalia.silva@emp.com', '11988881022', 'Casado', 'F', 22),
('Leandro Farias', '1997-02-14', '10120230362', '01001022', 'leandro.farias@emp.com', '11988881023', 'Solteiro', 'M', 23),
('Carolina Dias', '1998-05-30', '10120230363', '01001023', 'carol.dias@emp.com', '11988881024', 'Solteiro', 'F', 24),
('Vitor Hugo', '1999-10-08', '10120230364', '01001024', 'vitor.hugo@emp.com', '11988881025', 'Solteiro', 'M', 25),
('Isabela Monteiro', '2001-04-20', '10120230365', '01001025', 'isabela.mont@emp.com', '11988881026', 'Solteiro', 'F', 26),
('Renato Cruz', '2000-08-12', '10120230366', '01001026', 'renato.cruz@emp.com', '11988881027', 'Solteiro', 'M', 27),
('Tatiana Vieira', '1996-12-05', '10120230367', '01001027', 'tati.vieira@emp.com', '11988881028', 'Casado', 'F', 28),
('Andre Moura', '1988-03-22', '10120230368', '01001028', 'andre.moura@emp.com', '11988881029', 'Divorciado', 'M', 29),
('Priscila Ramos', '1990-07-15', '10120230369', '01001029', 'pri.ramos@emp.com', '11988881030', 'Casado', 'F', 30),
('Fernando Torres', '1975-11-10', '10120230370', '01001030', 'fernando.torres@emp.com', '11988881031', 'Casado', 'M', 31),
('Vanessa Nunes', '1982-01-28', '10120230371', '01001031', 'vanessa.nunes@emp.com', '11988881032', 'Casado', 'F', 32),
('Rodrigo Teixeira', '1987-06-05', '10120230372', '01001032', 'rodrigo.teixeira@emp.com', '11988881033', 'Solteiro', 'M', 33),
('Luana Borges', '1993-09-18', '10120230373', '01001033', 'luana.borges@emp.com', '11988881034', 'Solteiro', 'F', 34),
('Igor Machado', '1995-12-30', '10120230374', '01001034', 'igor.machado@emp.com', '11988881035', 'Solteiro', 'M', 35),
('Silvia Campos', '1983-04-14', '10120230375', '01001035', 'silvia.campos@emp.com', '11988881036', 'Casado', 'F', 36),
('Marcos Viana', '1989-08-25', '10120230376', '01001036', 'marcos.viana@emp.com', '11988881037', 'Casado', 'M', 37),
('Elaine Batista', '1992-11-08', '10120230377', '01001037', 'elaine.batista@emp.com', '11988881038', 'Divorciado', 'F', 38),
('Daniel Cardoso', '1996-02-20', '10120230378', '01001038', 'daniel.cardoso@emp.com', '11988881039', 'Solteiro', 'M', 39),
('Raquel Peixoto', '1994-05-15', '10120230379', '01001039', 'raquel.peixoto@emp.com', '11988881040', 'Solteiro', 'F', 40),
('Guilherme Neves', '1998-10-10', '10120230380', '01001040', 'gui.neves@emp.com', '11988881041', 'Solteiro', 'M', 41),
('Sabrina Leite', '2002-01-22', '10120230381', '01001041', 'sabrina.leite@emp.com', '11988881042', 'Solteiro', 'F', 42),
('Jorge Diniz', '1976-06-05', '10120230382', '01001042', 'jorge.diniz@emp.com', '11988881043', 'Casado', 'M', 43),
('Clara Sampaio', '1985-09-18', '10120230383', '01001043', 'clara.sampaio@emp.com', '11988881044', 'Casado', 'F', 44),
('Fabio Tavares', '1988-12-30', '10120230384', '01001044', 'fabio.tavares@emp.com', '11988881045', 'Divorciado', 'M', 45),
('Michele Gusmão', '1990-04-12', '10120230385', '01001045', 'michele.gusmao@emp.com', '11988881046', 'Solteiro', 'F', 46),
('Arthur Assis', '1993-07-25', '10120230386', '01001046', 'arthur.assis@emp.com', '11988881047', 'Solteiro', 'M', 47),
('Bianca Pacheco', '1995-10-08', '10120230387', '01001047', 'bianca.pacheco@emp.com', '11988881048', 'Casado', 'F', 48),
('Caio Furtado', '1997-01-20', '10120230388', '01001048', 'caio.furtado@emp.com', '11988881049', 'Solteiro', 'M', 49),
('Debora Maciel', '1999-05-15', '10120230389', '01001049', 'debora.maciel@emp.com', '11988881050', 'Solteiro', 'F', 50),
('Elias Cordeiro', '2001-08-10', '10120230390', '01001050', 'elias.cordeiro@emp.com', '11988881051', 'Solteiro', 'M', 51),
('Flavia Galvão', '1984-11-22', '10120230391', '01001051', 'flavia.galvao@emp.com', '11988881052', 'Casado', 'F', 52),
('Gabriel Lemos', '1987-02-14', '10120230392', '01001052', 'gabriel.lemos@emp.com', '11988881053', 'Casado', 'M', 53),
('Helena Bentes', '1991-06-30', '10120230393', '01001053', 'helena.bentes@emp.com', '11988881054', 'Solteiro', 'F', 54),
('Ivan Queiroz', '1994-09-18', '10120230394', '01001054', 'ivan.queiroz@emp.com', '11988881055', 'Solteiro', 'M', 55),
('Janaina Pinho', '1996-12-05', '10120230395', '01001055', 'janaina.pinho@emp.com', '11988881056', 'Solteiro', 'F', 56),
('Kleber Sales', '1998-03-22', '10120230396', '01001056', 'kleber.sales@emp.com', '11988881057', 'Solteiro', 'M', 57),
('Lara Guimarães', '2000-07-15', '10120230397', '01001057', 'lara.guimaraes@emp.com', '11988881058', 'Solteiro', 'F', 58),
('Milton Faria', '1982-10-10', '10120230398', '01001058', 'milton.faria@emp.com', '11988881059', 'Casado', 'M', 59),
('Nadia Paiva', '1986-01-28', '10120230399', '01001059', 'nadia.paiva@emp.com', '11988881060', 'Divorciado', 'F', 60),
('Otavio Salgado', '1989-05-05', '10120230400', '01001060', 'otavio.salgado@emp.com', '11988881061', 'Casado', 'M', 61),
('Paula Macedo', '1992-08-18', '10120230401', '01001061', 'paula.macedo@emp.com', '11988881062', 'Solteiro', 'F', 62),
('Quintino Neto', '1995-11-30', '10120230402', '01001062', 'quintino.neto@emp.com', '11988881063', 'Solteiro', 'M', 63),
('Rita Gouveia', '1998-02-14', '10120230403', '01001063', 'rita.gouveia@emp.com', '11988881064', 'Solteiro', 'F', 64),
('Samuel Braga', '2001-06-20', '10120230404', '01001064', 'samuel.braga@emp.com', '11988881065', 'Solteiro', 'M', 65);

-- 4. DADOS BANCÁRIOS (65 Contas)
INSERT INTO dados_bancarios (numero_conta, instituicao_bancaria, agencia_bancaria, id_funcionario) VALUES
(10001, 'Banco do Brasil', 1010, 1), (10002, 'Itaú Unibanco', 1010, 2), (10003, 'Bradesco', 1010, 3), (10004, 'Santander', 1010, 4), (10005, 'Nubank', 1010, 5),
(10006, 'Caixa Econômica', 1010, 6), (10007, 'Banco Inter', 1010, 7), (10008, 'Banco do Brasil', 1010, 8), (10009, 'Itaú Unibanco', 1010, 9), (10010, 'Bradesco', 1010, 10),
(10011, 'Santander', 2020, 11), (10012, 'Nubank', 2020, 12), (10013, 'Caixa Econômica', 2020, 13), (10014, 'Banco Inter', 2020, 14), (10015, 'Banco do Brasil', 2020, 15),
(10016, 'Itaú Unibanco', 2020, 16), (10017, 'Bradesco', 2020, 17), (10018, 'Santander', 2020, 18), (10019, 'Nubank', 2020, 19), (10020, 'Caixa Econômica', 2020, 20),
(10021, 'Banco Inter', 3030, 21), (10022, 'Banco do Brasil', 3030, 22), (10023, 'Itaú Unibanco', 3030, 23), (10024, 'Bradesco', 3030, 24), (10025, 'Santander', 3030, 25),
(10026, 'Nubank', 3030, 26), (10027, 'Caixa Econômica', 3030, 27), (10028, 'Banco Inter', 3030, 28), (10029, 'Banco do Brasil', 3030, 29), (10030, 'Itaú Unibanco', 3030, 30),
(10031, 'Bradesco', 4040, 31), (10032, 'Santander', 4040, 32), (10033, 'Nubank', 4040, 33), (10034, 'Caixa Econômica', 4040, 34), (10035, 'Banco Inter', 4040, 35),
(10036, 'Banco do Brasil', 4040, 36), (10037, 'Itaú Unibanco', 4040, 37), (10038, 'Bradesco', 4040, 38), (10039, 'Santander', 4040, 39), (10040, 'Nubank', 4040, 40),
(10041, 'Caixa Econômica', 5050, 41), (10042, 'Banco Inter', 5050, 42), (10043, 'Banco do Brasil', 5050, 43), (10044, 'Itaú Unibanco', 5050, 44), (10045, 'Bradesco', 5050, 45),
(10046, 'Santander', 5050, 46), (10047, 'Nubank', 5050, 47), (10048, 'Caixa Econômica', 5050, 48), (10049, 'Banco Inter', 5050, 49), (10050, 'Banco do Brasil', 5050, 50),
(10051, 'Itaú Unibanco', 6060, 51), (10052, 'Bradesco', 6060, 52), (10053, 'Santander', 6060, 53), (10054, 'Nubank', 6060, 54), (10055, 'Caixa Econômica', 6060, 55),
(10056, 'Banco Inter', 6060, 56), (10057, 'Banco do Brasil', 6060, 57), (10058, 'Itaú Unibanco', 6060, 58), (10059, 'Bradesco', 6060, 59), (10060, 'Santander', 6060, 60),
(10061, 'Nubank', 7070, 61), (10062, 'Caixa Econômica', 7070, 62), (10063, 'Banco Inter', 7070, 63), (10064, 'Banco do Brasil', 7070, 64), (10065, 'Itaú Unibanco', 7070, 65);

-- 5. CONTRATOS (65 Vínculos Ativos)
INSERT INTO contratos (status_contrato, data_emissao, prazo, id_funcionario) VALUES
(1, '2023-01-10', 48, 1), (1, '2024-02-15', 36, 2), (1, '2022-03-20', 60, 3), (1, '2025-04-25', 24, 4), (1, '2026-05-01', 12, 5),
(1, '2021-06-10', 60, 6), (1, '2024-07-15', 36, 7), (1, '2023-08-20', 48, 8), (1, '2022-09-25', 60, 9), (1, '2025-10-01', 24, 10),
(1, '2020-11-10', 72, 11), (1, '2023-12-15', 48, 12), (1, '2024-01-20', 36, 13), (1, '2026-02-25', 12, 14), (1, '2019-03-01', 84, 15),
(1, '2022-04-10', 60, 16), (1, '2025-05-15', 24, 17), (1, '2024-06-20', 36, 18), (1, '2021-07-25', 60, 19), (1, '2026-08-01', 12, 20),
(1, '2023-09-10', 48, 21), (1, '2025-10-15', 24, 22), (1, '2024-11-20', 36, 23), (1, '2026-12-25', 12, 24), (1, '2022-01-01', 60, 25),
(1, '2025-02-10', 24, 26), (1, '2024-03-15', 36, 27), (1, '2023-04-20', 48, 28), (1, '2021-05-25', 60, 29), (1, '2022-06-01', 60, 30),
(1, '2018-07-10', 96, 31), (1, '2020-08-15', 72, 32), (1, '2023-09-20', 48, 33), (1, '2024-10-25', 36, 34), (1, '2026-11-01', 12, 35),
(1, '2021-12-10', 60, 36), (1, '2022-01-15', 60, 37), (1, '2024-02-20', 36, 38), (1, '2025-03-25', 24, 39), (1, '2023-04-01', 48, 40),
(1, '2026-05-10', 12, 41), (1, '2025-06-15', 24, 42), (1, '2019-07-20', 84, 43), (1, '2021-08-25', 60, 44), (1, '2022-09-01', 60, 45),
(1, '2024-10-10', 36, 46), (1, '2023-11-15', 48, 47), (1, '2025-12-20', 24, 48), (1, '2026-01-25', 12, 49), (1, '2024-02-01', 36, 50),
(1, '2025-03-10', 24, 51), (1, '2022-04-15', 60, 52), (1, '2023-05-20', 48, 53), (1, '2024-06-25', 36, 54), (1, '2026-07-01', 12, 55),
(1, '2025-08-10', 24, 56), (1, '2024-09-15', 36, 57), (1, '2023-10-20', 48, 58), (1, '2021-11-25', 60, 59), (1, '2022-12-01', 60, 60),
(1, '2024-01-10', 36, 61), (1, '2025-02-15', 24, 62), (1, '2026-03-20', 12, 63), (1, '2023-04-25', 48, 64), (1, '2024-05-01', 36, 65);

-- 6. FOLHAS DE PAGAMENTOS (65 Lançamentos)
INSERT INTO folhas_de_pagamentos (horas_trabalhadas, data_emissao, descontos, horas_extras, id_funcionario) VALUES
(160, '2026-06-26', 350.00, 10, 1), (160, '2026-06-26', 150.00, 0, 2), (160, '2026-06-26', 220.00, 5, 3), (150, '2026-06-26', 500.00, 0, 4), (170, '2026-06-26', 120.00, 15, 5),
(160, '2026-06-26', 300.00, 2, 6), (160, '2026-06-26', 100.00, 0, 7), (160, '2026-06-26', 180.00, 8, 8), (158, '2026-06-26', 450.00, 0, 9), (160, '2026-06-26', 200.00, 1, 10),
(160, '2026-06-26', 400.00, 0, 11), (160, '2026-06-26', 250.00, 0, 12), (160, '2026-06-26', 150.00, 12, 13), (160, '2026-06-26', 50.00, 0, 14), (160, '2026-06-26', 600.00, 0, 15),
(160, '2026-06-26', 300.00, 20, 16), (160, '2026-06-26', 200.00, 15, 17), (160, '2026-06-26', 150.00, 10, 18), (160, '2026-06-26', 100.00, 5, 19), (160, '2026-06-26', 50.00, 0, 20),
(160, '2026-06-26', 150.00, 2, 21), (160, '2026-06-26', 250.00, 4, 22), (160, '2026-06-26', 350.00, 6, 23), (160, '2026-06-26', 150.00, 8, 24), (160, '2026-06-26', 100.00, 10, 25),
(160, '2026-06-26', 120.00, 12, 26), (160, '2026-06-26', 180.00, 14, 27), (160, '2026-06-26', 200.00, 16, 28), (160, '2026-06-26', 300.00, 18, 29), (160, '2026-06-26', 400.00, 20, 30),
(160, '2026-06-26', 500.00, 0, 31), (160, '2026-06-26', 350.00, 5, 32), (160, '2026-06-26', 250.00, 0, 33), (160, '2026-06-26', 150.00, 0, 34), (160, '2026-06-26', 100.00, 2, 35),
(160, '2026-06-26', 450.00, 0, 36), (160, '2026-06-26', 250.00, 0, 37), (160, '2026-06-26', 350.00, 10, 38), (160, '2026-06-26', 150.00, 5, 39), (160, '2026-06-26', 200.00, 0, 40),
(160, '2026-06-26', 150.00, 0, 41), (100, '2026-06-26', 50.00, 0, 42), (160, '2026-06-26', 400.00, 0, 43), (160, '2026-06-26', 200.00, 10, 44), (160, '2026-06-26', 300.00, 5, 45),
(160, '2026-06-26', 150.00, 2, 46), (160, '2026-06-26', 100.00, 8, 47), (160, '2026-06-26', 120.00, 15, 48), (160, '2026-06-26', 180.00, 20, 49), (160, '2026-06-26', 250.00, 10, 50),
(160, '2026-06-26', 350.00, 5, 51), (160, '2026-06-26', 150.00, 2, 52), (160, '2026-06-26', 100.00, 0, 53), (180, '2026-06-26', 200.00, 20, 54), (170, '2026-06-26', 150.00, 10, 55),
(165, '2026-06-26', 100.00, 5, 56), (160, '2026-06-26', 50.00, 0, 57), (160, '2026-06-26', 150.00, 2, 58), (160, '2026-06-26', 250.00, 4, 59), (160, '2026-06-26', 350.00, 6, 60),
(160, '2026-06-26', 450.00, 8, 61), (160, '2026-06-26', 150.00, 10, 62), (160, '2026-06-26', 100.00, 12, 63), (160, '2026-06-26', 200.00, 14, 64), (160, '2026-06-26', 300.00, 16, 65);

-- 7. CANDIDATOS (15 Candidatos no Banco de Talentos)
INSERT INTO candidatos (nome, cpf, cep, email, telefone, genero, estado_civil, data_nascimento) VALUES
('Tiago Lopes', '10120230501', '01001070', 'tiago.lopes@email.com', '11977771001', 'M', 'Solteiro', '1994-03-12'),
('Sofia Martins', '10120230502', '01001071', 'sofia.martins@email.com', '11977771002', 'F', 'Casado', '1990-08-25'),
('Pedro Henrique', '10120230503', '01001072', 'pedro.henrique@email.com', '11977771003', 'M', 'Solteiro', '1996-11-05'),
('Laura Silva', '10120230504', '01001073', 'laura.silva@email.com', '11977771004', 'F', 'Divorciado', '1988-02-18'),
('Marcos Paulo', '10120230505', '01001074', 'marcos.paulo@email.com', '11977771005', 'M', 'Casado', '1992-07-30'),
('Alice Fernandes', '10120230506', '01001075', 'alice.fernandes@email.com', '11977771006', 'F', 'Solteiro', '1999-12-10'),
('João Costa', '10120230507', '01001076', 'joao.costa@email.com', '11977771007', 'M', 'Solteiro', '2001-04-22'),
('Carolina Mendes', '10120230508', '01001077', 'carol.mendes@email.com', '11977771008', 'F', 'Casado', '1985-09-14'),
('Vitor Almeida', '10120230509', '01001078', 'vitor.almeida@email.com', '11977771009', 'M', 'Solteiro', '1997-05-20'),
('Livia Nunes', '10120230510', '01001079', 'livia.nunes@email.com', '11977771010', 'F', 'Solteiro', '2000-01-15'),
('Rafael Santos', '10120230511', '01001080', 'rafael.santos@email.com', '11977771011', 'M', 'Casado', '1993-06-08'),
('Gabriela Dias', '10120230512', '01001081', 'gabi.dias@email.com', '11977771012', 'F', 'Solteiro', '1995-10-02'),
('Rodrigo Carvalho', '10120230513', '01001082', 'rodrigo.carvalho@email.com', '11977771013', 'M', 'Divorciado', '1989-04-28'),
('Marina Rocha', '10120230514', '01001083', 'marina.rocha@email.com', '11977771014', 'F', 'Solteiro', '1998-11-12'),
('Bruno Teixeira', '10120230515', '01001084', 'bruno.teixeira@email.com', '11977771015', 'M', 'Casado', '1991-08-05');

-- 8. CANDIDATURAS (Processos para as 5 Vagas Abertas)
INSERT INTO candidaturas (status_candidatura, data_candidatura, prazo, etapa, id_vaga, id_candidato) VALUES
-- Vaga 66 (Executivo de Contas)
(1, '2026-06-10', '2026-07-10', 'Entrevista com o Gestor', 66, 3),
(1, '2026-06-12', '2026-07-10', 'Teste de Perfil', 66, 4),
(0, '2026-06-05', '2026-07-10', 'Reprovado na Triagem', 66, 13),
-- Vaga 67 (SDR)
(1, '2026-06-15', '2026-07-15', 'Dinâmica de Grupo', 67, 5),
(1, '2026-06-16', '2026-07-15', 'Dinâmica de Grupo', 67, 6),
(1, '2026-06-20', '2026-07-15', 'Triagem Inicial', 67, 14),
-- Vaga 68 (Agente de Suporte N1 - Diurno)
(1, '2026-06-18', '2026-07-20', 'Entrevista Técnica', 68, 7),
(1, '2026-06-19', '2026-07-20', 'Entrevista Técnica', 68, 8),
(0, '2026-06-01', '2026-07-20', 'Desistência do Candidato', 68, 15),
-- Vaga 69 (Agente de Suporte N1 - Noturno)
(1, '2026-06-21', '2026-07-25', 'Teste Prático', 69, 9),
(1, '2026-06-22', '2026-07-25', 'Teste Prático', 69, 10),
(1, '2026-06-23', '2026-07-25', 'Triagem de Currículo', 69, 11),
-- Vaga 70 (Dev Fullstack Pleno - TI)
(1, '2026-06-10', '2026-07-30', 'Code Challenge', 70, 1),
(1, '2026-06-14', '2026-07-30', 'Entrevista com Tech Lead', 70, 2),
(0, '2026-06-08', '2026-07-30', 'Reprovado no Code Challenge', 70, 12);