package com.project.ShareBook.exception;

public class JWTException extends RuntimeException {
    private final JWTErrorType errorType;

    public JWTException(JWTErrorType errorType) {
        super(errorType.getDesc());
        this.errorType = errorType;
    }
}