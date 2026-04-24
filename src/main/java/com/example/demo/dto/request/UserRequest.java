package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@NotBlank(message = "fullName is required")
	@JsonProperty("full_name")
	private String fullName;

	@NotBlank(message = "email is required")
	@Email(message = "email must be valid")
	private String email;
}
