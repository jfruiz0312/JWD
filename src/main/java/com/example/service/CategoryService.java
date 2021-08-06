package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.entities.Category;

public interface CategoryService {

	List<Category> getAllCategories();

	Category createCategory(Category category);

	Category updateCategory(Long id, Category category);

	void deleteCategory(Long categoryId);

	Category findById(Long id);
	
	Page<Category> findAll(Pageable pageable);


}
