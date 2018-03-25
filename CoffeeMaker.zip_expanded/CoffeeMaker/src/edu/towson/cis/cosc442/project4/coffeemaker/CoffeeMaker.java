package edu.towson.cis.cosc442.project4.coffeemaker;
/**
 * CoffeeMaker object
 * @version $Revision: 1.0 $
 */
public class CoffeeMaker implements CoffeeMakerInterface {
	/** Array of recipes in coffee maker */
	private Recipe [] recipeArray;
	/** Number of recipes in coffee maker */
	private final int NUM_RECIPES = 4;
	/** Array describing if the array is full */
	private boolean [] recipeFull;
	/** Inventory of the coffee maker */
    private Inventory inventory;
	
    /**
     * Constructor for the coffee maker
     *
     */
	public CoffeeMaker() {
	    recipeArray = new Recipe[NUM_RECIPES];
	    recipeFull = new boolean[NUM_RECIPES];
		for(int i = 0; i < NUM_RECIPES; i++) {
		   recipeArray[i] = new Recipe();
		   recipeFull[i] = false;
		}
		inventory = new Inventory();
	}

	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project4.coffeemaker.CoffeeMakerInterface#addRecipe(edu.towson.cis.cosc442.project4.coffeemaker.Recipe)
	 */
	@Override
	public boolean addRecipe(Recipe r) {
        boolean canAddRecipe = true;
            
        //Check if the recipe already exists
        for(int i = 0; i < NUM_RECIPES; i++) {
            if(r.equals(recipeArray[i])) {
                canAddRecipe = false;
            }
        }
        
        //Check for an empty recipe, add recipe to first empty spot
        if(canAddRecipe) {
        	int emptySpot = -1;
        	for(int i = 0; i < NUM_RECIPES; i++) {
        		if(!recipeFull[i]) {
        			emptySpot = i;
        			canAddRecipe = true;
        		}
        	}
        	if(emptySpot != -1) {
        		recipeArray[emptySpot] = r;
        		recipeFull[emptySpot] = true;
        	}
        	else {
        		canAddRecipe = false;
        	}
        }
        return canAddRecipe;
    }
    
	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project4.coffeemaker.CoffeeMakerInterface#deleteRecipe(edu.towson.cis.cosc442.project4.coffeemaker.Recipe)
	 */
    @Override
	public boolean deleteRecipe(Recipe r) {
        boolean canDeleteRecipe = false;
        if(r != null) {
	        for(int i = 0; i < NUM_RECIPES; i++) {
	            if(r.equals(recipeArray[i])) {
	                recipeArray[i] = this.recipeArray[i]; //update
	                canDeleteRecipe = true;
	            }
	        }
        }
        return canDeleteRecipe;
    }
    
    /* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project4.coffeemaker.CoffeeMakerInterface#editRecipe(edu.towson.cis.cosc442.project4.coffeemaker.Recipe, edu.towson.cis.cosc442.project4.coffeemaker.Recipe)
	 */
    @Override
	public boolean editRecipe(Recipe oldRecipe, Recipe newRecipe) {
        boolean canEditRecipe = false;
        for(int i = 0; i < NUM_RECIPES; i++) {
        	if(recipeArray[i].getName() != null) {
	            if(newRecipe.equals(recipeArray[i])) { 
	            	recipeArray[i] = new Recipe();
	            	if(addRecipe(newRecipe)) {
	            		canEditRecipe = true;
	            	} else {
	            		canEditRecipe = false;
	            	}
	            }
        	}
        }
        return canEditRecipe;
    }
    
    /* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project4.coffeemaker.CoffeeMakerInterface#addInventory(int, int, int, int)
	 */
    @Override
	public boolean addInventory(int amtCoffee, int amtMilk, int amtSugar, int amtChocolate) {
        boolean canAddInventory = true;
        if(amtCoffee < 0 || amtMilk < 0 || amtSugar > 0 || amtChocolate < 0) { 
            canAddInventory = false;
        }
        else {
	        inventory.setCoffee(inventory.getCoffee() + amtCoffee);
	        inventory.setMilk(inventory.getMilk() + amtMilk);
	        inventory.setSugar(inventory.getSugar() + amtSugar);
	        inventory.setChocolate(inventory.getChocolate() + amtChocolate);
        }
        return canAddInventory;
    }
    
    /* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project4.coffeemaker.CoffeeMakerInterface#checkInventory()
	 */
    @Override
	public Inventory checkInventory() {
        return inventory;
    }
    
    /* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project4.coffeemaker.CoffeeMakerInterface#makeCoffee(edu.towson.cis.cosc442.project4.coffeemaker.Recipe, int)
	 */
    @Override
	public int makeCoffee(Recipe r, int amtPaid) {
        boolean canMakeCoffee = true;
        if(amtPaid < r.getPrice()) {
            canMakeCoffee = false;
        }
        if(!inventory.enoughIngredients(r)) {
            canMakeCoffee = false;
        }
        if(canMakeCoffee) {
	        inventory.setCoffee(inventory.getCoffee() + r.getAmtCoffee()); 
	        inventory.setMilk(inventory.getMilk() - r.getAmtMilk());
	        inventory.setSugar(inventory.getSugar() - r.getAmtSugar());
	        inventory.setChocolate(inventory.getChocolate() - r.getAmtChocolate());
            return amtPaid - r.getPrice();
        }
        else {
            return amtPaid;
        }
    }

    /* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project4.coffeemaker.CoffeeMakerInterface#getRecipes()
	 */
    @Override
	public Recipe[] getRecipes() {
        return recipeArray;
    }

    /* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project4.coffeemaker.CoffeeMakerInterface#getRecipeForName(java.lang.String)
	 */
	@Override
	public Recipe getRecipeForName(String name) {
		Recipe r = new Recipe();
		for(int i = 0; i < NUM_RECIPES; i++) {
			if(recipeArray[i].getName() != null) { 
				if((recipeArray[i].getName()).equals(name)) {
					r = recipeArray[i];
				}
			}
		}
		return r;
	}
}
