package com.example.demo.dto.response;

import java.time.LocalDateTime;

public record EnrollmentResponse(Long enrollmentId, Long userId, Long courseId, LocalDateTime enrolledAt) {}