package com.example.demo.exception.handler;

import com.example.demo.dto.response.ErrorResponseDto;
import com.example.demo.exception.EntityExistsException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.exception.PaymentProviderNotSupportedException;
import com.example.demo.exception.PaymentValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(ex.getMessage()));
	}

	@ExceptionHandler(PaymentProviderNotSupportedException.class)
	public ResponseEntity<ErrorResponseDto> handleUnsupportedProvider(PaymentProviderNotSupportedException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(ex.getMessage()));
	}

	@ExceptionHandler(PaymentValidationException.class)
	public ResponseEntity<ErrorResponseDto> handlePaymentValidation(PaymentValidationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(ex.getMessage()));
	}

	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleEntityExists(EntityExistsException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDto> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new LinkedHashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto("Validation failed", errors));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponseDto> handleValidation(MethodArgumentTypeMismatchException ex) {
		Map<String, String> errors = new LinkedHashMap<>();
		errors.put(ex.getName(), "Invalid value: " + ex.getValue());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto("Validation failed", errors));
	}

}
