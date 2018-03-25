package edu.towson.cis.cosc442.project4.coffeemaker;

public interface CoffeeMakerInterface {

	/**
	 * Returns true if a recipe is successfully added to the 
	 * coffee maker
	 * @param r
	
	 * @return boolean */
	boolean addRecipe(Recipe r);

	/**
	 * Returns true if the recipe was deleted from the 
	 * coffee maker
	 * @param r
	
	 * @return boolean */
	boolean deleteRecipe(Recipe r);

	/**
	 * Returns true if the recipe is successfully edited
	 * @param oldRecipe
	 * @param newRecipe
	
	 * @return boolean */
	boolean editRecipe(Recipe oldRecipe, Recipe newRecipe);

	/**
	 * Returns true if inventory was successfully added
	 * @param amtCoffee
	 * @param amtMilk
	 * @param amtSugar
	 * @param amtChocolate
	
	 * @return boolean */
	boolean addInventory(int amtCoffee, int amtMilk, int amtSugar, int amtChocolate);

	/**
	 * Returns the inventory of the coffee maker
	
	 * @return Inventory */
	Inventory checkInventory();

	/**
	 * Returns the change of a user's beverage purchase, or
	 * the user's money if the beverage cannot be made
	 * @param r
	 * @param amtPaid
	
	 * @return int */
	int makeCoffee(Recipe r, int amtPaid);

	/**
	 * Returns an array of all the recipes
	
	 * @return Recipe[] */
	Recipe[] getRecipes();

	/**
	 * Returns the Recipe associated with the given name
	 * @param name
	
	 * @return Recipe */
	Recipe getRecipeForName(String name);

}