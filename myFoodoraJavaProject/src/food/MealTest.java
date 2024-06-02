package food;

import org.junit.Test;

public class MealTest {

    @Test
    public final void whenMoreThanTwoDishesAddedInHalfMeal() {
        try {
            // Create a half meal
            Meal meal = new Meal("Special Meal", false, true, true, true);

            // Add more than two dishes to the half meal
            meal.addItem(new FoodItem("Salad", FoodItem.foodCategory.STARTER, true, true, 20));
            meal.addItem(new FoodItem("Pasta", FoodItem.foodCategory.MAINDISH, true, true, 20));
            meal.addItem(new FoodItem("Tiramisu", FoodItem.foodCategory.DESSERT, true, true, 20));
            System.out.println(meal);
        } catch (BadMealCompositionCreationException e) {
            // Handle the exception, e.g., fail the test or log a message
            // Fail the test
            org.junit.Assert.fail("Expected BadMealCompositionCreationException, but it was not thrown");
        }
    }
}