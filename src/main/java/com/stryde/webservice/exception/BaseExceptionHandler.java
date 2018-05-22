package com.stryde.webservice.exception;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Exception to http response handler
 * 
 * @author candra
 **/
@ControllerAdvice(annotations = RestController.class)
public class BaseExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionHandler.class);
	private static final String VALIDATION_ERROR_MSG = "Validation error field: {0} - message: {1}";
	private static final String DATABASE_ERROR_MSG = "Error while performing database operation: {0}";
	private static final String UNEXPECTED_ERROR_MSG = "Unexpected technical error";

	// custom standard exception
	@ExceptionHandler(StrydeException.class)
	public ResponseEntity<String> handleCustomEvmsException(final StrydeException ex) {
		LOGGER.error("Handling EvmsException with error code: {} and status: {} caused by: {}", ex.getErrorCode(),
				ex.getErrorCode().getHttpStatus(), ex.getCause(), ex);
		return ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(ex.getErrorCode().toString());
	}
	// login fuckup exception
	@ExceptionHandler(AuthException.class)
	public ResponseEntity<?> handleAuthException(final AuthException ex) {
		LOGGER.error("Handling Exception with error code: {} and status: {} caused by: {}", ex.getErrorCode(),
				ex.getErrorCode().getHttpStatus(), ex.getCause(), ex);
		return  ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(ex.getErrorCode().toString());
	}

	// api exception
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<String> handleApiException(final ApiException ex) {
		LOGGER.error("Handling ApiException with error code: {} and status: {} caused by: {}", ex.getErrorCode(),
				ex.getErrorCode().getHttpStatus(), ex.getCause(), ex);
		return ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(ex.getErrorCode().toString());
	}

	// validation exception
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(final MethodArgumentNotValidException ex) {
		LOGGER.error("Handling ValidationException with message: {} caused by: {}", ex);
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> MessageFormat.format(VALIDATION_ERROR_MSG, e.getField(), e.getDefaultMessage()))
				.collect(Collectors.toList());
		return ResponseEntity.badRequest().body(errors);
	}

	// constaints violation exception
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleRequestParamsValidationException(final ConstraintViolationException ex) {
		LOGGER.error("Handling ValidationException with message: {} caused by: {}", ex);
		List<String> errors = ex.getConstraintViolations().stream()
				.map(e -> MessageFormat.format(VALIDATION_ERROR_MSG, e.getPropertyPath(), e.getMessage()))
				.collect(Collectors.toList());
		return ResponseEntity.badRequest().body(errors);
	}

	// database exceptions DataAccessException
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<?> handleDatabaseException(final DataAccessException ex) {
		LOGGER.error("Handling DatabaseException with message: {} caused by: {}", ex.getMessage(), ex.getCause(), ex);
		String msg = MessageFormat.format(DATABASE_ERROR_MSG, ex.getMessage());
		return ResponseEntity.unprocessableEntity().body(msg);
	}
	// standard exception
/*	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(final Exception ex) {
		LOGGER.error("Handling unexpected exception with message: {} caused by: {}", ex.getMessage(), ex.getCause(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(UNEXPECTED_ERROR_MSG);
	}*/


	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<?> handleNotFOundException(final NoHandlerFoundException ex) {
		LOGGER.error("Handling Exception for which no handler was found with message: {} caused by: {}", ex.getMessage(), ex.getCause(), ex);
		String msg = MessageFormat.format(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(UNEXPECTED_ERROR_MSG);
	}


}
