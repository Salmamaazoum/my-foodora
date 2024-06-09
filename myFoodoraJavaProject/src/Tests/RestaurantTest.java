package Tests;

import Exceptions.NotFoundException;
import NotificationService.NotificationService;
import NotificationService.Offer;
import appSystem.AppSystem;
import food.BadMealCompositionCreationException;
import food.FoodItem;
import food.Meal;
import user.Coordinate;
import user.Customer;
import user.Order;
import user.Restaurant;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import food.*;
import Exceptions.NotFoundException;
import NotificationService.NotificationService;

public class RestaurantTest {

    private Restaurant restaurant;
    

    @BeforeEach
    void setUp() throws NotFoundException, BadMealCompositionCreationException {
        restaurant = new Restaurant("Le Bistrot", "gourmet", "securepass");
        restaurant.addDishRestaurantMenu("Salad", "starter", "vegetarian", "yes", "10");
		restaurant.addDishRestaurantMenu("Pasta", "maindish", "standard", "no", "25");
		restaurant.addDishRestaurantMenu("Tiramisu", "dessert", "standard", "no", "8");
		
        Meal meal = restaurant.createMeal("Meal1", "half");
        restaurant.addMeal(meal);
        
        restaurant.addDish2Meal("Meal1", "Salad");
        restaurant.addDish2Meal("Meal1", "Pasta");
    }

    @Test
    @DisplayName("Test adding a new meal increases meal count")
    void testAddMeal() throws NotFoundException, BadMealCompositionCreationException {
        int initialSize = restaurant.getMeals().size();
        
        Meal newMeal = restaurant.createMeal("Meal2", "half");
        restaurant.addMeal(newMeal);
        
        restaurant.addDish2Meal("Meal2", "Tiramisu");
        restaurant.addDish2Meal("Meal2", "Pasta");
        
        assertEquals(initialSize + 1, restaurant.getMeals().size(), "Meal should be added to the list");
    }

    @Test
    @DisplayName("Test removing a meal decreases meal count")
    void testRemoveMeal() throws NotFoundException {
        int initialSize = restaurant.getMeals().size();
        restaurant.removeMeal("Meal2");
        assertEquals(initialSize - 1, restaurant.getMeals().size(), "Meal should be removed from the list");
    }

    @Test
    @DisplayName("Test setting special discount triggers notification")
    void testSetSpecialDiscount() {
        restaurant.setSpecialDiscount(0.15); // Change from default value
        assertEquals(0.15, restaurant.getSpecialDiscount(), "Special discount should be updated");
        // Verifying if notification service is called is tricky without a mock, implying need for integration testing or mocking framework
    }

    @Test
    @DisplayName("Test special offer updates meal's special status and triggers notification")
    void testSetSpecialOffer() throws NotFoundException {
    	Meal meal = restaurant.findMealUsingName("Meal1");
    	double price1 = meal.getPrice(); // Initial Price, using only the generic discount factor
    	System.out.println(price1);
        assertFalse(meal.isMealOfTheWeek(), "Initially, meal should not be meal of the week");
        restaurant.setSpecialOffer(meal);
        assertTrue(meal.isMealOfTheWeek(), "Meal should be set as meal of the week");
        System.out.println(meal.getPrice());
        
        assertEquals(price1*(1-restaurant.getSpecialDiscount()), meal.getPrice(), "Meal price should be calculated with special discount");
    }

    @Test
    @DisplayName("Test find meal using name successfully")
    void testFindMealUsingName() {
        assertDoesNotThrow(() -> {
            Meal foundMeal = restaurant.findMealUsingName("Meal1");
            assertNotNull(foundMeal, "Meal should be found by name");
        });
    }

    @Test
    @DisplayName("Test finding non-existing meal throws NotFoundException")
    void testFindMealUsingNameNotFound() {
        assertThrows(NotFoundException.class, () -> {
            restaurant.findMealUsingName("NonExistentMeal");
        }, "Should throw NotFoundException for a non-existent meal");
    }

    // Additional tests could be written for modifying the menu, handling different types of meals, etc.
}


