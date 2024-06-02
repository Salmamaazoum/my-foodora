package user;

import appSystem.AppSystem;

public class Manager extends User {
	
	String surname;
	
    public Manager( String name, String username, String password, String surname) {
        super( name, username, password);
        this.surname = surname;
    }
    
    
}
