package cli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	private static void readTextFile(String fileName) {


		FileReader file = null;
		BufferedReader reader = null;
		int i =1;

		try {
			// open input stream pointing at fileName
			file = new FileReader(fileName);

			// open input buffered reader to read file line by line
			reader = new BufferedReader(file);
			String line = "";

			// reading input file line by line
			while ((line = reader.readLine()) != null) {
				System.out.println(i +"-"+ line);
				handleCommand(line);
				System.out.println("");
				i++;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (file != null) {
				try {
					file.close();
					reader.close();

				} catch (IOException e) {
					System.out.println("File not found: " + fileName);
					// Ignore issues during closing 
				}
			}
		}

	} 
	
    private static void runTestScenario(String testScenarioFile) throws IOException {
        readTextFile(testScenarioFile);
    }

	public static void main(String[] args) {
		appSystem = AppSystem.getInstance();
		appSystem.addDefaultManagers();
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
			case "help":
	            System.out.println("Available commands:\n");
	            System.out.println("runTest <testScenarioFile> - execute the list of CLUI commands contained in the testScenario file passed as argument\n");
	            System.out.println("login <username> <password> - Log in with the specified username and password.");
	            System.out.println("logout - Log out of the current session.");
	            System.out.println("----------------------------------------");
	            System.out.println("----------------------------------------");
	            System.out.println("Manager related tasks :");
	            System.out.println("registerCustomer <firstName> <lastName> <username> <password> - Registers a new customer with the specified first name, last name, username, and password.");
	            System.out.println("registerRestaurant <name> <username> <password> - Registers a new restaurant with the given name, username, and password.");
	            System.out.println("registerCourier <firstName> <lastName> <username> <password> - Registers a new courier with the provided first name, last name, username, and password.");
	            System.out.println("showCourierDeliveries - Displays a list of all couriers sorted by the number of deliveries they have completed.");
	            System.out.println("showRestaurantsTop - Displays a list of restaurants sorted by popularity or total number of orders.");
	            System.out.println("showMenuItem <restaurant-name> - Shows all menu items available in the specified restaurant.");
	            System.out.println("setDeliveryPolicy <delPolicyName> - Sets the delivery policy for the system to the specified policy.");
	            System.out.println("setProfitPolicy <ProfitPolicyName> - Sets the profit policy of the system based on the given policy name.");
	            System.out.println("associateCard <userName> <cardType> - Associates a fidelity card of the specified type with the given user.");
	            System.out.println("showTotalProfit - Displays the total profit made by the system. Can also display profit for a specific date range if dates are provided.");
	            System.out.println("showTotalProfit <startDate> <endDate> - Displays the total profit made by the system within the specified date range.");
	            System.out.println("----------------------------------------");
	            System.out.println("----------------------------------------");
	            System.out.println("Customer related tasks :");
	            System.out.println("createOrder <restaurantName> <orderName> - Create a new order at the specified restaurant and assign it a name for future reference.");
	            System.out.println("addItem2Order <orderName> <itemName> - Add a specified item to an already created order by its name.");
	            System.out.println("endOrder <orderName> - Finalize and submit the specified order, processing payment and scheduling delivery.");
	            System.out.println("historyOfOrders - Display the history of all completed orders for the logged-in customer.");
	            System.out.println("registerFidelityCard <cardType> - Register a fidelity card of the specified type for the logged-in customer.");
	            System.out.println("unregisterFidelityCard - Unregister the current fidelity card for the logged-in customer, removing any associated benefits.");
	            System.out.println("displayFidelityCardInfo - Display details about the currently registered fidelity card for the logged-in customer.");
	            System.out.println("----------------------------------------");
	            System.out.println("----------------------------------------");
	            System.out.println("Restaurant related tasks :");
	            System.out.println("showMenu - Displays the entire menu for the current restaurant.");
	            System.out.println("setSpecialDiscountFactor <specialDiscountFactor> - Sets a special discount factor for applicable meals in the restaurant.");
	            System.out.println("setGenericDiscountFactor <genericDiscountFactor> - Sets a generic discount factor that applies to all meals.");
	            System.out.println("addDishRestaurantMenu <dishName> <dishCategory> <foodType> <glutenFree> <unitPrice> - Adds a dish to the restaurant menu with specified attributes.");
	            System.out.println("createMeal <mealName> <mealType> - Creates a new meal. Optionally specify if the meal is vegetarian and/or gluten-free.");
	            System.out.println("createMeal <mealName> <mealType> <standardOrVegetarian> <GlutenFree> - Extended version to specify dietary preferences.");
	            System.out.println("addDish2Meal <dishName> <mealName> - Adds a dish to an existing meal.");
	            System.out.println("showMeal <mealName> - Displays details about a specific meal.");
	            System.out.println("setSpecialOffer <mealName> - Marks a meal as a special offer, potentially altering its price.");
	            System.out.println("removeFromSpecialOffer <mealName> - Removes the special offer status from a meal.");
	            System.out.println("showSortedHalfMeals - Displays all half-meal options sorted by some criteria such as popularity or price.");
	            System.out.println("showSortedDishes - Displays dishes sorted by popularity or another criterion.");
	            System.out.println("showCourierDeliveries - Displays a list of all couriers sorted by the number of deliveries they have completed.");
	            System.out.println("showRestaurantsTop - Displays a list of restaurants sorted by popularity or total number of orders.");
	            System.out.println("showMenuItem <restaurant-name> - Shows all menu items available in the specified restaurant.");
	            System.out.println("setDeliveryPolicy <delPolicyName> - Sets the delivery policy for the system to the specified policy.");
	            System.out.println("setProfitPolicy <ProfitPolicyName> - Sets the profit policy of the system based on the given policy name.");
	            System.out.println("associateCard <userName> <cardType> - Associates a fidelity card of the specified type with the given user.");
	            System.out.println("showTotalProfit - Displays the total profit made by the system. Can also display profit for a specific date range if dates are provided.");
	            System.out.println("showTotalProfit <startDate> <endDate> - Displays the total profit made by the system within the specified date range.");
	            System.out.println("----------------------------------------");
	            System.out.println("----------------------------------------");
	            System.out.println("Courier related tasks :");
	            System.out.println("onDuty <username> - Sets the specified courier's status to on duty, indicating they are available for deliveries.");
	            System.out.println("offDuty <username> - Sets the specified courier's status to off duty, indicating they are not available for deliveries.");
	            System.out.println("----------------------------------------");
	            System.out.println("----------------------------------------");
				break;
				
			case "runTest":
                if (parts.length == 2) {
                    String testScenarioFile = parts[1];
                    try {
                        runTestScenario(testScenarioFile);
                    } catch (Exception e) {
                        System.out.println("Failed to run test scenario: " + e.getMessage());
                    }
                } else {
                    System.out.println("Usage: runTest <testScenario-file>");
                }
                break;

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
						System.out.println("Login failed. Unknown user");
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
				
				/*
				 * Manager related Tasks
				 */

			case "registerCustomer":
				if (parts.length == 5) {

					String firstName = parts[1];
					String lastName = parts[2];
					String username = parts[3];
					String password = parts[4];

					Customer customer = new Customer(firstName, username, password, lastName);

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
				
			case "showCourierDeliveries":
				if (parts.length == 1) {
					try {
						appSystem.showSortedCouriers();

					} catch (Exception e) {
						System.out.println("Fail to display sorted Couriers !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : showCourierDeliveries <>");
				}
				break;
				
			case "showCustomers":
				if (parts.length == 1) {
					try {
						appSystem.showCustomers();

					} catch (Exception e) {
						System.out.println("Fail to display sorted Customers !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : showCustomers <>");
				}
				break;

				
			case "showRestaurantTop":
				if (parts.length == 1) {
					try {
						appSystem.showSortedRestaurants();

					} catch (Exception e) {
						System.out.println("Fail to display sorted Restaurants !" + e.getMessage());
					}
				} else {
					System.out.println("Usage : showRestaurantTop <>");
				}
				break;
				
			case "showMenuItem":
				if (parts.length == 2) {
					String RestaurantName = parts[1];
					try {
						appSystem.showMenuItem(RestaurantName);

					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Usage :  showMenuItem <restaurant-name>");
				}
				break;
				
			case "setDeliveryPolicy":
				if (parts.length == 2) {
					String delPolicyName = parts[1];
					try {
						appSystem.setDeliveryPolicyName(delPolicyName);
						System.out.println("Delivery policy changed successfully");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Usage : setDeliveryPolicy <delPolicyName>");
				}
				break;
				
			case "setProfitPolicy":
				if (parts.length == 3) {
					String ProfPolicyName = parts[1];
					double target = Double.parseDouble(parts[2]);
					try {
						appSystem.setProfitPolicyName(ProfPolicyName, target);
						System.out.println("Profit policy changed successfully");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Usage : setProfitPolicy <ProfitPolicyName> <target>");
				}
				break;
				
			case "associateCard" :
				if (parts.length == 3) {
					String userName = parts[1];
					String cardType = parts[2];
					try {
						appSystem.associateCard(userName,cardType);
						System.out.println("Fidelity card of type "+cardType+" has been assigned successfully to customer "+userName+"!" );
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Usage : associateCard <userName> <cardType>");
				}
				break;
				
			case "showTotalProfit":
				if (parts.length == 1) {
					try {
						System.out.println("Total Profit: " + appSystem.computeTotalProfit());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				else if (parts.length == 3) {
					String startDate = parts[1];
					String endDate = parts[2];
						try {
							System.out.println("Total Profit: " + appSystem.computeTotalProfit(startDate,endDate));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
				} else {
					System.out.println("Usage : showTotalProfit <> or showTotalProfit <satrtDate> <EndDate>");
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
						System.out.println("Registering successful ! Welcome to the "+cardtype+" fidelity card plan!");
					} catch (Exception e) {
						System.out.println("Fail to register Fidelity Card plan" + e.getMessage());
					}
				} else {
					System.out.println("Usage : registerFidelityCard <cardType>");
				}
				break;
				
			case "setConsensusMail":
				if (parts.length == 2) {
					String mail = parts[1];
					try {
						appSystem.setConsensusMail(mail);
						System.out.println("You will from now on receive mail notifications about special offers");
					} catch (Exception e) {
						System.out.println("Fail to register Fidelity Card plan" + e.getMessage());
					}
				} else {
					System.out.println("Usage : registerFidelityCard <cardType>");
				}
				break;
			
			case "setConsensusPhone":
				if (parts.length == 2) {
					String phone = parts[1];
					try {
						appSystem.setConsensusPhone(phone);
						System.out.println("You will from now on receive phone notifications about special offers");
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
				
			case "displayFidelityCardInfo":
				if (parts.length == 1) {
					
					try {
						appSystem.displayFidelityCardInfo();
					} catch (Exception e) {
						System.out.println("Fail to display Fidelity Card information!" + e.getMessage());
					}
				} else {
					System.out.println("Usage : displayFidelityCardInfo <>");
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
						System.out.println("New Special Offer : "+mealName+" is the meal of the Week!");

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
				
				/*
				 * Courier related Tasks
				 */
				
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
            		System.out.println("Usage : offDuty <username>");
            	}
            	break;
					

			default:
				System.out.println("Unknown command: " + command);
				break;

		}
	}
}
