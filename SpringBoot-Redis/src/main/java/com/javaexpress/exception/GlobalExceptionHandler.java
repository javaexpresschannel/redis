package com.javaexpress.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorAPI> handlException(ResourceNotFoundException ex) {
		ErrorAPI errorAPI = ErrorAPI.builder()
				.status(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.localDateTime(LocalDateTime.now())
				.details(ex.getMessage())
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.title("Client Error")
				.build();
		return new ResponseEntity<>(errorAPI,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorAPI> handlException(Exception ex) {
		ErrorAPI errorAPI = ErrorAPI.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.localDateTime(LocalDateTime.now())
				.details(ex.getMessage())
				//.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title("Something went wrong")
				.build();
		return new ResponseEntity<>(errorAPI,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorAPI>> handlException(MethodArgumentNotValidException ex) {
		List<ObjectError> objectErrors = ex.getAllErrors();
		
		List<ErrorAPI> errorAPIS = objectErrors.stream().map(temp -> 
					ErrorAPI.builder()
					.status(HttpStatus.BAD_REQUEST.getReasonPhrase())
					.localDateTime(LocalDateTime.now())
					.details(temp.getDefaultMessage())
					.statusCode(HttpStatus.BAD_REQUEST.value())
					.title("Validation Error")
					.build()).collect(Collectors.toList());
		return new ResponseEntity<>(errorAPIS,HttpStatus.BAD_REQUEST);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
