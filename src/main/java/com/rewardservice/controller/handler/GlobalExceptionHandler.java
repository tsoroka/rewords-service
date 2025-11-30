package com.rewardservice.controller.handler;

import com.rewardservice.service.model.DataFetchException;
import com.rewardservice.service.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        String details = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.joining("; "));
        return new ErrorResponse(
                LocalDateTime.now(),
                "Validation Failed",
                details
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception ex) {
        return new ErrorResponse(
                LocalDateTime.now(),
                "An unexpected error occurred",
                ex.getMessage()
        );
    }

    @ExceptionHandler(DataFetchException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorResponse handleDataFetchException(DataFetchException ex) {
        return new ErrorResponse(
                LocalDateTime.now(),
                "Data Fetch Error from external system",
                ex.getMessage()
        );
    }
}