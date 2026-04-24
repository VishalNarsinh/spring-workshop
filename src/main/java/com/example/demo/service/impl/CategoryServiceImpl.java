package com.example.demo.service.impl;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.entity.Category;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public CategoryResponse createCategory(CategoryRequest request) {
		String name = request.getName().toLowerCase();
		if (categoryRepository.existsCategoryByNameIgnoreCase(name)) {
			throw new EntityNotFoundException("Category with name " + name + " already exists");
		}
		Category category = new Category();
		category.setName(name);
		Category saved = categoryRepository.save(category);
		return new CategoryResponse(saved.getId(), saved.getName());
	}

	@Override
	public List<CategoryResponse> getCategories() {
		return categoryRepository.findAll().stream().map(category -> new CategoryResponse(category.getId(), category.getName())).toList();
	}

	@Override
	public CategoryResponse getCategoryById(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new EntityNotFoundException("Category not found for id: " + categoryId));
		return new CategoryResponse(category.getId(), category.getName());
	}
}
