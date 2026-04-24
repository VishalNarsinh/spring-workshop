package com.example.demo.service.impl;

import com.example.demo.dto.request.LessonRequest;
import com.example.demo.dto.request.LessonUpdateRequest;
import com.example.demo.dto.response.LessonResponse;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

	private final CourseRepository courseRepository;
	private final LessonRepository lessonRepository;

	public LessonServiceImpl(CourseRepository courseRepository, LessonRepository lessonRepository) {
		this.courseRepository = courseRepository;
		this.lessonRepository = lessonRepository;
	}

	@Override
	public LessonResponse createLesson(LessonRequest request) {
		Long courseId = request.getCourseId();
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found for id: " + courseId));

		Lesson lesson = new Lesson();
		lesson.setTitle(request.getTitle());
		lesson.setContent(request.getContent());
		lesson.setCourse(course);
		Lesson savedLesson = lessonRepository.save(lesson);
		return new LessonResponse(savedLesson.getId(), course.getId(), savedLesson.getTitle(), savedLesson.getContent());
	}

	@Override
	public List<LessonResponse> getLessonsByCourseId(Long courseId) {
		if (!courseRepository.existsById(courseId)) {
			throw new EntityNotFoundException("Course not found for id: " + courseId);
		}
		return lessonRepository.findAllByCourseId(courseId)
				.stream()
				.map(lesson -> new LessonResponse(lesson.getId(), courseId, lesson.getTitle(), lesson.getContent()))
				.toList();
	}

	@Override
	public LessonResponse getLessonById(Long lessonId) {
		Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new EntityNotFoundException("Lesson not found for id: " + lessonId));
		return new LessonResponse(lesson.getId(), lesson.getCourse().getId(), lesson.getTitle(), lesson.getContent());
	}

	@Override
	public LessonResponse updateLesson(Long lessonId, LessonUpdateRequest request) {
		Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new EntityNotFoundException("Lesson not found for id: " + lessonId));
		lesson.setTitle(request.getTitle());
		lesson.setContent(request.getContent());
		Lesson saved = lessonRepository.save(lesson);
		return new LessonResponse(saved.getId(), saved.getCourse().getId(), saved.getTitle(), saved.getContent());
	}

	@Override
	public void deleteLesson(Long lessonId) {
		Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new EntityNotFoundException("Lesson not found for id: " + lessonId));
		lessonRepository.delete(lesson);
	}
}
