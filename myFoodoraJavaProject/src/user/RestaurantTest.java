package user;

import appSystem.AppSystem;
import food.BadMealCompositionCreationException;
import food.Meal;

public class RestaurantTest {
	public static void main(String[] args) throws NotFoundException, BadMealCompositionCreationException {
		AppSystem as = AppSystem.getInstance();
		Restaurant r = new Restaurant("h","c","n");
		AppSystem.getRestaurants().add(r);
		r.addDishRestaurantMenu("s", "starter", "vegetarian", "yes", "10");
		r.addDishRestaurantMenu("d", "maindish", "vegetarian", "yes", "12");
		System.out.println(r.getMenu().getItems());
		r.addMeal(r.createMeal("myMeal", "half"));
		System.out.println(r.getMeals());
		
		r.addDish2Meal("myMeal", "s");
		r.addDish2Meal("myMeal", "d");
		System.out.println("menu "+r.getMenu().getItems());
		r.setSpecialOffer("myMeal");

		Coordinate address = new Coordinate(1.0, 1.0); // Assuming Coordinate is defined elsewhere
        Customer customer = new Customer("John", "john123", "pass123", "Doe", address, "john@example.com", "1234567890");
        
        Order o = new Order("h",customer);
        o.addItem("myMeal",1);
        
        customer.registerFidelityCard("lottery");;
        customer.endOrder(o);
        customer.endOrder(o);
        customer.displayFidelityCard();
        
        
	}
}
