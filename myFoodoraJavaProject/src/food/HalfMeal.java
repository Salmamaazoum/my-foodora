package food;


import java.util.ArrayList;

public class HalfMeal extends Meal{
	
	/*
	 * List of dishes which compose the Meal ( Later we can create classes corresponding to each Dish )
	 * Or class named MealComposition with attributes Starter, mainDish and Dessert
	 */
	
	private ArrayList<FoodItem> mealComposition;
	
	
	public HalfMeal(String mealName, boolean isVegetarian, boolean isGlutenFree, boolean mealOftheWeek) {
		super(mealName, isVegetarian, isGlutenFree,mealOftheWeek);
		this.mealComposition = new ArrayList<FoodItem>(2);

	}
	
	public HalfMeal(String mealName, boolean isVegetarian, boolean isGlutenFree) {
		super(mealName, isVegetarian, isGlutenFree);
		this.mealComposition = new ArrayList<FoodItem>(3);
	}
	
	public HalfMeal(String mealName) {
		super(mealName);
		this.mealComposition = new ArrayList<FoodItem>(3);
	}
	
	
	public void addItem(FoodItem item) throws BadMealCompositionCreationException{
		this.itemVisitor.addItemToMeal(item, this);
	}

	public ArrayList<FoodItem> getMealComposition() {
		return mealComposition;
	}

	public void setMealComposition(ArrayList<FoodItem> mealComposition) {
		this.mealComposition = mealComposition;
	}

	@Override
	public String toString() {
		String s = "The meal '" + this.name + "' is composed of : ";
		for (int i=0;i<this.mealComposition.size()-1;i++) {
			s += this.mealComposition.get(i).getName() +", ";
		}
		if (!this.mealComposition.isEmpty())
			s+=this.mealComposition.get(this.mealComposition.size()-1).getName()+".";
		return s ;
		}
	}
	
	