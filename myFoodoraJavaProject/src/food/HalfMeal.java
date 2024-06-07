package food;


import java.util.ArrayList;

public class HalfMeal extends Meal{
	
	
	public HalfMeal(String mealName, boolean isVegetarian, boolean isGlutenFree, boolean mealOftheWeek) {
		super(mealName, isVegetarian, isGlutenFree,mealOftheWeek);
		this.mealComposition = new ArrayList<FoodItem>();

	}
	
	public HalfMeal(String mealName, boolean isVegetarian, boolean isGlutenFree) {
		super(mealName, isVegetarian, isGlutenFree);
		this.mealComposition = new ArrayList<FoodItem>();
	}
	
	public HalfMeal(String mealName) {
		super(mealName);
		this.mealComposition = new ArrayList<FoodItem>();
	}
	
	
	public void addItem(FoodItem item) throws BadMealCompositionCreationException{
		this.itemVisitor.addItemToMeal(item, this);
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
	
	