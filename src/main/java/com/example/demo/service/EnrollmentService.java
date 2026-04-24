package com.example.demo.service;

import com.example.demo.dto.request.EnrollmentRequest;
import com.example.demo.dto.response.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

	EnrollmentResponse enrollUser(EnrollmentRequest request);

	List<EnrollmentResponse> getEnrollmentsByUserId(Long userId);

	EnrollmentResponse getEnrollmentById(Long enrollmentId);
}
