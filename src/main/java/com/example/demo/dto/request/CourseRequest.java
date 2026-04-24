package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CourseRequest {

	@NotBlank(message = "title is required")
	private String title;

	private String description;

	@JsonProperty("category_ids")
	private Set<Long> categoryIds;
}
