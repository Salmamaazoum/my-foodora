package cli;

import java.util.Scanner;
import appSystem.AppSystem;
import user.Coordinate;
import user.Customer;

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
        
        switch (command.toLowerCase()) {
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
                    
                    if (appSystem.login(username, password)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Registering failed.");
                    }
                } else {
                    System.out.println("Usage: registerCustomer <firstName> <lastName> <username> <address> <password>");
                }
                break;            	
                
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}
