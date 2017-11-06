package com.hanlinbode.hlbd.exception;

public class ResultNotFoundException extends RuntimeException {
    private String message;

    public ResultNotFoundException(String message) {
        super(message);
    }

}
