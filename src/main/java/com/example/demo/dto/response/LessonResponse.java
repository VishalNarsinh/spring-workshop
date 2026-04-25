package com.example.demo.dto.response;

import java.util.Objects;

public final class LessonResponse {

	private final Long id;
	private final Long courseId;
	private final String title;
	private final String content;

	public LessonResponse(Long id, Long courseId, String title, String content) {
		this.id = id;
		this.courseId = courseId;
		this.title = title;
		this.content = content;
	}

	public Long id() {return id;}

	public Long courseId() {return courseId;}

	public String title() {return title;}

	public String content() {return content;}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (LessonResponse) obj;
		return Objects.equals(this.id, that.id) && Objects.equals(this.courseId, that.courseId) && Objects.equals(this.title, that.title) &&
				Objects.equals(this.content, that.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, courseId, title, content);
	}

	@Override
	public String toString() {
		return "LessonResponse[" + "id=" + id + ", " + "courseId=" + courseId + ", " + "title=" + title + ", " + "content=" + content + ']';
	}
}
