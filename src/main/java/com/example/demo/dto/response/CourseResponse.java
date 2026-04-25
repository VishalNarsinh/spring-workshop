package com.example.demo.dto.response;

import java.util.Objects;

public final class CourseResponse {

	private final Long id;
	private final String title;
	private final String description;

	public CourseResponse(Long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public Long id() {return id;}

	public String title() {return title;}

	public String description() {return description;}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (CourseResponse) obj;
		return Objects.equals(this.id, that.id) && Objects.equals(this.title, that.title) && Objects.equals(this.description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, description);
	}

	@Override
	public String toString() {
		return "CourseResponse[" + "id=" + id + ", " + "title=" + title + ", " + "description=" + description + ']';
	}
}
