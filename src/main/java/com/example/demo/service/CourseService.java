package com.example.demo.service;

import com.example.demo.dto.response.CourseCategoryResponse;
import com.example.demo.dto.response.CourseResponse;
import com.example.demo.dto.response.PagedResponseDto;
import com.example.demo.dto.request.CourseRequest;
import org.springframework.data.domain.Pageable;

public interface CourseService {

	CourseResponse createCourse(CourseRequest request);

	PagedResponseDto<CourseResponse> getCourses(Pageable pageable);

	PagedResponseDto<CourseResponse> searchCourses(int page, int size, String sortBy, String direction);

	CourseResponse getCourseById(Long courseId);

	CourseCategoryResponse linkCategoryToCourse(Long courseId, Long categoryId);

	void unlinkCategoryFromCourse(Long courseId, Long categoryId);
}
