package food;
import java.util.*;

public abstract class Meal extends MenuComponent{
	
	
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
	 * Declaring and instantiating the addItemVisitor
	 */
	
	protected AddItemVisitor itemVisitor = new ConcreteAddItemVisitor();
	
	
	public Meal (String name) {
		super();
		this.name=name;
	}
	
	public Meal (String name, boolean isVegetarian, boolean isGlutenFree, boolean mealOfTheWeek){
		super(name,isVegetarian,isGlutenFree);
		this.mealOfTheWeek=mealOfTheWeek;		
		}
	
	public Meal (String name, boolean isVegetarian, boolean isGlutenFree){
		super(name,isVegetarian,isGlutenFree);
		}
	
	
	public abstract void addItem(FoodItem item) throws BadMealCompositionCreationException;
	

	public boolean isMealOfTheWeek() {
		return mealOfTheWeek;
	}

	public void setMealOfTheWeek(boolean mealOfTheWeek) {
		this.mealOfTheWeek = mealOfTheWeek;
	}


	public ArrayList<FoodItem> getMealComposition() {
		return mealComposition;
	}

	public void setMealComposition(ArrayList<FoodItem> mealComposition) {
		this.mealComposition = mealComposition;
	}

	

	



}
	
		

