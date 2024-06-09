package user;

import appSystem.AppSystem;
import food.BadMealCompositionCreationException;
import food.FoodItem;
import food.Meal;

public class RestaurantTest {
	public static void main(String[] args) throws NotFoundException, BadMealCompositionCreationException {
		AppSystem as = AppSystem.getInstance();
		Restaurant r = new Restaurant("h","c","n");
		AppSystem.getRestaurants().add(r);
		r.addDishRestaurantMenu("s", "starter", "vegetarian", "yes", "20");
		r.addDishRestaurantMenu("d", "maindish", "vegetarian", "yes", "12");
	
		r.addMeal(r.createMeal("myMeal", "half"));

		r.addDish2Meal("myMeal", "s");
		r.addDish2Meal("myMeal", "d");
		
		r.setSpecialOffer("myMeal");

		Coordinate address = new Coordinate(1.0, 1.0); // Assuming Coordinate is defined elsewhere
        Customer customer = new Customer("John", "john123", "pass123", "Doe", address, "john@example.com", "1234567890");
        
        Order o = new Order("h",customer);
        o.addItem("s",1);
        
        customer.registerFidelityCard("point");;
        customer.endOrder(o);
        customer.endOrder(o);
        r.showSortedDishes();

        customer.displayFidelityCard();
        
        
	}
}
