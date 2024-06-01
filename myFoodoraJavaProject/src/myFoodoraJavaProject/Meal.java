package myFoodoraJavaProject;
import java.util.*;

public class Meal {
	private String name;
	
	private boolean isHalfMeal ;  // half-meal or full-meal
	
	private boolean isVegetarian; //Vegetarian or Standard
	
	private boolean isGlutenFree; //Gluten-free or Not
	
	private boolean mealOfTheWeek; 
	
	private ArrayList<FoodItem> mealComposition;
	
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
				if (item.getCategory().equalsIgnoreCase(mealComposition.get(0).getCategory())) {
					throw new BadMealCompositionCreationException("attempt to create half-meal with duplicate categories");
				}
				if ( (!mealComposition.get(0).getCategory().equalsIgnoreCase("MAINDISH")) && (!item.getCategory().equalsIgnoreCase("MAINDISH"))  ) {
					throw new BadMealCompositionCreationException("attempt to create a half-meal with no main-dish");
				}
		
			}
		
		}
		
		if ( (!this.isHalfMeal)) {
			if (this.mealComposition.size()==3) {
				throw new BadMealCompositionCreationException("attempt to add fourth item to a full-meal");
			}
			for (FoodItem existingItem : mealComposition) {
	            if (item.getCategory().equalsIgnoreCase(existingItem.getCategory())) {
	                throw new BadMealCompositionCreationException("attempt to create full-meal with duplicate categories");
	            }
			}
		}
		
		if ( (this.isVegetarian) && (!item.isVegetarian)) {
			throw new BadMealCompositionCreationException("attempt to add non-vegetarian item to a vegetarian meal");
		}
		
		if ( (this.isGlutenFree) && (!item.isGlutenFree)) {
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
	/*
	public static void main(String[] args) throws BadMealCompositionCreationException {
		Meal meal1 = new Meal("Special Meal",false,true,true,true);
		FoodItem f1 = new FoodItem ("Entrée","Starter",true,true,80);
		meal1.addItem(f1);
		System.out.println(meal1.mealComposition.get(0).getName());
		FoodItem f2 = new FoodItem ("Entrée2","Maindish",true,true,90);
		meal1.addItem(f2);
		FoodItem f3 = new FoodItem ("Entrée2","dessert",true,true,90);
		meal1.addItem(f3);
		FoodItem f4 = new FoodItem ("Entrée2","Maindish",true,true,90);
		meal1.addItem(f4);
		
	}
	*/
	
}
	
		

