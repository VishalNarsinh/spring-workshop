package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorResponseDto {

	@JsonProperty(index = 0)
	private final String message;

	@JsonProperty(index = 1)
	private final Map<String, String> fieldErrors;

	public ErrorResponseDto(String message) {
		this(message, null);
	}
}
