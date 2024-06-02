package appSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import user.Courier;
import user.Customer;
import user.Manager;
import user.User;
import user.Restaurant;

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

    public enum UserType {
        MANAGER,
        CUSTOMER,
        COURIER,
        RESTAURANT
    }
	
	public List<Manager> getManagers() {
		return managers;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public Optional<User> getCurrentUser() {
		return currentUser;
	}
	
	public static List<Restaurant> getRestaurants() {
		return restaurants;
	}

	private AppSystem() {
		
		AppSystem.managers = new ArrayList<>();
		AppSystem.customers = new ArrayList<>();
		AppSystem.couriers = new ArrayList<>();
		AppSystem.restaurants = new ArrayList<>();
		
        // Add default managers
        Manager salma = new Manager("Salma", "Salma", "1234", "Salma");
        Manager hala = new Manager("Hala", "Hala", "1234", "Salma");
        
        AppSystem.managers.add(salma);
        AppSystem.managers.add(hala);
        AppSystem.currentUser = Optional.empty();
        AppSystem.currentUserType = Optional.empty();
        
	}


    public static AppSystem getInstance() {
        if (instance==null) {
            instance = new AppSystem();
        }
        return instance;
    }
    
    public boolean login(String username, String password) {
        if (tryLogin(managers, username, password, UserType.MANAGER) || 
            tryLogin(customers, username, password, UserType.CUSTOMER) || 
            tryLogin(couriers, username, password, UserType.COURIER)) {
            return true; // Login successful
        }
        return false; // Login failed
    }

    private <T extends User> boolean tryLogin(List<T> users, String username, String password, UserType typeofUser) {
        for (T user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                AppSystem.currentUser = Optional.of(user);
                AppSystem.currentUserType = Optional.of(typeofUser);
                return true;
            }
        }
        return false;
    }
    
    public void logout() {
    	AppSystem.currentUser = null;
    }
    
    public boolean addCustomer(Customer customer) {
    	AppSystem.customers.add(customer);
    	return true;
    }
    
    public boolean addManager(Manager manager) {
    	AppSystem.managers.add(manager);
    	return true;
    }
    
    public boolean addCourier(Courier courier) {
    	AppSystem.couriers.add(courier);
    	return true;
    }
    
    public boolean addRestaurant(Restaurant restaurant) {
    	AppSystem.restaurants.add(restaurant);
    	return true;
    }
    
}
