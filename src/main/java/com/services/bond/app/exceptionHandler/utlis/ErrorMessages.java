package com.services.bond.app.exceptionHandler.utlis;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    BOND_NOT_FOUND("ERROR_BONO_001", "Bond not found."),
    DUPLICATE_BOND("ERROR_BONO_002", "Bond is duplicated."),
    INVALID_BOND("ERR_BONO_003", "Invalid bond parameters."),
    GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred.");


    private final String code;
    private final String message;

    ErrorMessages(String code, String message){
        this.code = code;
        this.message = message;
    }

}
