package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public final class UserResponse {

	private final Long id;
	private final String fullName;
	private final String email;

	public UserResponse(Long id, String fullName, String email) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
	}

	public Long id() {return id;}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (UserResponse) obj;
		return Objects.equals(this.id, that.id) && Objects.equals(this.fullName, that.fullName) && Objects.equals(this.email, that.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fullName, email);
	}

	@Override
	public String toString() {
		return "UserResponse[" + "id=" + id + ", " + "fullName=" + fullName + ", " + "email=" + email + ']';
	}
}
