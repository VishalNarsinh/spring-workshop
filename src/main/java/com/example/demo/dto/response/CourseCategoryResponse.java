package com.example.demo.dto.response;

import java.util.Objects;

public final class CourseCategoryResponse {

	private final Long courseId;
	private final Long categoryId;
	private final String message;

	public CourseCategoryResponse(Long courseId, Long categoryId, String message) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.message = message;
	}

	public Long courseId() {return courseId;}

	public Long categoryId() {return categoryId;}

	public String message() {return message;}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (CourseCategoryResponse) obj;
		return Objects.equals(this.courseId, that.courseId) && Objects.equals(this.categoryId, that.categoryId) && Objects.equals(this.message, that.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId, categoryId, message);
	}

	@Override
	public String toString() {
		return "CourseCategoryResponse[" + "courseId=" + courseId + ", " + "categoryId=" + categoryId + ", " + "message=" + message + ']';
	}

}
