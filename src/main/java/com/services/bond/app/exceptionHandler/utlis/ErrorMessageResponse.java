package com.services.bond.app.exceptionHandler.utlis;

import lombok.Builder;

import java.util.List;

@Builder
public class ErrorMessageResponse {
    private String exception;
    private String code;
    private String message;
    private List<String> details;
    private String path;
}