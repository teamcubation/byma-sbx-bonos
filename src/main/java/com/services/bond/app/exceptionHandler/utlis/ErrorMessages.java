package com.services.bond.app.exceptionHandler.utlis;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    BOND_NOT_FOUND("ERROR_BONO_001", "Bond not found."),
    DUPLICATE_BOND("ERROR_BONO_002", "Bond is duplicated."),
    INVALID_BOND("ERR_BONO_003", "Invalid bond parameters."),
    VALUE_NEGATIVE("ERR_BONO_004", "Value cannot be negative."),
    URL_NOT_FOUND("ERR_GEN_002", "URL not found"),
    GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred.");


    private final String code;
    private final String message;

    ErrorMessages(String code, String message){
        this.code = code;
        this.message = message;
    }

}
