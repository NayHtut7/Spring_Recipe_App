package com.hostmdy.recipe.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String description;
	
	private BigDecimal amount;
	
	@ManyToOne
	private Recipe recipe;
	
	@OneToOne
	private UnitsOfMeasurement uom;
	
	

	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Ingredient(String description, BigDecimal amount, Recipe recipe, UnitsOfMeasurement uom) {
		super();
		this.description = description;
		this.amount = amount;
		this.recipe = recipe;
		this.uom = uom;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public UnitsOfMeasurement getUom() {
		return uom;
	}

	public void setUom(UnitsOfMeasurement uom) {
		this.uom = uom;
	}
	
	
}
