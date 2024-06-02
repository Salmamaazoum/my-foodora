package appSystem;

import user.Customer;
import user.Manager;
import user.Coordinate;

public class AppTest {
	public static void main(String[] args) {
		AppSystem appSystem = AppSystem.getInstance();
		
		// Login Manager
		System.out.println(appSystem.login("Salma", "1234"));
		
		//Add customer 1 by the manager 1
		Customer customer1 = new Customer("Layla1","Layla1", "1234", "Layla", new Coordinate(), "layla@gmail.com", "0667890987");
		
		Manager manager1 = appSystem.getManagers().get(0);
		
		System.out.println(manager1.addNewUser(customer1));
		System.out.println(appSystem.getCustomers().get(0));
		
		//Add customer 2 by the manager 2
		Customer customer2 = new Customer("Layla2","Layla2", "1234", "Layla", new Coordinate(), "layla@gmail.com", "0667890987");
		
		Manager manager2 = appSystem.getManagers().get(1);
		
		System.out.println(manager2.addNewUser(customer2));
		System.out.println(appSystem.getCustomers().get(1));
		
		// Login customer 1
		System.out.println(appSystem.login("Layla1", "1234"));
		
	}
	

}
