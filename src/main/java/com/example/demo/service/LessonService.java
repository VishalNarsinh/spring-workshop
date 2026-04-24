package com.example.demo.service;

import com.example.demo.dto.request.LessonRequest;
import com.example.demo.dto.request.LessonUpdateRequest;
import com.example.demo.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {

	LessonResponse createLesson(LessonRequest request);

	List<LessonResponse> getLessonsByCourseId(Long courseId);

	LessonResponse getLessonById(Long lessonId);

	LessonResponse updateLesson(Long lessonId, LessonUpdateRequest request);

	void deleteLesson(Long lessonId);
}
