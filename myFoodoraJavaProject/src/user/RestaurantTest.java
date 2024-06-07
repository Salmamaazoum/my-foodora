package user;

import food.BadMealCompositionCreationException;

public class RestaurantTest {
	public static void main(String[] args) throws NotFoundException, BadMealCompositionCreationException {
		Restaurant r = new Restaurant("h","c","n");
		r.addDishRestaurantMenu("s", "starter", "vegetarian", "yes", "10");
		r.addDishRestaurantMenu("d", "maindish", "vegetarian", "yes", "10");
		System.out.println(r.getMenu().getItems());
		r.addMeal(r.createMeal("myMeal", "half"));
		System.out.println(r.getMeals());
		
		r.addDish2Meal("myMeal", "s");
		r.addDish2Meal("myMeal", "d");
		System.out.println(r.getMeals().get(0));
		r.setSpecialOffer("myMeal");
	}
}
