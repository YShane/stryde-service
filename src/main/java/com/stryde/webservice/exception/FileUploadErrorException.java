package com.stryde.webservice.exception;

public class FileUploadErrorException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    protected AppErrorCode errorCode;

    public FileUploadErrorException(AppErrorCode errorCode) {

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


