package com.app.exception;

public class DadosBancariosVinculadosException extends RuntimeException {
    public DadosBancariosVinculadosException() {
        super("ERRO! A entidade possui dados bancários vinculados");
    }
}
