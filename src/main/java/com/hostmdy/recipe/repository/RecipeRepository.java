package com.hostmdy.recipe.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}

