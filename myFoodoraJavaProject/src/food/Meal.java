package food;
import java.util.*;

public abstract class Meal {
	
	/*
	 * Name of the Meal
	 */
	
	protected String name;
	
	/*
	 * Whether it is Vegetarian or Standard
	 */

	protected boolean isVegetarian; 
	
	/*
	 * Whether it is Gluten-Free or not
	 */
	protected boolean isGlutenFree; 
	
	/*
	 * Whether it is meal of the week or Not
	 */
	
	protected boolean mealOfTheWeek;
	
	/*
	 * List of dishes which compose the Meal ( Later we can create classes corresponding to each Dish )
	 * Or class named MealComposition with attributes Starter, mainDish and Dessert
	 */
	
	protected ArrayList<FoodItem> mealComposition;
	
	/*
	 * The price of the Meal
	 */
	protected double price;
	
	/*
	 * Declaring and instantiating the addItemVisitor
	 */
	
	protected AddItemVisitor itemVisitor = new ConcreteAddItemVisitor();
	
	
	public Meal (String name) {
		this.name=name;
	}
	
	public Meal (String name, boolean isVegetarian, boolean isGlutenFree, boolean mealOfTheWeek){
		
		this.name=name;
		
		this.isGlutenFree = isGlutenFree;
		
		this.isVegetarian = isVegetarian;
		
		this.mealOfTheWeek=mealOfTheWeek;		
		}
	
	public Meal (String name, boolean isVegetarian, boolean isGlutenFree){
		
		this.name=name;
		
		this.isGlutenFree = isGlutenFree;
		
		this.isVegetarian = isVegetarian;
		
		}
	
	
	public abstract void addItem(FoodItem item) throws BadMealCompositionCreationException;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public boolean isGlutenFree() {
		return isGlutenFree;
	}

	public void setGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}

	public boolean isMealOfTheWeek() {
		return mealOfTheWeek;
	}

	public void setMealOfTheWeek(boolean mealOfTheWeek) {
		this.mealOfTheWeek = mealOfTheWeek;
	}



	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<FoodItem> getMealComposition() {
		return mealComposition;
	}

	public void setMealComposition(ArrayList<FoodItem> mealComposition) {
		this.mealComposition = mealComposition;
	}

	

	



}
	
		

