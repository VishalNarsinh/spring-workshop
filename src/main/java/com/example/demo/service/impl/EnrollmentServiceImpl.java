package com.example.demo.service.impl;

import com.example.demo.dto.request.EnrollmentRequest;
import com.example.demo.dto.response.EnrollmentResponse;
import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.User;
import com.example.demo.exception.EntityExistsException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	private final EnrollmentRepository enrollmentRepository;

	@Override
	public EnrollmentResponse enrollUser(EnrollmentRequest request) {
		Long userId = request.getUserId();
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found for id: " + userId));
		Course course = courseRepository.findById(request.getCourseId())
				.orElseThrow(() -> new EntityNotFoundException("Course not found for id: " + request.getCourseId()));

		user.getEnrollments().stream().filter(enrollment -> enrollment.getCourse().getId().equals(course.getId())).findFirst().ifPresent(enrollment -> {
			throw new EntityExistsException("User with id: " + userId + " is already enrolled in course with id: " + course.getId());
		});
		Enrollment enrollment = new Enrollment();
		enrollment.setEnrolledAt(LocalDateTime.now());
		enrollment.setUser(user);
		enrollment.setCourse(course);

		Enrollment saved = enrollmentRepository.save(enrollment);
		return new EnrollmentResponse(saved.getId(), user.getId(), course.getId(), saved.getEnrolledAt());
	}

	@Override
	public List<EnrollmentResponse> getEnrollmentsByUserId(Long userId) {
		if (!userRepository.existsById(userId)) {
			throw new EntityNotFoundException("User not found for id: " + userId);
		}
		return enrollmentRepository.findAllByUserId(userId)
				.stream()
				.map(enrollment -> new EnrollmentResponse(enrollment.getId(), enrollment.getUser().getId(), enrollment.getCourse().getId(),
						enrollment.getEnrolledAt()))
				.toList();
	}

	@Override
	public EnrollmentResponse getEnrollmentById(Long enrollmentId) {
		Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
				.orElseThrow(() -> new EntityNotFoundException("Enrollment not found for id: " + enrollmentId));
		return new EnrollmentResponse(enrollment.getId(), enrollment.getUser().getId(), enrollment.getCourse().getId(), enrollment.getEnrolledAt());
	}
}
