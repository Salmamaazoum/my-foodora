package user;

import food.*;
import java.util.*;

import appSystem.AppSystem;

public class Restaurant extends User {

	private String restaurantName;
	private Coordinate adress;
	private Menu menu;
	private ArrayList<Meal> meals;
	private double genericDiscount;
	private double specialDiscount;
	private MealPriceCalculationStrategy mealPriceStrategy;

	public Restaurant(String name, String username, String password, String restaurantName, Coordinate adress,
			double genericDiscount, double specialDiscount) {
		super(name, username, password);
		this.restaurantName = restaurantName;
		this.adress = adress;
		this.meals = new ArrayList<Meal>();
		this.menu = new Menu();
		this.genericDiscount = genericDiscount;
		this.specialDiscount = specialDiscount;
		this.mealPriceStrategy = new MealPriceCalculationStrategyDiscount();
	}

	// Getters and Setters

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}

	public double getGenericDiscount() {
		return genericDiscount;
	}

	public double getSpecialDiscount() {
		return specialDiscount;
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

	public void setGenericDiscount(double genericDiscount) {
		this.genericDiscount = genericDiscount;
	}

	public void setSpecialDiscount(double specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	// ============================

	// Set the Meal Pricing Strategy, a priori using Discounts

	public void setPriceStrategy(MealPriceCalculationStrategy mealPriceStrategy) {
		this.mealPriceStrategy = mealPriceStrategy;
	}

	// Compute a meal's price depending on whether it is special or not

	public double getMealPrice(Meal meal) {
		if (meal.isMealOfTheWeek()) {
			return this.mealPriceStrategy.calculatePrice(meal, this.specialDiscount);
		}

		else {
			return this.mealPriceStrategy.calculatePrice(meal, this.genericDiscount);
		}

	}

}
