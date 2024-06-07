package user;

import food.BadMealCompositionCreationException;
import food.FoodItem;
import food.Meal;

public class NotificationServiceTest {
	public static void main(String[] args) throws BadMealCompositionCreationException {
        // Create a restaurant
        Restaurant restaurant = new Restaurant("Restaurant1", "restaurant", "password", new Coordinate(0, 0), 0.1, 0.2);
        
        // Create customers
        Customer c1 = new Customer("Hala", "Hala123", "password", "Surname1", new Coordinate(10, 10), "customer1@example.com", "123456789");
        Customer c2 = new Customer("Customer2", "customer2", "password", "Surname2", new Coordinate(20, 20), "customer2@example.com", "987654321");
        
        /*
         *         NotificationService.getInstance().addSubscriber(new MailSubscriber(c1.getUsername(),c1.getId(),c1.getEmail()));
        NotificationService.getInstance().addSubscriber(new PhoneSubscriber(c2.getUsername(),c2.getId(),c2.getPhone()) );
        // Create a meal
        Meal meal = new Meal("Special Meal", false, true, true, true);
        meal.addItem(new FoodItem("Salad", FoodItem.foodCategory.STARTER, true, true, 20));
        meal.addItem(new FoodItem("Pasta", FoodItem.foodCategory.MAINDISH, true, true, 20));
        meal.addItem(new FoodItem("Tiramisu", FoodItem.foodCategory.DESSERT, true, true, 20));
        
        restaurant.addMeal(meal);
        meal.setMealOfTheWeek(false);
        restaurant.setSpecialOffer(meal);
         */

	}
}
