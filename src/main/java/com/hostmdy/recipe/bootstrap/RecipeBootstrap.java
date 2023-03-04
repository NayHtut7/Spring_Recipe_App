package com.hostmdy.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Difficulty;
import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Notes;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.domain.UnitsOfMeasurement;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.repository.UomRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private final RecipeRepository recipeRepository;
	private final CategoryRepository categoryRepository;
	private final UomRepository uomRepository;
	
	
	
	public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UomRepository uomRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.uomRepository = uomRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		recipeRepository.saveAll(getRecipes());
	}
	
	List<Recipe> getRecipes(){
		
		List<Recipe> recipes = new ArrayList<>();
		
		Optional<Category> americanOpt = categoryRepository.findByTitle("American");
		if(americanOpt.isEmpty())throw new RuntimeException("American category is not found!");
		
		
		Optional<Category> fastFoodOpt = categoryRepository.findByTitle("FastFood");
		if(fastFoodOpt.isEmpty())throw new RuntimeException("FastFood category is not found!");
			
		
		Optional<Category> italianOpt = categoryRepository.findByTitle("Italian");
		if(italianOpt.isEmpty())throw new RuntimeException("Italian category is not found!");
		
		//Get UOMOptional
		Optional<UnitsOfMeasurement> poundOpt = uomRepository.findByUom("pound");
		if(poundOpt.isEmpty())throw new RuntimeException("Unit-pound is not found!");
		
		Optional<UnitsOfMeasurement> teaspoonOpt = uomRepository.findByUom("teaspoon");
		if(teaspoonOpt.isEmpty())throw new RuntimeException("Unit-teaspoon is not found!");
		
		Optional<UnitsOfMeasurement> tablespoonOpt = uomRepository.findByUom("tablespoon");
		if(tablespoonOpt.isEmpty())throw new RuntimeException("Unit-tablespoon is not found!");
		
		Optional<UnitsOfMeasurement> cupOpt = uomRepository.findByUom("cup");
		if(cupOpt.isEmpty())throw new RuntimeException("Unit-cup is not found!");
		
		Optional<UnitsOfMeasurement> ounceOpt = uomRepository.findByUom("ounce");
		if(ounceOpt.isEmpty())throw new RuntimeException("Unit-ounce is not found!");
		
		//Note-> Recipe connect
		Notes notes = new Notes();
		notes.setRecipeNote("Nutrition Facts (per serving)\r\n"
				
				+ "Calories 740\r\n"
				
				+ "Fat 34g\r\n"
				
				+ "Carbs 67g\r\n"
				
				+ "Protein 45g\r\n");
		
		Recipe recipe = new Recipe();
		
		recipe.setNotes(notes);
		notes.setRecipe(recipe);
		
		recipe.setTitle("True Cheeseburger Pizza");
		recipe.setSource("https://www.allrecipes.com");
		recipe.setPrepTime(15);
		recipe.setCookTime(25);
		recipe.setDescription("This is a unique twist on two favorites: pizza and cheeseburgers! It is super fast to make, and you can easily add your favorite burger toppings such as lettuce and tomato. This is a family favorite and disappears fast!");
		recipe.setDirections("1.Preheat oven to 375 degrees F (190 degrees C).\r\n"
				+ "\r\n"
				+ "2.Heat a large skillet over medium-high heat. Cook and stir beef, salt, and pepper in the hot skillet until meat is browned and crumbly, 5 to 7 minutes; drain and discard grease.\r\n"
				+ "\r\n"
				+ "3.Mix ketchup and mustard in a bowl; spread mixture over pizza crust. Spread mozzarella cheese and Cheddar cheese over ketchup mixture and top with beef and onions. Transfer pizza to a large baking sheet.\r\n"
				+ "\r\n"
				+ "4.Bake in preheated oven until cheese is melted, about 20 minutes. Top with pickles and allow pizza to cool for 5 minutes. Top with shredded lettuce and tomatoes before slicing and serving.");
		recipe.setDifficulty(Difficulty.EASY);
		
		//Get Category Object
		Category american = americanOpt.get();
		Category fastFood = fastFoodOpt.get();
		Category italian = italianOpt.get();
		
		Set<Category> catagories = new HashSet<>();
		catagories.add(american);
		catagories.add(fastFood);
		catagories.add(italian);
		
		//category - recipe manyTomany two ways
		recipe.setCategories(catagories);
		american.getRecipes().add(recipe);
		fastFood.getRecipes().add(recipe);
		italian.getRecipes().add(recipe);
		
		//Get UOM Objects
		UnitsOfMeasurement pound = poundOpt.get();
		UnitsOfMeasurement teaspoon = teaspoonOpt.get();
		UnitsOfMeasurement tablespoon = tablespoonOpt.get();
		UnitsOfMeasurement cup = cupOpt.get();
		UnitsOfMeasurement ounce = ounceOpt.get();
		
		//Create Ingredients Objects
		Ingredient beef = new Ingredient("pound ground beef chuck",new BigDecimal(3/4),recipe,pound);
		Ingredient salt = new Ingredient(" salt" , new BigDecimal(1/4), recipe, teaspoon);
		Ingredient blackPepper = new Ingredient(" ground black pepper", new BigDecimal(1/4), recipe, teaspoon);
		Ingredient ketchup = new Ingredient("  ketchup", new BigDecimal(2/3), recipe, cup);
		Ingredient yellowMustard = new Ingredient(" prepared yellow mustard", new BigDecimal(2), recipe, tablespoon);
		Ingredient pizzaCrust = new Ingredient(" pre-baked pizza crust", new BigDecimal(14), recipe, ounce);
		Ingredient mozzarellaCheese = new Ingredient(" shredded mozzarella cheese", new BigDecimal(1*1/2), recipe, cup);
		Ingredient cheddarCheese = new Ingredient(" shredded sharp Cheddar cheese", new BigDecimal(1), recipe, cup);
		Ingredient onions = new Ingredient(" chopped onions",new BigDecimal(1/2), recipe, cup);
		Ingredient dillPickle = new Ingredient("dill pickle slices", new BigDecimal(1/2), recipe, cup);
		Ingredient shreddedLettuce = new Ingredient(" shredded lettuce (Optional)", new BigDecimal(1), recipe, cup);
		Ingredient dicedTomatos = new Ingredient("  diced tomatoes (Optional)", new BigDecimal(1), recipe, cup);
		
		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(beef);
		ingredients.add(salt);
		ingredients.add(blackPepper);
		ingredients.add(ketchup);
		ingredients.add(yellowMustard);
		ingredients.add(pizzaCrust);
		ingredients.add(mozzarellaCheese);
		ingredients.add(cheddarCheese);
		ingredients.add(onions);
		ingredients.add(dillPickle);
		ingredients.add(shreddedLettuce);
		ingredients.add(dicedTomatos);
		
		recipe.setIngredients(ingredients);
		
		recipes.add(recipe);
		
		return recipes;
	}
	
	
	
}
//