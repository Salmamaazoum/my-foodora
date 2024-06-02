package user;

import food.*;
import java.util.*;

import appSystem.AppSystem;

public class Restaurant extends User {

	private Coordinate address;
	private Menu menu;
	private ArrayList<Meal> meals;
	private double genericDiscount;
	private double specialDiscount;
	private MealPriceCalculationStrategy mealPriceStrategy;

	public Restaurant(String name, String username, String password, Coordinate address,
			double genericDiscount, double specialDiscount) {
		super(name, username, password);
		this.address = address;
		this.meals = new ArrayList<Meal>();
		this.menu = new Menu();
		this.genericDiscount = genericDiscount;
		this.specialDiscount = specialDiscount;
		this.mealPriceStrategy = new MealPriceCalculationStrategyDiscount();
	}
	
	public Restaurant(String name, String username, String password) {
		super(name, username, password);
		this.meals = new ArrayList<Meal>();
		this.menu = new Menu();
		this.mealPriceStrategy = new MealPriceCalculationStrategyDiscount();
	}

	// Getters and Setters

	public Coordinate getLocation() {
		return address;
	}

	public void setLocation(Coordinate adress) {
		this.address = adress;
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
		if (meal.isMealOfTheWeek()) {
			NotificationService.getInstance().setOffer(meal,this);
			meal.setPrice(this.mealPriceStrategy.calculatePrice(meal, this.specialDiscount));
		}
		else {
			meal.setPrice(this.mealPriceStrategy.calculatePrice(meal, this.genericDiscount));
		}
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

	
	public void setMealPrice(Meal meal) {
		if (meal.isMealOfTheWeek()){
			meal.setPrice(this.mealPriceStrategy.calculatePrice(meal, this.specialDiscount));
		}
		else {
			meal.setPrice(this.mealPriceStrategy.calculatePrice(meal, this.genericDiscount));
		}

		
	}
	
	public void setSpecialOffer(Meal meal) {
		if (!meal.isMealOfTheWeek()) {
			meal.setMealOfTheWeek(true);
			this.setMealPrice(meal);
			NotificationService.getInstance().setOffer(meal,this);
		}
		
	}
	
	public void removeFromSpecialOffer(Meal meal) {
		if (meal.isMealOfTheWeek()) {
			meal.setMealOfTheWeek(false);
			this.setMealPrice(meal);
		}
	}
}
