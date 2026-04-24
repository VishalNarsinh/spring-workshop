package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonUpdateRequest {

	@NotBlank(message = "title is required")
	private String title;

	@NotBlank(message = "content is required")
	private String content;
}
