package com.stryde.webservice.exception;

public class AuthException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    protected AppErrorCode errorCode;

    public AuthException(AppErrorCode errorCode) {

        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public AppErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(AppErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
