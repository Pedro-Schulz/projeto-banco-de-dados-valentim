-- 001_add_ativo_column.sql
-- Adds the 'ativo' (active) boolean column to all tables for soft delete support

ALTER TABLE departamentos
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE vagas
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE funcionarios
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE dados_bancarios
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE contratos
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE folhas_de_pagamentos
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE candidatos
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE candidaturas
    ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;