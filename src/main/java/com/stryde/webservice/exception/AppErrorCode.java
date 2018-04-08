package com.stryde.webservice.exception;

import org.springframework.http.HttpStatus;

public enum AppErrorCode {

	WRONG_CREDENTIAL(HttpStatus.BAD_REQUEST),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND),
	RECORD_NOT_FOUND(HttpStatus.NOT_FOUND), 
	EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST),
	USERNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST),
	UPLOAD_FILE_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR);
	
	private HttpStatus httpStatus;
	
	private AppErrorCode(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
