package com.app.exception;

public class FolhaDePagamentoVinculadaException extends RuntimeException {
    public FolhaDePagamentoVinculadaException() {
        super("ERRO! A entidade possui folhas de pagamento vinculadas!");
    }
}
