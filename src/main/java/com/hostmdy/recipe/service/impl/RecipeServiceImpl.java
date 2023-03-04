package com.hostmdy.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}



	@Override
	public List<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		return (List<Recipe>) recipeRepository.findAll();
	}
	

}
