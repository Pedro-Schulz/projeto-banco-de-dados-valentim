package com.app.exception;

public class ControllerException extends RuntimeException {
    public ControllerException(String message) {
        super(message);
    }
}
