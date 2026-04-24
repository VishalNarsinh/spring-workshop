package com.example.demo.controller;

import com.example.demo.dto.request.EnrollmentRequest;
import com.example.demo.dto.response.EnrollmentResponse;
import com.example.demo.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

	private final EnrollmentService enrollmentService;

	public EnrollmentController(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

	@PostMapping
	public ResponseEntity<EnrollmentResponse> enrollUser(@Valid @RequestBody EnrollmentRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enrollUser(request));
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByUser(@PathVariable Long userId) {
		return ResponseEntity.ok(enrollmentService.getEnrollmentsByUserId(userId));
	}

	@GetMapping("/{enrollmentId}")
	public ResponseEntity<EnrollmentResponse> getEnrollmentById(@PathVariable Long enrollmentId) {
		return ResponseEntity.ok(enrollmentService.getEnrollmentById(enrollmentId));
	}
}
