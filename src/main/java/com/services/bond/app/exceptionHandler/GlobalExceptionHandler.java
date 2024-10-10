package com.services.bond.app.exceptionHandler;

import com.services.bond.app.application.service.exception.BondDuplicateException;
import com.services.bond.app.application.service.exception.BondNotFoundException;
import com.services.bond.app.exceptionHandler.utlis.ErrorMessageResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

import static com.services.bond.app.exceptionHandler.utlis.ErrorMessages.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BondNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleBondNotFoundException(HttpServletRequest req, Exception e) {
        return ErrorMessageResponse.builder()
                .exception(e.getClass().getSimpleName())
                .code(BONO_NOT_FOUND.getCode())
                .message(BONO_NOT_FOUND.getMessage())
                .path(req.getRequestURI())
                .build();
    }
    @ExceptionHandler(BondDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessageResponse handleBondDuplicateException(HttpServletRequest req, Exception e) {
        return ErrorMessageResponse.builder()
                .exception(e.getClass().getSimpleName())
                .code(DUPLICATE_BONO.getCode())
                .message(DUPLICATE_BONO.getMessage())
                .path(req.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessageResponse handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        return ErrorMessageResponse.builder()
                .exception(e.getClass().getSimpleName())
                .code(INVALID_BONO.getCode())
                .message(INVALID_BONO.getMessage())
                .path(req.getRequestURI())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessageResponse handleGenericError(HttpServletRequest req, Exception e) {
        return ErrorMessageResponse.builder()
                .exception(e.getClass().getSimpleName())
                .code(GENERIC_ERROR.getCode())
                .message(GENERIC_ERROR.getMessage())
                .path(req.getRequestURI())
                .build();
    }
}