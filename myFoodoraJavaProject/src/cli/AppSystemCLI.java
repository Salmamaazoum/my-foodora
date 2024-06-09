package cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import appSystem.AppSystem;
import user.Coordinate;
import user.Courier;
import user.Customer;
import user.Order;
import user.Restaurant;
import user.UserType;

public class AppSystemCLI {
	private static AppSystem appSystem;
	private static Map<String, Order> customerOrders = new HashMap<String, Order>();
	
    private static void printWelcomeMessage() {
        System.out.println(
            "  _  _  _  _  ____  __    __  ____   __  ____   __  \n" +
            " ( \\/ )( \\/ )(  __)/  \\  /  \\(    \\ /  \\(  _ \\ / _\\ \n" +
            " / \\/ \\ )  (  ) _)(  O )(  O )) D ((  O ))   //    \\\n" +
            " \\_)(_/(__/  (__)  \\__/  \\__/(___/ \\__/(__\\_)\\_/ \\_/\n" +
            "\n              Deliciously Delivered ☺"
            + "\n"+
            "\nWelcome to myFoodora!"
        );
    }

	public static void main(String[] args) {
		appSystem = AppSystem.getInstance();
		Scanner scanner = new Scanner(System.in);

		printWelcomeMessage();
		
		System.out.println("Please use 'login <username> <password>'.");
		System.out.println("For any inquiries regarding the commands, type 'help'.");

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
									System.out.print(restaurant.getName() + "\n");
									System.out.println("");
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

				customerOrders = new HashMap<String, Order>(); // Reset customerOrders to empty Map after Logout
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
					System.out
							.println("Usage: registerCustomer <firstName> <lastName> <username> <address> <password>");
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

			/*
			 * Customer related Tasks
			 */

			case "createOrder":
				if (parts.length == 3) {
					String restaurantName = parts[1];
					String orderName = parts[2];

					try {
						Order order = appSystem.createOrder(restaurantName);
						customerOrders.put(orderName, order);
						System.out.println("Order from Restaurant " + restaurantName + " created successfully");

						// Display Meals and Menu of Selected Restaurant
						if (!AppSystem.getRestaurants().isEmpty()) {
							for (Restaurant restaurant : AppSystem.getRestaurants()) {
								if (restaurant.getName().equalsIgnoreCase(restaurantName)) {
									restaurant.displayRestaurant();
								}
							}
						}		
						// ==================================
					} catch (Exception e) {
						System.out.println("Fail to create order! " + e.getMessage());
					}
				} else {
					System.out.println("Usage : createOrder <restaurantName> <orderName>");
				}
				break;

			case "addItem2Order":
				if (parts.length == 3) {
					String orderName = parts[1];
					String itemName = parts[2];

					try {
						appSystem.addItem2Order(customerOrders, itemName, orderName);
						System.out.println(itemName + " added sucessfully to order " + orderName);
					} catch (Exception e) {
						System.out.println("Fail to add food Item! " + e.getMessage());
					}

				} else {
					System.out.println("Usage : addItem2Order <orderName> <itemName>");
				}
				break;

			case "endOrder":
				if (parts.length == 2) {
					String orderName = parts[1];

					try {
						appSystem.endOrder(orderName, customerOrders);
						
					} catch (Exception e) {
						System.out.println("Fail to finalise order! " + e.getMessage());
					}
				} else {
					System.out.println("Usage : endOrder <orderName>");
				}
				break;
			
			case "historyOfOrders":
				if (parts.length == 1) {
					try {
						appSystem.displayHistoryOrders();
					} catch (Exception e) {
						System.out.println("Fail to display previous orders" + e.getMessage());
					}
				} else {
					System.out.println("Usage : historyOfOrders <>");
				}
				break;
				
			case "registerFidelityCard":
				if (parts.length == 2) {
					String cardtype = parts[1];
					try {
						appSystem.registerFidelityCard(cardtype);
					} catch (Exception e) {
						System.out.println("Fail to register Fidelity Card plan" + e.getMessage());
					}
				} else {
					System.out.println("Usage : registerFidelityCard <cardType>");
				}
				break;
				
			case "unregisterFidelityCard":
				if (parts.length == 1) {
					
					try {
						appSystem.unregisterFidelityCard();
					} catch (Exception e) {
						System.out.println("Fail to unregister Fidelity Card plan" + e.getMessage());
					}
				} else {
					System.out.println("Usage : unregisterFidelityCard <>");
				}
				break;

			/*
			 * Restaurant related Tasks
			 */
				
			case "showMenu":
				if (parts.length == 1) {
					try {
						appSystem.showMenu();

					} catch (Exception e) {
						System.out.println("Fail to display menu !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : showMenu <>");
				}
				break;
				
			case "setSpecialDiscountFactor":
				if (parts.length == 2) {
					String specialDiscountFactor = parts[1];
					try {
						appSystem.setSpecialDiscountFactor(specialDiscountFactor);
						System.out.println("Special discount updated successfully to "+specialDiscountFactor);

					} catch (Exception e) {
						System.out.println("Fail to set special discount factor !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : setSpecialDiscountFactor <specialDiscountFactor>");
				}
				break;
				
			case "setGenericDiscountFactor":
				if (parts.length == 2) {
					String genericDiscountFactor = parts[1];
					try {
						appSystem.setGenericDiscountFactor(genericDiscountFactor);
						System.out.println("Special discount updated successfully to "+genericDiscountFactor);

					} catch (Exception e) {
						System.out.println("Fail to set generic discount factor !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : setGenericDiscountFactor <genericDiscountFactor>");
				}
				break;

			case "addDishRestaurantMenu":
				if (parts.length == 6) {
					String dishName = parts[1];
					String dishCategory = parts[2];
					String foodType = parts[3];
					String glutenFree = parts[4];
					String unitPrice = parts[5];

					try {
						appSystem.addDishRestaurantMenu(dishName, dishCategory, foodType, glutenFree, unitPrice);
						System.out.println("Dish added successfully to the Menu");
					} catch (Exception e) {
						System.out.println("Fail to add Dish to Menu! " + e.getMessage());
					}

				} else {
					System.out.println(
							"Usage : addDishRestaurantMenu <dishName> <dishCategory> <foodType> <glutenFree (yes or no)> <unitPrice>");
				}
				break;

			case "createMeal":
				if (parts.length == 3) {
					String mealName = parts[1];
					String mealType = parts[2];
					System.out.println(
							"In the configuration you inserted, the meal is by default standard and gluten-containing.");

					try {
						appSystem.createMeal(mealName, mealType);
						System.out.println("Meal Created Successfully");
					} catch (Exception e) {
						System.out.println("Fail to create meal !" + e.getMessage());
					}
				} else if (parts.length == 5) {
					String mealName = parts[1];
					String mealType = parts[2];
					String standardOrVeg = parts[3];
					String isGlutenFree = parts[4];

					try {
						appSystem.createMeal(mealName, mealType, standardOrVeg, isGlutenFree);
						System.out.println("Meal Created Successfully");
					} catch (Exception e) {
						System.out.println("Fail to create meal !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : createMeal <mealName> <mealType (full or half) >");
					System.out.println(
							"Or : createMeal <mealName> <mealType (full/half)> <standardOrVegetarian> <GlutenFree (Yes/No)> ");
				}
				break;

			case "addDish2Meal":
				if (parts.length == 3) {
					String dishName = parts[1];
					String mealName = parts[2];
					try {
						appSystem.addDish2Meal(dishName, mealName);

						System.out.println("Dish Added successfully to Meal");
					} catch (Exception e) {
						System.out.println("Fail to add dish to Meal !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : addDish2Meal <dishName> <mealName>");
				}
				break;

			case "showMeal":
				if (parts.length == 2) {
					String mealName = parts[1];
					try {
						appSystem.showMeal(mealName);

					} catch (Exception e) {
						System.out.println("Fail to display meal !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : showMeal <mealName>");
				}
				break;

			case "setSpecialOffer":
				if (parts.length == 2) {
					String mealName = parts[1];
					try {
						appSystem.setSpecialOffer(mealName);

					} catch (Exception e) {
						System.out.println("Fail to set special offer on meal " + mealName + "!" + e.getMessage());
					}
				} else {
					System.out.println("Usage : setSpecialOffer <mealName>");
				}
				break;

			case "removeFromSpecialOffer":
				if (parts.length == 2) {
					String mealName = parts[1];
					try {
						appSystem.removeFromSpecialOffer(mealName);

					} catch (Exception e) {
						System.out.println("Fail to remove special offer from meal " + mealName + "!" + e.getMessage());
					}
				} else {
					System.out.println("Usage : setSpecialOffer <mealName>");
				}
				break;
<<<<<<< HEAD
			
            case "onDuty":
            	if (parts.length==2) {
            		String username = parts[1];
            		try {
            			appSystem.setOnDuty(username, true);
            			System.out.println("Statut changed successfully");
         
            		}
            		catch(Exception e){
            			System.out.println( e.getMessage());
            		}
            	}
            	else {
            		System.out.println("Usage : onDuty <username>");
            	}
            	break;
            	
            case "offDuty":
            	if (parts.length==2) {
            		String username = parts[1];
            		try {
            			appSystem.setOnDuty(username, false);
            			System.out.println("Statut changed successfully");
         
            		}
            		catch(Exception e){
            			System.out.println( e.getMessage());
            		}
            	}
            	else {
            		System.out.println("Usage : onDuty <username>");
            	}
            	break;
=======
				
			case "showSortedHalfMeals":
				if (parts.length == 1) {
					try {
						appSystem.showSortedHalfMeals();

					} catch (Exception e) {
						System.out.println("Fail to display sorted half meals !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : showSortedHalfMeals <>");
				}
				break;
				
			case "showSortedDishes":
				if (parts.length == 1) {
					try {
						appSystem.showSortedDishes();

					} catch (Exception e) {
						System.out.println("Fail to display sorted dishes !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : showSortedDishes <>");
				}
				break;
>>>>>>> main

			default:
				System.out.println("Unknown command: " + command);
				break;

		}
	}
}
