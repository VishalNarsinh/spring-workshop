package com.example.demo.controller;

import com.example.demo.dto.request.CourseRequest;
import com.example.demo.dto.response.CourseCategoryResponse;
import com.example.demo.dto.response.CourseResponse;
import com.example.demo.dto.response.PagedResponseDto;
import com.example.demo.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@PostMapping
	public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(request));
	}

	@GetMapping
	public ResponseEntity<PagedResponseDto<CourseResponse>> getCourses(@PageableDefault(size = 10) Pageable pageable) {
		return ResponseEntity.ok(courseService.getCourses(pageable));
	}

	@GetMapping("/search")
	public ResponseEntity<PagedResponseDto<CourseResponse>> searchCourses(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "title") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		return ResponseEntity.ok(courseService.searchCourses(page, size, sortBy, direction));
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long courseId) {
		return ResponseEntity.ok(courseService.getCourseById(courseId));
	}

	@PutMapping("/{courseId}/categories/{categoryId}")
	public ResponseEntity<CourseCategoryResponse> linkCategoryToCourse(@PathVariable Long courseId, @PathVariable Long categoryId) {
		return ResponseEntity.ok(courseService.linkCategoryToCourse(courseId, categoryId));
	}

	@DeleteMapping("/{courseId}/categories/{categoryId}")
	public ResponseEntity<Void> unlinkCategoryFromCourse(@PathVariable Long courseId, @PathVariable Long categoryId) {
		courseService.unlinkCategoryFromCourse(courseId, categoryId);
		return ResponseEntity.noContent().build();
	}
}
