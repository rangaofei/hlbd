package com.hanlinbode.hlbd.exception;

public class ResultAlreadyExistException extends RuntimeException {
    private String message;

    public ResultAlreadyExistException(String message) {
        super(message);
    }

}
