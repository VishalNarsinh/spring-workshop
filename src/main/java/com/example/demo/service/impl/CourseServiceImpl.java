package com.example.demo.service.impl;

import com.example.demo.dto.request.CourseRequest;
import com.example.demo.dto.response.CourseCategoryResponse;
import com.example.demo.dto.response.CourseResponse;
import com.example.demo.dto.response.PagedResponseDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.exception.EntityExistsException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public CourseResponse createCourse(CourseRequest request) {
		String title = request.getTitle().trim().toLowerCase();
		if (courseRepository.existsCourseByTitle(title)) {
			throw new EntityExistsException("Course already exists with title: " + title);
		}
		Course course = new Course();
		course.setTitle(title);
		course.setDescription(request.getDescription());
		List<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());
		categories.forEach(course::addCategory);
		Course saved = courseRepository.save(course);
		return new CourseResponse(saved.getId(), saved.getTitle(), saved.getDescription());
	}

	@Override
	public PagedResponseDto<CourseResponse> getCourses(Pageable pageable) {
		Page<CourseResponse> coursePage = courseRepository.findAll(pageable)
				.map(course -> new CourseResponse(course.getId(), course.getTitle(), course.getDescription()));
		return toPagedResponse(coursePage);
	}

	@Override
	public PagedResponseDto<CourseResponse> searchCourses(int page, int size, String sortBy, String direction) {
		Sort sort = Sort.by(sortBy);
		if (direction.equalsIgnoreCase("desc")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<CourseResponse> coursePage = courseRepository.findAll(pageable)
				.map(course -> new CourseResponse(course.getId(), course.getTitle(), course.getDescription()));
		return toPagedResponse(coursePage);
	}

	@Override
	public CourseResponse getCourseById(Long courseId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found for id: " + courseId));
		return new CourseResponse(course.getId(), course.getTitle(), course.getDescription());
	}

	@Override
	public CourseCategoryResponse linkCategoryToCourse(Long courseId, Long categoryId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found for id: " + courseId));
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new EntityNotFoundException("Category not found for id: " + categoryId));
		if (course.getCategories().contains(category)) {
			throw new EntityExistsException("Course already linked to category with id: " + categoryId);
		}
		course.addCategory(category);
		courseRepository.save(course);
		return new CourseCategoryResponse(course.getId(), category.getId(), "Category linked to course");
	}

	@Override
	public void unlinkCategoryFromCourse(Long courseId, Long categoryId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found for id: " + courseId));

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new EntityNotFoundException("Category not found for id: " + categoryId));

		course.getCategories().remove(category);
		courseRepository.save(course);
	}

	private PagedResponseDto<CourseResponse> toPagedResponse(Page<CourseResponse> page) {
		return new PagedResponseDto<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(), page.isLast());
	}
}
