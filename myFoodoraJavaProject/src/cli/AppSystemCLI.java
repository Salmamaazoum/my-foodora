package cli;

<<<<<<< HEAD
import java.util.Optional;
import java.util.Scanner;
import appSystem.AppSystem;
import user.Coordinate;
import user.Courier;
import user.Customer;
import user.Restaurant;
import user.User;

public class AppSystemCLI {
    private static AppSystem appSystem;

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
                
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}
=======
import java.util.Scanner;
import appSystem.AppSystem;
import user.Coordinate;
import user.Customer;

public class AppSystemCLI {
}
>>>>>>> main
