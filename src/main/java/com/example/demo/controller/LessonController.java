package com.example.demo.controller;

import com.example.demo.dto.request.LessonRequest;
import com.example.demo.dto.request.LessonUpdateRequest;
import com.example.demo.dto.response.LessonResponse;
import com.example.demo.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

	private final LessonService lessonService;

	@PostMapping
	public ResponseEntity<LessonResponse> createLesson(@Valid @RequestBody LessonRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.createLesson(request));
	}

	@GetMapping("/courses/{courseId}")
	public ResponseEntity<List<LessonResponse>> getLessonsByCourseId(@PathVariable Long courseId) {
		return ResponseEntity.ok(lessonService.getLessonsByCourseId(courseId));
	}

	@GetMapping("/{lessonId}")
	public ResponseEntity<LessonResponse> getLessonById(@PathVariable Long lessonId) {
		return ResponseEntity.ok(lessonService.getLessonById(lessonId));
	}

	@PutMapping("/{lessonId}")
	public ResponseEntity<LessonResponse> updateLesson(@PathVariable Long lessonId, @Valid @RequestBody LessonUpdateRequest request) {
		return ResponseEntity.ok(lessonService.updateLesson(lessonId, request));
	}

	@DeleteMapping("/{lessonId}")
	public ResponseEntity<Void> deleteLesson(@PathVariable Long lessonId) {
		lessonService.deleteLesson(lessonId);
		return ResponseEntity.noContent().build();
	}
}
