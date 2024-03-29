package com.hanlinbode.hlbd.exception;

public class ParamIncorrectException extends RuntimeException {
    private String message;
    private Object body;

    public ParamIncorrectException(String message) {
        super(message);
        this.message = message;
    }

    public ParamIncorrectException(String message, Object body) {
        super(message);
        this.message = message;
        this.body = body;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
