package com.app.exception;

public class ContratoVinculadoException extends RuntimeException {
    public ContratoVinculadoException() {
        super("ERRO! A entidade possui um contrato vinculado");
    }
}