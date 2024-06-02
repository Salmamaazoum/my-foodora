package appSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import user.Courier;
import user.Customer;
import user.Manager;
import user.User;
import user.UserIdGenerator;

public class AppSystem {
    /**
     * Singleton instance of the Application
      */
	
	private static AppSystem instance = null;
	private static List<Manager> managers;
	private static List<Customer> customers;
	private static List<Courier> couriers;
	private static Optional<User> currentUser;
	
	public List<Manager> getManagers() {
		return managers;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public Optional<User> getCurrentUser() {
		return currentUser;
	}
	
	private AppSystem() {
		
		AppSystem.managers = new ArrayList<>();
		AppSystem.customers = new ArrayList<>();
		AppSystem.couriers = new ArrayList<>();
		
        // Add default managers
        Manager salma = new Manager("Salma", "Salma", "1234", "Salma");
        Manager hala = new Manager("Hala", "Hala", "1234", "Salma");
        
        AppSystem.managers.add(salma);
        AppSystem.managers.add(hala);
        AppSystem.currentUser = null;
        
	}


    public static AppSystem getInstance() {
        if (instance==null) {
            instance = new AppSystem();
        }
        return instance;
    }
    
    public boolean login(String username, String password) {
    	
        for (Manager manager : managers) {
            if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
            	AppSystem.currentUser = Optional.of(manager);
                return true; // Login successful
            }
        }
        
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
            	AppSystem.currentUser = Optional.of(customer);
                return true; // Login successful
            }
        }
        
        for (Courier courier : couriers) {
            if (courier.getUsername().equals(username) && courier.getPassword().equals(password)) {
            	AppSystem.currentUser = Optional.of(courier);
                return true; // Login successful
            }
        }
        
        return false; // Login failed
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
}
