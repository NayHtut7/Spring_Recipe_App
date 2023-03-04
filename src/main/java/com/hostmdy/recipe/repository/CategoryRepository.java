package com.hostmdy.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	Optional<Category>  findByTitle(String title);
	
}
