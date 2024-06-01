package myFoodoraJavaProject;
import java.util.*;

public class Restaurant {
	
	private String name;
	
	private static int nextid;
	
	private int iD;
	
	private Location location;
	
	private String username;
	
	private String password;
	
	private Menu menu;
	
	private ArrayList<Meal> meals;
	
	private double genericDiscount;
	
	private double specialDiscount;
	
	private MealPriceCalculationStrategy mealPriceStrategy;
	


	public Restaurant(String name, Location location, String username, String password, double genericDiscount, double specialDiscount) {
		super();
		this.name = name;
		this.location = location;
		this.username = username;
		this.password = password;
		this.meals = new ArrayList<Meal>();
		this.menu = new Menu();
		this.genericDiscount = genericDiscount;
		this.specialDiscount = specialDiscount;
		Restaurant.nextid++;
		this.iD=nextid;
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
	
	public static void main(String[] args) {
        // Create a restaurant
        Location location = new Location(12.34, 56.78);
        Restaurant restaurant = new Restaurant("Good Eats", location, "goodeats", "password123", 0.05, 0.10);

        // Add items to the menu
        FoodItem item1 = new FoodItem("Caesar Salad", "starter", true, true, 5.99);
        FoodItem item2 = new FoodItem("Grilled Chicken", "starter", true, true, 12.99);
        FoodItem item3 = new FoodItem("Cheesecake", "dessert", true, true, 6.99);
        restaurant.addItemMenu(item1);
        restaurant.addItemMenu(item2);
        restaurant.addItemMenu(item3);
        for (FoodItem item : restaurant.menu.getItems()) {
        	System.out.println(item.getName());
        }

        // Create a meal
        Meal meal = new Meal("Healthy Combo", false, true, true, false);
        try {
            meal.addItem(item1);
            meal.addItem(item2);
            meal.addItem(item3);
        } catch (BadMealCompositionCreationException e) {
            System.out.println("Error creating meal: " + e.getMessage());
        }

        // Add meal to the restaurant
        restaurant.addMeal(meal);

        // Get the price of the meal
        double price = restaurant.getMealPrice(meal);
        System.out.println("Price of " + meal.getName() + ": $" + price);
    }
	
}
	
	