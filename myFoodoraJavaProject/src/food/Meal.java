package food;
import java.util.*;

public class Meal {
	private String name;
	
	private boolean isHalfMeal ;  // half-meal or full-meal
	
	private boolean isVegetarian; //Vegetarian or Standard
	
	private boolean isGlutenFree; //Gluten-free or Not
	
	private boolean mealOfTheWeek; 
	
	private ArrayList<FoodItem> mealComposition;
	
	private double price;
	
	public Meal (String name, boolean isHalfMeal, boolean isVegetarian, boolean isGlutenFree, boolean mealOfTheWeek){
		
		this.name=name;
		this.isGlutenFree=isGlutenFree;
		this.isHalfMeal=isHalfMeal;
		this.isVegetarian=isVegetarian;
		this.mealOfTheWeek=mealOfTheWeek;
		this.mealComposition=new ArrayList<FoodItem>();
			
		}
	
	public void addItem(FoodItem item) throws BadMealCompositionCreationException {
		if (this.isHalfMeal) {
			if (this.mealComposition.size()==2) {
				throw new BadMealCompositionCreationException("attempt to add third item to a half-meal");
			}
			
			if (this.mealComposition.size()==1) {
				if (item.getCategory().equals(mealComposition.get(0).getCategory())) {
					throw new BadMealCompositionCreationException("attempt to create half-meal with duplicate categories");
				}
				if ( (!mealComposition.get(0).getCategory().equals(FoodItem.foodCategory.MAINDISH)) && (!item.getCategory().equals(FoodItem.foodCategory.MAINDISH))  ) {
					throw new BadMealCompositionCreationException("attempt to create a half-meal with no main-dish");
				}
		
			}
		
		}
		
		if ( (!this.isHalfMeal)) {
			if (this.mealComposition.size()==3) {
				throw new BadMealCompositionCreationException("attempt to add fourth item to a full-meal");
			}
			for (FoodItem existingItem : mealComposition) {
	            if (item.getCategory().equals(existingItem.getCategory())) {
	                throw new BadMealCompositionCreationException("attempt to create full-meal with duplicate categories");
	            }
			}
		}
		
		if ( (this.isVegetarian) && (!item.getIsVegetarian())) {
			throw new BadMealCompositionCreationException("attempt to add non-vegetarian item to a vegetarian meal");
		}
		
		if ( (this.isGlutenFree) && (!item.getIsGlutenFree())) {
			throw new BadMealCompositionCreationException("attempt to add gluten-containing item to a gluten-free meal");
		}

		
		this.mealComposition.add(item);
		
		
	}
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHalfMeal() {
		return isHalfMeal;
	}

	public void setHalfMeal(boolean isHalfMeal) {
		this.isHalfMeal = isHalfMeal;
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

	public ArrayList<FoodItem> getMealComposition() {
		return mealComposition;
	}

	public void setMealComposition(ArrayList<FoodItem> mealComposition) {
		this.mealComposition = mealComposition;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		String s = "The meal '" + name + "' is composed of : ";
		for (int i=0;i<mealComposition.size()-1;i++) {
			s += mealComposition.get(i).getName() +", ";
		}
		if (!mealComposition.isEmpty())
			s+=mealComposition.get(mealComposition.size()-1).getName()+".";
		return s ;
	}
	
	


}
	
		

