package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public final class CategoryResponse {

	private final Long id;
	private final String name;

	public CategoryResponse(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long id() {return id;}

	public String name() {return name;}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (CategoryResponse) obj;
		return Objects.equals(this.id, that.id) && Objects.equals(this.name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "CategoryResponse[" + "id=" + id + ", " + "name=" + name + ']';
	}
}
