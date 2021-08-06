package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entities.Category;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategories() {
		 List<Category>categories= new ArrayList<>();
		 categoryRepository.findAll().forEach(categories::add);
		 return categories;
	}

	@Override
	public Category createCategory(Category category) {
		Category newCategory;
		newCategory=categoryRepository.save(category);
		return newCategory;
	}

	@Override
	public Category updateCategory(Long id, Category categoryDetails) {
		
	Category category=findById(id);
	category.setName(categoryDetails.getName());
	categoryRepository.save(category);
	return category;
	
	}

	@Override
	public void deleteCategory(Long categoryId) {
		categoryRepository.delete(findById(categoryId));
	}

	@Override
	public Category findById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);

		if (!category.isPresent()) {
            throw new ResourceNotFoundException("There is no Category with ID = " + id);
        }

		return category.get();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
		
	}

}
