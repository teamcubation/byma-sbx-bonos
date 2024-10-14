package com.services.bond.app.application.service.exception;

public class BondDuplicateException extends RuntimeException {
    public BondDuplicateException(String message) {
        super(message);
    }
}
