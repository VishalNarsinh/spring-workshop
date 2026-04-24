package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonRequest {

	@NotBlank(message = "title is required")
	private String title;

	@NotBlank(message = "content is required")
	private String content;

	@NotNull(message = "courseId is required")
	@JsonProperty("course_id")
	private Long courseId;
}
