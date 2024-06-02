package user;

import appSystem.AppSystem;

public class Manager extends User {
	
	String surname;
	
    public Manager( String name, String username, String password, String surname) {
        super( name, username, password);
        this.surname = surname;
    }
    
    public boolean addNewUser(User user) {
    	
    	AppSystem appSystem = AppSystem.getInstance();
	
        if (user instanceof Manager) {
            return appSystem.addManager((Manager) user);
            
        } else if (user instanceof Customer) {
            return appSystem.addCustomer((Customer) user);
            
        } else if (user instanceof Courier) {
        	return appSystem.addCourier((Courier) user);
        }
        return false; // If user is of an unknown type
    }
    
}
