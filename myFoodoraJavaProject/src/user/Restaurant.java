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
	private MealPriceCalculationStrategy mealPriceStrategy = new MealPriceCalculationStrategyDiscount();
	private FoodFactory foodFactory = new FoodFactory();

	public Restaurant(String name, String username, String password, Coordinate address,
			double genericDiscount, double specialDiscount) {
		super(name, username, password);2
		
		this.address = address;
		
		this.meals = new ArrayList<Meal>();
		
		this.menu = new Menu();
		this.genericDiscount = genericDiscount;
		this.specialDiscount = specialDiscount;

	}
	
	public Restaurant(String name, String username, String password) {
		super(name, username, password);
		this.meals = new ArrayList<Meal>();
		this.menu = new Menu();

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
	
	/*
	 * Create a meal method to use it in appSystem
	 */
	
	public Meal createMeal(String mealName, String mealType) {
		Meal meal = this.foodFactory.createMeal(mealName, mealType);
		return meal;
	}
	
	public Meal createMeal(String mealName, String mealType, String StandardOrVeg, String isGlutenFree) {
		Meal meal = this.foodFactory.createMeal(mealName, mealType,  StandardOrVeg,  isGlutenFree);
		return meal;
	}

	/*
	 *  Modify the Menu
	 */
	
	public void addDishRestaurantMenu(FoodItem item) {
		this.menu.addItem(item);
	}

	public void addDishRestaurantMenu(String dishName, String dishCategory,String foodType,String glutenFree, String unitPrice) {
		FoodItem dish = this.foodFactory.createDish(dishName, dishCategory, foodType, glutenFree, unitPrice);
		this.menu.addItem(dish);
	}

	public void removeItemMenu(FoodItem item) {
		this.menu.removeItem(item);
	};
	
	public void removeItemMenu(String dishName) throws NotFoundException{
		FoodItem dish = this.findDishUsingName(dishName);
		this.menu.removeItem(dish);
	}

	// ========================

	// Add and Remove Meals
	
	public void addMeal(String mealName, String mealType) {
		Meal meal = this.foodFactory.createMeal(mealName, mealType);
		this.meals.add(meal);
	}
	
	public void addMeal(String mealName, String mealType, String StandardOrVeg, String isGlutenFree) {
		Meal meal = this.foodFactory.createMeal(mealName, mealType, StandardOrVeg, isGlutenFree);
		this.meals.add(meal);
	}
	
	/*
	 * In case a Restaurants creates a meal and sets mealOfTheWeek= True then adds it to Menu
	 */

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
	
	public FoodItem findDishUsingName(String dishName) throws NotFoundException {
		for (FoodItem dish : this.menu.getItems()) {
			if(dish.getName().equalsIgnoreCase(dishName)) {
				return dish;
			}
		}
		
		throw new NotFoundException("There is no dish with such name in the Menu.");

	}
	
	public Meal findMealUsingName(String mealName) throws NotFoundException {
		for (Meal meal : this.meals) {
			if(meal.getName().equalsIgnoreCase(mealName)) {
				return meal;
			}
		}
		
		throw new NotFoundException("There is no meal with such name.");

	}
	
	public void addDish2Meal(String mealName, String dishName) throws NotFoundException, BadMealCompositionCreationException {
		FoodItem dish = this.findDishUsingName(dishName);
		Meal meal = this.findMealUsingName(mealName);
		meal.addItem(dish);
	}
}
