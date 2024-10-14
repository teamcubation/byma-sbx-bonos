package com.services.bond.app.application.service.exception;


public class BondNotFoundException extends RuntimeException {
    public BondNotFoundException(String message) {
        super(message);
    }
    public BondNotFoundException() {
        super();
    }
}