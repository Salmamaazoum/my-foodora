package user;

import food.*;

public class FoodFactory {
	
	public FoodFactory() {
		
	}
	
	public FoodItem createDish(String dishName, String dishCategory, String foodType,String glutenFree, String unitPrice ) {
		FoodItem item = new FoodItem();
    	item.setName(dishName);
    	for (FoodItem.foodCategory category : FoodItem.foodCategory.values()) {
    		if (category.name().equalsIgnoreCase(dishCategory)) {
    			item.setCategory(category);
    			break;
    		}
    	}
    	item.setVegetarian(foodType.equalsIgnoreCase("vegetarian"));
    	item.setGlutenFree(glutenFree.equalsIgnoreCase("yes"));
    	item.setPrice(Double.parseDouble(unitPrice));
    	
    	return item;
	}
	
	public Meal createMeal(String mealName, String mealType) {
		Meal meal = null;
		switch (mealType) {
		case "full":
			meal = new FullMeal(mealName);
			break;
		case "half":
			meal = new HalfMeal(mealName);
			break;
		}
		
		return meal;
	}
	
	public Meal createMeal(String mealName, String mealType, String StandardOrVeg, String isGlutenFree) {
		Meal meal = null;
		switch (mealType) {
		case "full":
			meal = new FullMeal(mealName);
			meal.setVegetarian(StandardOrVeg.equalsIgnoreCase("vegetarian"));
			meal.setGlutenFree(isGlutenFree.equalsIgnoreCase("yes"));
			break;
		case "half":
			meal = new HalfMeal(mealName);
			meal.setVegetarian(StandardOrVeg.equalsIgnoreCase("vegetarian"));
			meal.setGlutenFree(isGlutenFree.equalsIgnoreCase("yes"));
			break;
		}
		return meal;
	}
}
