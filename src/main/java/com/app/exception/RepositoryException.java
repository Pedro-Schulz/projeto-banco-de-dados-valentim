package com.app.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String mensagem) {
        super("Erro na camada Repository!");
    }
}