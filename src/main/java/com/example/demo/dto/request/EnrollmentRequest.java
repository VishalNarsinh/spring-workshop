package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentRequest {

	@NotNull(message = "userId is required")
	@JsonProperty("user_id")
	private Long userId;

	@NotNull(message = "courseId is required")
	@JsonProperty("course_id")
	private Long courseId;
}
