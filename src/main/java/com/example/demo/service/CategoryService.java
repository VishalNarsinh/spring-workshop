package com.example.demo.service;

import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

	CategoryResponse createCategory(CategoryRequest request);

	List<CategoryResponse> getCategories();

	CategoryResponse getCategoryById(Long categoryId);
}
