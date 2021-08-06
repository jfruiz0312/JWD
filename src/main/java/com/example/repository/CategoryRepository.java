package com.example.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>{

	Page<Category> findAll(Pageable pageable);

}
