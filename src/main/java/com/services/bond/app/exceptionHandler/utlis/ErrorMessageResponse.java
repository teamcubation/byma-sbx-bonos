package com.services.bond.app.exceptionHandler.utlis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageResponse {
    private String exception;
    private String code;
    private String message;
    private List<String> details;
    private String path;
}