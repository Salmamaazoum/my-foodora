package user;

import food.*;
import java.util.*;

import Exceptions.NotFoundException;
import NotificationService.NotificationService;
import NotificationService.Offer;
import appSystem.AppSystem;

public class Restaurant extends User {

	private Coordinate address;
	
	private Menu menu;
	
	private ArrayList<Meal> meals;
	
	private double genericDiscount = 0.05;
	
	private double specialDiscount = 0.1;
	
	private int orderCounter = 0;
	
	public int getOrderCounter() {
		return orderCounter;
	}

	public void incrementOrderCounter() {
		this.orderCounter ++;
	}

	private MealPriceCalculationStrategy mealPriceStrategy = new MealPriceCalculationStrategyDiscount();
	
	private FoodFactory foodFactory = new FoodFactory();

	public Restaurant(String name, String username, String password, Coordinate address,
			double genericDiscount, double specialDiscount) {
		super(name, username, password);
		
		this.address = address;
		
		this.meals = new ArrayList<Meal>();
		
		this.menu = new Menu();
		this.genericDiscount = genericDiscount;
		this.specialDiscount = specialDiscount;
		this.address= new Coordinate();

	}
	
	public Restaurant(String name, String username, String password) {
		super(name, username, password);
		this.meals = new ArrayList<Meal>();
		this.menu = new Menu();
		this.address= new Coordinate();

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

	/*
	 * Add meal based on the name, type, etc.
	 * Remove meal based on the name.
	 */
	
	public void addMeal(String mealName, String mealType) {
		Meal meal = this.foodFactory.createMeal(mealName, mealType);
		this.meals.add(meal);  //what if it already exists? override equals method
	}
	
	public void addMeal(String mealName, String mealType, String StandardOrVeg, String isGlutenFree) {
		Meal meal = this.foodFactory.createMeal(mealName, mealType, StandardOrVeg, isGlutenFree);
		this.meals.add(meal);
	}
	
	/*
	 * In case a Restaurants creates a meal and sets mealOfTheWeek= True then adds it to Menu
	 * Add and Remove meals using Meal Object
	 */

	public void addMeal(Meal meal) {
		if (meal.isMealOfTheWeek()) {
			NotificationService.getInstance().setOffer(meal,this,Offer.mealOfTheWeek);
			meal.setPrice(this.mealPriceStrategy.calculatePrice(meal, this.specialDiscount));
		}
		else {
			meal.setPrice(this.mealPriceStrategy.calculatePrice(meal, this.genericDiscount));
		}
		this.meals.add(meal);
	}

	public void removeMeal(Meal meal) {
		if (meals.contains(meal)) {  //needs Overriding of equals and hashCode
			this.meals.remove(meal);
		}
	}
	
	public void removeMeal(String mealName) throws NotFoundException {
		Meal meal = this.findMealUsingName(mealName);
		if (meals.contains(meal)) {  //needs Overriding of equals and hashCode
			this.meals.remove(meal);
		}
	}
	/*
	 * Display a meal
	 */
	
	public void showMeal(String mealName) throws NotFoundException {
		Meal meal = this.findMealUsingName(mealName);
		System.out.println(meal);
	}
	// =============================

	// Establish Generic and Special Discounts

	public void setGenericDiscount(double genericDiscount) {
		this.genericDiscount = genericDiscount;
		NotificationService.getInstance().setOffer(null,this,Offer.genericDiscount);
	}

	public void setSpecialDiscount(double specialDiscount) {
		this.specialDiscount = specialDiscount;
		NotificationService.getInstance().setOffer(null,this,Offer.specialDiscount);
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
			NotificationService.getInstance().setOffer(meal,this,Offer.mealOfTheWeek);
		}
		
	}
	
	public void setSpecialOffer(String mealName) throws NotFoundException {
		Meal meal = this.findMealUsingName(mealName);
		if (!meal.isMealOfTheWeek()) {
			meal.setMealOfTheWeek(true);
			this.setMealPrice(meal);
			NotificationService.getInstance().setOffer(meal,this,Offer.mealOfTheWeek);
		}
		
	}
	
	public void removeFromSpecialOffer(Meal meal) {
		if (meal.isMealOfTheWeek()) {
			meal.setMealOfTheWeek(false);
			this.setMealPrice(meal);
		}
	}
	
	public void removeFromSpecialOffer(String mealName) throws NotFoundException {
		Meal meal = this.findMealUsingName(mealName);
		if (meal.isMealOfTheWeek()) {
			meal.setMealOfTheWeek(false);
			this.setMealPrice(meal);
		}
	}
	
	/*
	 * Find Meal or Dish Using the Name
	 */
	
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
		
		throw new NotFoundException("There is no meal with such name. Please create a new Meal with name "+mealName+ " using command createMeal.");

	}
	
	/*
	 * Add a Dish to a certain meal
	 */
	
	public void addDish2Meal(String mealName, String dishName) throws NotFoundException, BadMealCompositionCreationException {
		FoodItem dish = this.findDishUsingName(dishName);
		Meal meal = this.findMealUsingName(mealName);
		meal.addItem(dish);
		meal.setPrice(meal.getPrice()+(1-this.genericDiscount)*dish.getPrice());
	}
	
	public void showSortedHalfMeals() {
		
		ArrayList<MenuComponent> halfMeals = new ArrayList<>();
	        for (Meal meal : this.meals) {
	            if (meal instanceof HalfMeal) {
	                halfMeals.add(meal);
	            }
	        }

	    OrderedFrequencySorter sorter = new OrderedFrequencySorter();
	    ArrayList<MenuComponent> sortedHalfMeals = sorter.sort(halfMeals);
		System.out.println("Sorted Half Meals");
		for (MenuComponent item : sortedHalfMeals) {
			System.out.println(item.getName()+" : ordered "+item.getOrderedFrequency() +(item.getOrderedFrequency()==1 ? " time." : " times."));
		}
	}
	
	public void showSortedDishes() {
		
		ArrayList<MenuComponent> dishes = new ArrayList<MenuComponent>(this.menu.getItems());
	    OrderedFrequencySorter sorter = new OrderedFrequencySorter();
	    ArrayList<MenuComponent> sortedDishes = sorter.sort(dishes);
		System.out.println("Sorted Dishes");
		for (MenuComponent item : sortedDishes) {
			System.out.println(item.getName()+" : ordered "+item.getOrderedFrequency() +(item.getOrderedFrequency()==1 ? " time." : " times."));
		}
	}
	
	public void displayRestaurant() {
	    StringBuilder output = new StringBuilder();

	    output.append("Menu of ").append(this.name).append(":\n");

	    if (this.menu.getItems().isEmpty()) {
	        output.append("No items in the menu.\n");
	    } else {
	        output.append(this.menu.toString()).append("\n"); 
	    }
	    

	    if (!this.meals.isEmpty()) {
	        output.append("Meals offered at ").append(this.name).append(":\n");
	        for (Meal meal : this.meals) {
	            output.append(meal.toString()).append("\n");
	        }
	    } else {
	        output.append("No meals currently offered.\n");
	    }

	    System.out.println(output.toString());
	}
	
	@Override
	public String toString() {
		return "Restaurant [address=" + address + ", genericDiscount=" + genericDiscount + ", specialDiscount="
				+ specialDiscount + ", orderCounter=" + orderCounter + ", id=" + id + ", name=" + name + ", username="
				+ username + "]";
	}

	
}
