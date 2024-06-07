package user;
import java.util.*;
import java.util.Map.Entry;

import food.FoodItem;
import food.Meal;
import appSystem.AppSystem;

public class Order {
	
	private Restaurant restaurant;
	
    private static int idCounter = 0;

    private int id;
    
    private Calendar orderDate; 
    
    private Customer customer;
    
    private Map<FoodItem,Integer> orderItems = new HashMap<FoodItem,Integer>(); //map food item into its corresponding quantity
	
    private Map<Meal,Integer> orderMeals = new HashMap<Meal,Integer>();
    
    private double price;

    
    public Order (String restaurantName, Customer customer) {
    	for (Restaurant restaurant : AppSystem.getRestaurants()) {
    		if (restaurant.getName().equalsIgnoreCase(restaurantName))
    			this.restaurant=restaurant;   		
    	}
    	
    	this.id = ++idCounter;
    	this.orderDate = Calendar.getInstance();
    	this.customer=customer;
    	
    }
    
    public void addItem(String foodName, int quantity) {   // add exception if name does not exist in Menu?
    	for (FoodItem item : restaurant.getMenu().getItems()) {
    		if (item.getName().equalsIgnoreCase(foodName)) {
    			orderItems.put(item,quantity);
    			return;
    		}
    	}
    	
    	for (Meal meal : restaurant.getMeals()) {
    		if(meal.getName().equalsIgnoreCase(foodName)) {
    			orderMeals.put(meal, quantity);
    		}
    	}
    }
    

    
    public double getPrice() {
    	double price = 0;
    	for (Entry<Meal, Integer> entry : orderMeals.entrySet())  {
    		price+=entry.getKey().getPrice()*entry.getValue();
    	}
    	for (Entry<FoodItem, Integer> entry : orderItems.entrySet()) {
    		price+=entry.getKey().getPrice()*entry.getValue();
    	}
    	return price;
    }

	@Override
	public String toString() {
		return "Order [restaurant=" + restaurant + ", id=" + id + ", orderItems=" + orderItems + ", orderMeals="
				+ orderMeals + "]";
	}
    
    
    
}
