package com.rewardservice.service.model.exception;

public class DataFetchException extends RuntimeException {

    public DataFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
