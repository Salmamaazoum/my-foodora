package user;
import java.util.*;

import food.BadMealCompositionCreationException;
import food.FoodItem;
import food.Meal;
import food.MealPriceCalculationStrategy;
import food.MealPriceCalculationStrategyDiscount;
import food.Menu;

public class Restaurant extends User {
	
	private String restaurantName;
	private Coordinate adress;
	private Menu menu;
	private ArrayList<Meal> meals;
	private double genericDiscount;
	private double specialDiscount;
	private MealPriceCalculationStrategy mealPriceStrategy;
	


	public Restaurant(String name, String username, String password, String restaurantName, Coordinate adress, double genericDiscount, double specialDiscount) {
		super(name, username, password);
		this.restaurantName = restaurantName;
		this.adress = adress;
		this.meals = new ArrayList<Meal>();
		this.menu = new Menu();
		this.genericDiscount = genericDiscount;
		this.specialDiscount = specialDiscount;
		this.mealPriceStrategy = new MealPriceCalculationStrategyDiscount();
		}
	
	
	// Modify the Menu 
	
	public void addItemMenu(FoodItem item) {
		this.menu.addItem(item);
	}
	
	public void removeItemMenu(FoodItem item) {
		this.menu.removeItem(item);
	};
	
	// ========================
	
	// Add and Remove Meals
	
	public void addMeal(Meal meal) {
		this.meals.add(meal);
	}
	
	public void removeMeal(Meal meal) {
		if (meals.contains(meal)) {
			this.meals.remove(meal);
		}
	}
	
	// =============================
	
	// Establish Generic and Special Discounts
	
	public void setGenericDiscount(double genDiscount) {
		this.genericDiscount=genDiscount;
	}
	
	public void setSpecialDiscount(double speDiscount) {
		this.specialDiscount=speDiscount;
	}
	
	// ============================
	
	// Set the Meal Pricing Strategy, a priori using Discounts
	
	public void setPriceStrategy( MealPriceCalculationStrategy mealPriceStrategy) {
		this.mealPriceStrategy=mealPriceStrategy;
	}
	
	// Compute a meal's price depending on whether it is special or not
	
	public double getMealPrice(Meal meal) {
		if (meal.isMealOfTheWeek()){
			return this.mealPriceStrategy.calculatePrice(meal, this.specialDiscount);
		}
	
		else {
			return this.mealPriceStrategy.calculatePrice(meal, this.genericDiscount);
		}
	
	}
	
}
	
	