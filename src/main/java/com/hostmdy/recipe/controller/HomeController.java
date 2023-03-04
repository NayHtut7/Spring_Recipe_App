package com.hostmdy.recipe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.service.RecipeService;

@Controller
public class HomeController {
	
	public final RecipeService recipeService;
	
	
	public HomeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}


	@GetMapping({"","/index","/"})
	public String index(Model model) {
		List<Recipe> recipes = recipeService.getRecipes();
		
		model.addAttribute("recipes",recipes);
		
		return "index";
		
	}
}
