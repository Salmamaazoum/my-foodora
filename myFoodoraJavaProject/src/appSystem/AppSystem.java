package appSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import food.*;

import javax.naming.NoPermissionException;

import user.*;

public class AppSystem {
    /**
     * Singleton instance of the Application
     */
    private static AppSystem instance = null;
    private static List<Manager> managers;
    private static List<Customer> customers;
    private static List<Courier> couriers;
    private static List<Restaurant> restaurants;
    private static Optional<User> currentUser;
    private static Optional<UserType> currentUserType;

    public List<Manager> getManagers() {
        return managers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public Optional<User> getCurrentUser() {
        return currentUser;
    }

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }
    
    

    public static Optional<UserType> getCurrentUserType() {
		return currentUserType;
	}

	private AppSystem() {

        managers = new ArrayList<>();
        customers = new ArrayList<>();
        couriers = new ArrayList<>();
        restaurants = new ArrayList<>();

        // Add default managers
        Manager salma = new Manager("Salma", "Salma", "1234", "Salma");
        Manager hala = new Manager("Hala", "Hala", "1234", "Salma");

        managers.add(salma);
        managers.add(hala);
        currentUser = Optional.empty();
        currentUserType = Optional.empty();

    }

    public static AppSystem getInstance() {
        if (instance == null) {
            instance = new AppSystem();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        if (tryLogin(managers, username, password, UserType.MANAGER) ||
                tryLogin(customers, username, password, UserType.CUSTOMER) ||
                tryLogin(couriers, username, password, UserType.COURIER)||
                tryLogin(restaurants, username, password, UserType.RESTAURANT) ) {
            return true; // Login successful
        }
        return false; // Login failed
    }

    public void logout() {
        currentUserType = Optional.empty();
        currentUserType = Optional.empty();
    }
    
    // Manager Tasks

    public void registerCustomer(Customer customer) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		customers.add(customer);
    	} else {
    	throw new NoPermissionException("Only managers can perform this action.");
    	}
    }

    public void registerRestaurant(Restaurant restaurant) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		restaurants.add(restaurant);
    	} else {
    		throw new NoPermissionException("Only managers can perform this action.");
    	}
    }

    public void registerCourier(Courier courier) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		couriers.add(courier);
    	} else {
    	throw new NoPermissionException("Only managers can perform this action.");
    	}
    }
    
    //============================================
    // Customer Tasks
    
    public Order createOrder(String restaurantName) throws NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
    		return new Order(restaurantName);
    	}
    	else
    		throw new NoPermissionException("Only Customers can perform this action");
    }
    
    public void addItem2Order(Map<String, Order> customerOrders, String itemName, String orderName) throws NoPermissionException, NotFoundException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
	    	for (Entry<String,Order> entry : customerOrders.entrySet()) {
				if (entry.getKey().equals(orderName)) {
					entry.getValue().addItem(itemName, 1);				}
	    	}
	    
	    	throw new NotFoundException("No order with such name exists");
    	}
    	else
    		throw new NoPermissionException("Only Customers can perform this action");
    	
    }
    
    //à faire, pay order how? change parameters manager profit etc
    
    public void endOrder(String orderName, String date,Map<String, Order> customerOrders) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
	    	for (Entry<String,Order> entry : customerOrders.entrySet()) {
				if (entry.getKey().equals(orderName)) {
					((Customer)currentUser.get()).getOrderHistory().put(entry.getValue(), date);
				}
	    	}
    	}
    	else
    		throw new NoPermissionException("Only Customers can perform this action");
    }
    
    //===========================
    // Restaurant Tasks
    
    public void addDishRestaurantMenu(String DishName, String DishCategory,String foodType,String glutenFree, String unitPrice) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
	    	((Restaurant)currentUser.get()).addDishRestaurantMenu(DishName, DishCategory, foodType, glutenFree, unitPrice);
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    	
    	
    }
    
    public Meal createMeal (String mealName, String mealType) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		return ((Restaurant)currentUser.get()).createMeal(mealName, mealType);
    	}
    	else {
    		throw new NoPermissionException("Only Restaurants can perform this action");
    	}
    }
    
    public Meal createMeal (String mealName, String mealType,String StandardOrVeg, String isGlutenFree) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		return ((Restaurant)currentUser.get()).createMeal(mealName, mealType, StandardOrVeg,  isGlutenFree);
    	}
    	else {
    		throw new NoPermissionException("Only Restaurants can perform this action");
    	}
    }
    
    
    
    
    
    public void addDish2Meal(String dishName, String mealName, Map<String,Meal> createdMeals) throws NotFoundException, NoPermissionException, BadMealCompositionCreationException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		for (Entry<String,Meal> entry : createdMeals.entrySet()) {
				if (entry.getKey().equals(mealName)) {
					((Restaurant)currentUser.get()).addDish2Meal(mealName, dishName);
				}
    		}
    		
    		throw new NotFoundException("No meal with such name has been created. Please create a new Meal with name "+mealName+" using command createMeal.");
    		
    	}
    	else
    		throw new NoPermissionException("Only Customers can perform this action");
    }
    
    public void saveMeal (String mealName, Map<String,Meal> createdMeals) {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		int i=0;
	    	for (Entry<String,Meal> entry : createdMeals.entrySet()) {
				if (entry.getKey().equals(mealName)) {
					i=1;
				}
	    	}
    	}
    }

    private <T extends User> boolean tryLogin(List<T> users, String username, String password, UserType typeofUser) {
        for (T user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = Optional.of(user);
                currentUserType = Optional.of(typeofUser);
                return true;
            }
        }
        return false;
    }


}
