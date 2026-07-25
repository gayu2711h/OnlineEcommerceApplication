package com.ecom.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.custom_exception.ApiException;
import com.ecom.dtos.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleException(ApiException e)
	{
		System.out.println("Inside Global Exception Handler !!");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Failure", e.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(
	        MethodArgumentNotValidException ex) {

	    String error = ex.getBindingResult()
	                     .getFieldError()
	                     .getDefaultMessage();

	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(new ApiResponse("Failure", error));
	}

}
