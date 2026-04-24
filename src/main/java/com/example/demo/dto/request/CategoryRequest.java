package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequest {

	@NotBlank(message = "name is required")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
