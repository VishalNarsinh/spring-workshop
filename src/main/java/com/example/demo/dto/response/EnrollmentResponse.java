package com.example.demo.dto.response;

import java.time.LocalDateTime;
import java.util.Objects;

public final class EnrollmentResponse {

	private final Long enrollmentId;
	private final Long userId;
	private final Long courseId;
	private final LocalDateTime enrolledAt;

	public EnrollmentResponse(Long enrollmentId, Long userId, Long courseId, LocalDateTime enrolledAt) {
		this.enrollmentId = enrollmentId;
		this.userId = userId;
		this.courseId = courseId;
		this.enrolledAt = enrolledAt;
	}

	public Long enrollmentId() {return enrollmentId;}

	public Long userId() {return userId;}

	public Long courseId() {return courseId;}

	public LocalDateTime enrolledAt() {return enrolledAt;}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (EnrollmentResponse) obj;
		return Objects.equals(this.enrollmentId, that.enrollmentId) && Objects.equals(this.userId, that.userId) && Objects.equals(this.courseId, that.courseId) &&
				Objects.equals(this.enrolledAt, that.enrolledAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(enrollmentId, userId, courseId, enrolledAt);
	}

	@Override
	public String toString() {
		return "EnrollmentResponse[" + "enrollmentId=" + enrollmentId + ", " + "userId=" + userId + ", " + "courseId=" + courseId + ", " +
				"enrolledAt=" + enrolledAt + ']';
	}
}