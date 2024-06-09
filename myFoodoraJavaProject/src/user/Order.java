package user;
import java.util.*;
import java.util.Map.Entry;

import FidelityCards.FidelityCard;
import food.FoodItem;
import food.Meal;
import appSystem.AppSystem;

public class Order {
	
	private Restaurant restaurant;
	
    private static int idCounter = 0;

    private int id;
    
    private Calendar orderDate; 
    
    private Customer customer;
    
    private Courier courier;
    
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
    

    
    public double getFirstPrice() {
    	
    	double price = 0;
    	
    	for (Entry<Meal, Integer> entry : orderMeals.entrySet())  {
    		price+=entry.getKey().getPrice()*entry.getValue();
    	}
    	
    	for (Entry<FoodItem, Integer> entry : orderItems.entrySet()) {
    		price+=entry.getKey().getPrice()*entry.getValue();
    	}
    	
    	return price;
    }
    
    public double getFinalPrice() {
    	
    	FidelityCard fidelityCard = this.customer.getFidelityCard();
    	
    	return fidelityCard.computeOrderPrice(this);
    }
    
    public void submitOrder(double price) {
    	
    	this.customer.getOrderHistory().add(this);
    	
    	/*
    	 * Update ordered frequency of chosen items of this submitted order!
    	 */
    	
    	for (Entry<Meal, Integer> entry : orderMeals.entrySet())  {
    		entry.getKey().updateOrderedFrequency();
    	}
    	
    	for (Entry<FoodItem, Integer> entry : orderItems.entrySet()) {
    		entry.getKey().updateOrderedFrequency();
    	}
    	
    	
    	// Display the order, along with the price 
    	
    	System.out.println("Order : ");
    	System.out.println(this);
    	System.out.println("The price of the order is: "+ price);
			
	}
    	
    
    
    
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Map<FoodItem, Integer> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Map<FoodItem, Integer> orderItems) {
		this.orderItems = orderItems;
	}

	public Map<Meal, Integer> getOrderMeals() {
		return orderMeals;
	}

	public void setOrderMeals(Map<Meal, Integer> orderMeals) {
		this.orderMeals = orderMeals;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Order ID: ").append(id).append("\n");
	    sb.append("Order Date: ").append(this.orderDate.getTime()).append("\n");
	    sb.append("Restaurant: ").append(restaurant.getName()).append("\n");
	    
	    
	    int itemIndex = 1;  // Proper use of an integer for indexing

	    if (!orderMeals.isEmpty()) {
	        sb.append("\nMeals Ordered:\n");
	        for (Entry<Meal, Integer> entry : orderMeals.entrySet()) {
	            sb.append("  ").append(itemIndex++).append(". ").append(entry.getKey().getName())
	              .append(" - Quantity: ").append(entry.getValue())
	              .append(", Price per unit: ").append(entry.getKey().getPrice())
	              .append("\n");
	        }
	    }
	    
	    if (!orderItems.isEmpty()) {
	        sb.append("\nFood Items Ordered:\n");
	        for (Entry<FoodItem, Integer> entry : orderItems.entrySet()) {
	            sb.append("  ").append(itemIndex++).append(". ").append(entry.getKey().getName())
	              .append(" - Quantity: ").append(entry.getValue())
	              .append(", Price per, unit: ").append(entry.getKey().getPrice())
	              .append("\n");
	        }
	    }
	    return sb.toString();
	}
    
    
    
}
