package com.services.bond.app.application.service.exception;

public class ValueNegativeException extends RuntimeException {
    public ValueNegativeException(String message) {
        super(message);
    }
    public ValueNegativeException() {
        super();
    }
}
