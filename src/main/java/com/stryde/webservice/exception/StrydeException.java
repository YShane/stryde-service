package com.stryde.webservice.exception;

/**
 * Custom exception for handling Evms business logic exceptions
 * 
 * @author candra
 **/
public class StrydeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected AppErrorCode errorCode;

	public StrydeException(AppErrorCode errorCode) {
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
