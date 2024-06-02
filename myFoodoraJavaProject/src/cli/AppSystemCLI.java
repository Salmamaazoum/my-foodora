package cli;
import java.util.*;
import java.util.Map.Entry;

import appSystem.AppSystem;
import food.Meal;
import user.Coordinate;
import user.Courier;
import user.Customer;
import user.Restaurant;
import user.User;
import user.Order;
import user.UserType;

public class AppSystemCLI {
    private static AppSystem appSystem;
    private static Map<String,Order> customerOrders = new HashMap<String,Order>();

    public static void main(String[] args) {
        appSystem = AppSystem.getInstance();
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("Welcome to the AppSystem CLI!");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }
            handleCommand(input);
        }
        
        scanner.close();
    }

    private static void handleCommand(String input) {
        String[] parts = input.split(" ");
        String command = parts[0];
        
        switch (command) {
        
            case "login":
                if (parts.length == 3) {
                    String username = parts[1];
                    String password = parts[2];
                    if (appSystem.login(username, password)) {
                        System.out.println("Login successful!");
                        
                        // In case of Customer Login
                        if (AppSystem.getCurrentUserType().get().equals(UserType.CUSTOMER)) {
                        	
                        	// Display available restaurants
                        	if (!AppSystem.getRestaurants().isEmpty()) {
	                        	System.out.println("Here is the list of available restaurants :");
	                        	for (Restaurant restaurant : AppSystem.getRestaurants()) {
	                        		System.out.print(restaurant.getName()+" ");
                        	    }
                        	}
                        	
                        }
                    } else {
                        System.out.println("Login failed.");
                    }
                } else {
                    System.out.println("Usage: login <username> <password>");
                }
                break;

            case "logout":
                appSystem.logout();
                System.out.println("Logged out.");
                customerOrders = new HashMap<String,Order>();
                break;
                
            case "registerCustomer":
                if (parts.length == 5) {
                	
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String username = parts[3];
                    String password = parts[4];
                    
                    Customer customer = new Customer(firstName, username, password, lastName, new Coordinate());
                    
                    try {
                    	appSystem.registerCustomer(customer);
                        System.out.println("Registering successful!");
                    } catch (Exception e) {
                        System.out.println("Registering failed! " + e.getMessage());
                    }
                } else {
                    System.out.println("Usage: registerCustomer <firstName> <lastName> <username> <address> <password>");
                }
                break;            	
                
            case "registerRestaurant":
                if (parts.length == 4) {
                	
                    String name = parts[1];
                    String username = parts[2];
                    String password = parts[3];
                    
                    Restaurant restaurant = new Restaurant(name, username, password);
                    
                    try {
                    	appSystem.registerRestaurant(restaurant);
                        System.out.println("Registering successful!");
                    } catch (Exception e) {
                        System.out.println("Registering failed! " + e.getMessage());
                    }
                } else {
                    System.out.println("Usage: registerRestaurant <name> <username> <password>");
                }
                break; 
                
            case "registerCourier":
                if (parts.length == 5) {
                	
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String username = parts[3];
                    String password = parts[4];
                    
                    Courier courier = new Courier(firstName, username, password, lastName);
                    
                    try {
                    	appSystem.registerCourier(courier);
                        System.out.println("Registering successful!");
                    } catch (Exception e) {
                        System.out.println("Registering failed! " + e.getMessage());
                    }
                } else {
                    System.out.println("Usage: registerRestaurant <name> <username> <password>");
                }
                break;
            
            case "createOrder":
            	if (parts.length == 3) {
            		String restaurantName = parts[1];
            		String orderName = parts[2];
            		
            		try {
            			Order order= appSystem.createOrder(restaurantName);
            			customerOrders.put(orderName,order);
            			System.out.println("Order from "+restaurantName+" created successfully");
            			
            			// Display Meals and Menu of Selected Restaurant
            			if (!AppSystem.getRestaurants().isEmpty()) {
            				for (Restaurant restaurant : AppSystem.getRestaurants()) {
            					if (restaurant.getName().equalsIgnoreCase(restaurantName)) {
            						System.out.println("Menu of "+restaurantName);
            						System.out.println(restaurant.getMenu());
            						if (!restaurant.getMeals().isEmpty()) {
            							System.out.println("Meals of "+restaurantName);
            							for (Meal meal : restaurant.getMeals()) {
            								System.out.println(meal);
            							}
            						}
            						
            					}
            				}
            			}
            			//==================================
            		}
            		catch (Exception e) {
            			System.out.println("Fail to create order! "+ e.getMessage());
            		}
            	}
            	else {
            		System.out.println("Usage : createOrder <restaurantName> <orderName>");
            	}
            	break;
            	
            case "addItem2Order":
            	if (parts.length==3) {
            		String orderName = parts[1];
            		String itemName = parts[2];
            		
            		try {
            			appSystem.addItem2Order(customerOrders, itemName, orderName);
            			System.out.println(itemName + " added sucessfully to order "+orderName);
            		}
            		catch (Exception e) {
            			System.out.println("Fail to add food Item! "+ e.getMessage());
            		}
            		
            	}
            	else {
            		System.out.println("Usage : createOrder <restaurantName> <orderName>");
            	}
            	break;
            	
            	
            default:
                System.out.println("Unknown command: " + command);
                break;
               
                
        }
    }
}
