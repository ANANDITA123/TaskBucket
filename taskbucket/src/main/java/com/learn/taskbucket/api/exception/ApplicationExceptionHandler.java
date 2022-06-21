package com.learn.taskbucket.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler(Exception.class)
    @ResponseBody
	public ResponseEntity<BusinessException> handleException(final Exception ex) {
		log.error("Unknown error {}", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
	public ResponseEntity<BusinessException> handleParameterValidatorException(
			final MethodArgumentNotValidException ex) {
		log.error("An internal exception occurred. Cause {}", ex.getMessage(), ex);
		final List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		final BusinessException errorResponse = new BusinessException(
				TaskBucketErrors.VALIDATION_ERROR.getErrorCode(), errors.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(TaskBucketException.class)
    @ResponseBody
	public ResponseEntity<BusinessException> handleParameterValidatorException(final TaskBucketException ex) {
		log.error("An internal exception occurred. Cause {}", ex.getMessage(), ex);
		final BusinessException errorResponse = new BusinessException(ex.getErrorCode(), ex.getErrorMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    @ResponseBody
	public ResponseEntity<BusinessException> handleArrayIndexOutOfBoundsException(final TaskBucketException ex) {
		log.error("An internal exception occurred. Cause {}", ex.getMessage(), ex);
		final BusinessException errorResponse = new BusinessException(ex.getErrorCode(), ex.getErrorMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	
	
	

}
