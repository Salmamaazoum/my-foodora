package appSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import food.*;
import Exceptions.*;
import delivery.DeliveryPolicy;
import delivery.FastestDeliveryPolicy;
import targetProfit.ServiceFeeTargetPolicy;
import targetProfit.TargetProfitPolicy;
import FidelityCards.*;
import user.*;
public class AppSystem {
    /**
     * Singleton instance of the Application
     */
    private static AppSystem instance = null;
    
    private static List<Manager> managers = new ArrayList<>();
    
    private static List<Customer> customers = new ArrayList<>();
    
    private static List<Courier> couriers = new ArrayList<>();
    
    private static List<Restaurant> restaurants = new ArrayList<>();
    
    private static List<Order> orders = new ArrayList<>();
    
    private static Optional<User> currentUser ;
    
    private static Optional<UserType> currentUserType;
    
	private static DeliveryPolicy deliveryPolicy = new FastestDeliveryPolicy();
	private static TargetProfitPolicy targetProfitPolicy = new ServiceFeeTargetPolicy();

	private double serviceFee = 2;
	private double markupPercentage = 0.3;
	private double deliveryCost = 5;
    


	private AppSystem() {
        currentUser = Optional.empty();
        currentUserType = Optional.empty();

    }
	
	

    public void addDefaultManagers() {
    	// Add default managers
        Manager salma = new Manager("Salma", "Salma", "1234", "Salma");
        Manager hala = new Manager("Hala", "Hala", "1234", "Salma");
    	managers.add(salma);
    	managers.add(hala);
    }
    public static AppSystem getInstance() {
        if (instance == null) {
            instance = new AppSystem();
        }
        return instance;
    }
    
    public void addOrder(Order order) {
    	orders.add(order);
    }
    
	
    public static TargetProfitPolicy getTargetProfitPolicy() {
		return targetProfitPolicy;
	}

	public static void setTargetProfitPolicy(TargetProfitPolicy targetProfitPolicy) {
		AppSystem.targetProfitPolicy = targetProfitPolicy;
	}

	public static DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public double getMarkupPercentage() {
		return markupPercentage;
	}

	public void setMarkupPercentage(double markupPercentage) {
		this.markupPercentage = markupPercentage;
	}

	public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public static List<Order> getOrders() {
		return orders;
	}

    public List<Manager> getManagers() {
        return managers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public Optional<User> getCurrentUser() {
        return currentUser;
    }

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }
    
    

    public static Optional<UserType> getCurrentUserType() {
		return currentUserType;
	}
    
    public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) throws unknownDeliveryPolicyException {
    	this.deliveryPolicy = deliveryPolicy;
    }

    public boolean login(String username, String password) {
        if (tryLogin(managers, username, password, UserType.MANAGER) ||
                tryLogin(customers, username, password, UserType.CUSTOMER) ||
                tryLogin(couriers, username, password, UserType.COURIER)||
                tryLogin(restaurants, username, password, UserType.RESTAURANT) ) {
            return true; // Login successful
        }
        return false; // Login failed
    }

    public void logout() {
        currentUserType = Optional.empty();
        currentUserType = Optional.empty();
    }
    
    /*
     *  Manager Tasks
     */

    public void registerCustomer(Customer customer) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		customers.add(customer);
    	} else {
    	throw new NoPermissionException("Only managers can perform this action.");
    	}
    }

    public void registerRestaurant(Restaurant restaurant) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		restaurants.add(restaurant);
    	} else {
    		throw new NoPermissionException("Only managers can perform this action.");
    	}
    }

    public void registerCourier(Courier courier) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		couriers.add(courier);
    	} else {
    	throw new NoPermissionException("Only managers can perform this action.");
    	}
    }
    
    public void setDeliveryPolicyName(String deliveryPolicyName) throws NoPermissionException, unknownDeliveryPolicyException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		Manager manager = (Manager) currentUser.get();
    		manager.setDeliveryPolicy(deliveryPolicyName);
    	} else {
    		throw new NoPermissionException("Only managers can perform this action.");
    	}
    }
    
    public void setProfitPolicyName(String profitPolicyName, double target) throws NoPermissionException, unknownProfitPolicyException, NonReachableTargetProfitException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		Manager manager = (Manager) currentUser.get();
    		manager.setTargetProfitPolicy(profitPolicyName, target);
    	} else {
    		throw new NoPermissionException("Only managers can perform this action.");
    	}
    }
    
    public void showSortedCouriers() throws NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		((Manager)currentUser.get()).showSortedCouriers();
    	}
    	else
    		throw new NoPermissionException("Only Managers can perform this action");
    }
    
    public void showSortedRestaurants() throws NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		((Manager)currentUser.get()).showSortedRestaurants();
    	}
    	else
    		throw new NoPermissionException("Only Managers can perform this action");
    }
    
    public void showCustomers() throws NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		((Manager)currentUser.get()).showCustomers();
    	}
    	else
    		throw new NoPermissionException("Only Managers can perform this action");
    }
    
    public void showMenuItem(String restaurantName) throws NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		((Manager)currentUser.get()).showMenuItems(restaurantName);
    	}
    	else
    		throw new NoPermissionException("Only Managers can perform this action");
    }
    
    public Customer findCustomerUsingUserName(String userName) throws NotFoundException {
    	Customer customer = null;
    	for (Customer c : this.customers) {
    		if (c.getUsername().equals(userName)) {
    			customer = c;
    		}
    	}
    	if (customer == null)
    		throw new NotFoundException("The customer "+userName+" does not exist in the database.");
    	return customer;
    }
    
    public void associateCard(String userName, String cardType) throws NotFoundException, NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		FidelityCard card = FidelityCardFactory.createFidelityCard(cardType);
    		Customer customer = this.findCustomerUsingUserName(userName);
    		((Manager)currentUser.get()).associateCard(customer, card);
    	}
    	else
    		throw new NoPermissionException("Only Managers can perform this action");
    }
    
    public double computeTotalProfit() throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
    		return ((Manager)currentUser.get()).computeTotalProfit();
    	}
    	else
    		throw new NoPermissionException("Only Managers can perform this action");
    }

    public double computeTotalProfit(String startDate, String endDate) throws NoPermissionException, ParseException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.MANAGER) {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            cal1.setTime(sdf.parse(startDate));
            cal1.setTime(sdf.parse(endDate));
            return ((Manager)currentUser.get()).computeTotalProfit(cal1, cal2);
    	}
    	else
    		throw new NoPermissionException("Only Managers can perform this action");
    }
    
	public double getTotalIncomeLastMonth(){
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		
		date1.add(Calendar.MONTH, -1);
		date2.add(Calendar.MONTH, -1);

		int lastDayOfLastMonth = date2.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		date1.set(Calendar.DAY_OF_MONTH, 1);
		date2.set(Calendar.DAY_OF_MONTH, lastDayOfLastMonth);
		double totalIncome = totalIncome (date1, date2);
		return totalIncome ;
	}

    
    //============================================
    /*
     *  Customer Tasks
     */
    
    public Order createOrder(String restaurantName) throws NoPermissionException, NotFoundException{
    	Order order = null;
    	
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
    		if (!AppSystem.getRestaurants().isEmpty()) {
				for (Restaurant restaurant : AppSystem.getRestaurants()) {
					if (restaurant.getName().equalsIgnoreCase(restaurantName)) {
						order = new Order(restaurantName,((Customer)currentUser.get()));
					}
				}
    		}
    		if (order == null) {
    			throw new NotFoundException("The restaurant "+restaurantName+" does not exist!");
    		}
    	}
    	else
    		throw new NoPermissionException("Only Customers can perform this action");
    	return order;
    }
    
    public void addItem2Order(Map<String, Order> customerOrders, String itemName, String orderName) throws NoPermissionException, NotFoundException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
    		boolean orderFound = false;
	    	for (Entry<String,Order> entry : customerOrders.entrySet()) {
				if (entry.getKey().equals(orderName)) {
					orderFound=true;
					entry.getValue().addItem(itemName, 1);
					break;
				}
	    	}
	    	if (!orderFound) {
	    		throw new NotFoundException("No order with such name exists");
	    	}
    	}
    	else
    		throw new NoPermissionException("Only Customers can perform this action");
    	
    }
    
    //à faire, pay order how? change parameters manager profit etc
    
    public void endOrder(String orderName, Map<String, Order> customerOrders) throws NoPermissionException, NotFoundException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
    		
    		boolean orderFound = false;
    		/*
    		 * Loop through the orders which were created by the customers during this exact login
    		 */
	    	for (Entry<String,Order> entry : customerOrders.entrySet()) {
				if (entry.getKey().equals(orderName)) {
					
					Order order = entry.getValue();
					orderFound = true;

					((Customer)currentUser.get()).endOrder(order);
	
					break;
				}
	    	}
	    	
	  
	    	if (!orderFound) {
	    		throw new NotFoundException("No order with such name exists");
	    	}
    	}
    	else
    		throw new NoPermissionException("Only Customers can perform this action");
    }
    
    /*
     * display Customer's previous orders
     */
    
    public void displayHistoryOrders() throws NoPermissionException {
		if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
			((Customer)currentUser.get()).displayOrders();
		}
		else
		    throw new NoPermissionException("Only Customers can perform this action");
    }
    
    /*
     * register/unregister Fidelity Card plan
     */
    
    public void registerFidelityCard(String cardType) throws NoPermissionException {
		if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
			((Customer)currentUser.get()).registerFidelityCard(cardType);
		}
		else
		    throw new NoPermissionException("Only Customers can perform this action");
    }
    
    public void unregisterFidelityCard() throws NoPermissionException {
		if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
			((Customer)currentUser.get()).unregisterFidelityCard();
		}
		else
		    throw new NoPermissionException("Only Customers can perform this action");
    }
    
    public void displayFidelityCardInfo() throws NoPermissionException {
		if (currentUserType.isPresent() && currentUserType.get() == UserType.CUSTOMER) {
			((Customer)currentUser.get()).displayFidelityCard();;
		}
		else
		    throw new NoPermissionException("Only Customers can perform this action");
    }
    
    /*
     * Restaurant related Tasks
     
     */
    
    public void showMenu() throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
	    	((Restaurant)currentUser.get()).displayRestaurant();
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    	
    }
    
    public void setSpecialDiscountFactor(String discount) throws NoPermissionException, IllegalArgumentException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
            double discountFactor;
            try {
                discountFactor = Double.parseDouble(discount);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid discount format. Please enter a double value.");
            }
            

            if (discountFactor <= 0 || discountFactor >= 1) {
                throw new IllegalArgumentException("The discount factor must be greater than 0 and less than 1.");
            }	
	    	((Restaurant)currentUser.get()).setSpecialDiscount(discountFactor);
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    	
    }
    
    public void setGenericDiscountFactor(String discount) throws NoPermissionException, IllegalArgumentException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		
            double discountFactor;
            try {
                discountFactor = Double.parseDouble(discount);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid discount format. Please enter a double value.");
            }
            

            if (discountFactor <= 0 || discountFactor >= 1) {
                throw new IllegalArgumentException("The discount factor must be greater than 0 and less than 1.");
            }	
	    	((Restaurant)currentUser.get()).setGenericDiscount(discountFactor);
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    	
    }
   
    public void addDishRestaurantMenu(String DishName, String DishCategory,String foodType,String glutenFree, String unitPrice) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
	    	((Restaurant)currentUser.get()).addDishRestaurantMenu(DishName, DishCategory, foodType, glutenFree, unitPrice);
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    	
    	
    }
    


    
    public void createMeal (String mealName, String mealType) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		 ((Restaurant)currentUser.get()).addMeal(mealName, mealType);
    	}
    	else {
    		throw new NoPermissionException("Only Restaurants can perform this action");
    	}
    }
    
    public void createMeal (String mealName, String mealType,String StandardOrVeg, String isGlutenFree) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		((Restaurant)currentUser.get()).addMeal(mealName, mealType, StandardOrVeg, isGlutenFree);
    	}
    	else {
    		throw new NoPermissionException("Only Restaurants can perform this action");
    	}
    }
    
    
    public void addDish2Meal(String dishName, String mealName) throws NotFoundException, NoPermissionException, BadMealCompositionCreationException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
				((Restaurant)currentUser.get()).addDish2Meal(mealName, dishName);	
    		}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    }
    
    public void showMeal(String mealName) throws NotFoundException, NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		((Restaurant)currentUser.get()).showMeal(mealName);
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    }
    
    

    	
    public void setSpecialOffer(String mealName) throws NotFoundException, NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		((Restaurant)currentUser.get()).setSpecialOffer(mealName);
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    }
    
    
    public void removeFromSpecialOffer(String mealName) throws NotFoundException, NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		((Restaurant)currentUser.get()).setSpecialOffer(mealName);
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    }
    
    public void showSortedHalfMeals() throws NotFoundException, NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		((Restaurant)currentUser.get()).showSortedHalfMeals();
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    }
    
    public void showSortedDishes() throws NotFoundException, NoPermissionException{
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.RESTAURANT) {
    		((Restaurant)currentUser.get()).showSortedDishes();
    	}
    	else
    		throw new NoPermissionException("Only Restaurants can perform this action");
    }
    
    /*
     * Courier related tasks
     */
    public void setOnDuty(String username, boolean isOnDuty) throws NoPermissionException {
    	if (currentUserType.isPresent() && currentUserType.get() == UserType.COURIER && currentUser.get().getUsername().equals(username)) {
    		Courier courier = (Courier) currentUser.get();
    		courier.setOnDuty(isOnDuty);
    	}
    	else {
    		throw new NoPermissionException("Don't have the permission to perform the requested action");
    	}   	
    }
    
    private <T extends User> boolean tryLogin(List<T> users, String username, String password, UserType typeofUser) {
        for (T user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = Optional.of(user);
                currentUserType = Optional.of(typeofUser);
                return true;
            }
        }
        return false;
    }
    
    private double totalIncome(Calendar cal1, Calendar cal2) {
		double totalProfit = 0 ;
		for(Order order : AppSystem.getOrders()){
			Calendar dateOfOrder = order.getOrderDate();
			if ((dateOfOrder.compareTo(cal1)>=0)&&(dateOfOrder.compareTo(cal2)<=0)){
				totalProfit += order.getTotalPrice() ;
			}
		}
		return totalProfit;
    }
    


}
