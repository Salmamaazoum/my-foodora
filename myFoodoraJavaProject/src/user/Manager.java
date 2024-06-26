package user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Exceptions.NonReachableTargetProfitException;
import Exceptions.unknownDeliveryPolicyException;
import Exceptions.unknownProfitPolicyException;
import appSystem.AppSystem;
import delivery.DeliveryPolicy;
import delivery.DeliveryPolicyFactory;
import food.MenuComponent;
import targetProfit.TargetProfitPolicy;
import targetProfit.TargetProfitPolicyFactory;
import user.Courier;
import user.CourierSorter;
import food.FoodItem;
import food.Menu;
import FidelityCards.*;

public class Manager extends User {
	
	private AppSystem appSystem;
	private String surname;
	
	private DeliveryPolicyFactory deliveryPolicyFactory;
	private TargetProfitPolicyFactory targetProfitPolicyFactory;

	
    public Manager( String name, String username, String password, String surname) {
        super( name, username, password);
        this.surname = surname;
        this.deliveryPolicyFactory = new DeliveryPolicyFactory();
        this.targetProfitPolicyFactory = new TargetProfitPolicyFactory();
        this.appSystem = AppSystem.getInstance();
    }
	
	public void showSortedCouriers() {
		
		ArrayList<Courier> couriers = new ArrayList<Courier>(appSystem.getCouriers());
	    CourierSorter sorter = new CourierSorter();
	    ArrayList<Courier> sortedCouriers = sorter.sort(couriers);
		System.out.println("Couriers:");
		for (Courier courier : sortedCouriers) {
			System.out.println(courier);
	}

	}
	
	public void showSortedRestaurants() {
		
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>(AppSystem.getRestaurants());
	    RestaurantSorter sorter = new RestaurantSorter();
	    ArrayList<Restaurant> sortedRestaurants = sorter.sort(restaurants);
		System.out.println("Couriers:");
		for (Restaurant restaurant : sortedRestaurants) {
			System.out.println(restaurant);
	}

	}
	
	public void showCustomers() {
		
		List<Customer> customers = appSystem.getCustomers();

		for (Customer customer : customers) {
			System.out.println(customer);
	}

	}
	
    public void showMenuItems (String restaurantName) {
    	Menu menu = null;
    	
    	for (Restaurant restaurant : AppSystem.getRestaurants()) {
    		if (restaurant.getName().equalsIgnoreCase(restaurantName))
    			menu = restaurant.getMenu();
    			break;
    	}
    	
    	for (FoodItem item: menu.getItems()) {
    		System.out.println(item);
    	}
    	
    }
    
	public void setTargetProfitPolicy (String targetProfitPolicyName, double target) throws unknownProfitPolicyException, NonReachableTargetProfitException{
		TargetProfitPolicy targetProfitPolicy = targetProfitPolicyFactory.chooseTargetProfitPolicy(targetProfitPolicyName);
		targetProfitPolicy.meetTargetProfit(appSystem, target);
		AppSystem.setTargetProfitPolicy(targetProfitPolicy);
		
	}
	
	public void setDeliveryPolicy (String deliveryPolicyName) throws unknownDeliveryPolicyException{
		DeliveryPolicy deliveryPolicy = deliveryPolicyFactory.chooseDeliveryPolicy(deliveryPolicyName);
		appSystem.setDeliveryPolicy(deliveryPolicy);
	}
	
	public void associateCard(Customer c, FidelityCard f) {
		c.setFidelityCard(f);
	}

	public double computeTotalProfit() {
		double totalProfit = 0 ;
		for(Order order : AppSystem.getOrders()){
			totalProfit = totalProfit +order.getProfit();
		}
		return totalProfit;
	}
	
	public double computeTotalProfit(Calendar cal1, Calendar cal2) {
		double totalProfit = 0 ;
		for(Order order : AppSystem.getOrders()){
			Calendar dateOfOrder = order.getOrderDate();
			if ((dateOfOrder.compareTo(cal1)>=0)&&(dateOfOrder.compareTo(cal2)<=0)){
				totalProfit += order.getProfit() ;
			}
		}
		return totalProfit;
	}

}
