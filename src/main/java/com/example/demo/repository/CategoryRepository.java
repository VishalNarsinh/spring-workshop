package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsCategoryByNameIgnoreCase(String name);

	List<Category> findByIdIn(Collection<Long> ids);
}
