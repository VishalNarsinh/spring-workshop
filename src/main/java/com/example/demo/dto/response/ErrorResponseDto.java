package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ErrorResponseDto {

	@JsonProperty(index = 0)
	private final String message;

	@JsonProperty(index = 1)
	private final Map<String, String> fieldErrors;

	public ErrorResponseDto(String message) {
		this(message, null);
	}

	public ErrorResponseDto(String message, Map<String, String> fieldErrors) {
		this.message = message;
		this.fieldErrors = fieldErrors;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}
}