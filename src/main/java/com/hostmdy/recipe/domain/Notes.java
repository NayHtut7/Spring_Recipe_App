package com.hostmdy.recipe.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String recipeNote;
	
	@OneToOne(mappedBy = "notes")
	private Recipe recipe;


	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public Recipe getRecipe() {
		return recipe;
	}


	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRecipeNote() {
		return recipeNote;
	}


	public void setRecipeNote(String recipeNote) {
		this.recipeNote = recipeNote;
	}
	
	
	
	
}
