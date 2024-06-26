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
        restaurant.removeMeal("Meal1");
        assertEquals(initialSize - 1, restaurant.getMeals().size(), "Meal should be removed from the list");
    }

    @Test
    @DisplayName("Test setting special discount triggers notification")
    void testSetSpecialDiscount() {
        restaurant.setSpecialDiscount(0.15); // Change from default value
        assertEquals(0.15, restaurant.getSpecialDiscount(), "Special discount should be updated");
        
    }

    @Test
    @DisplayName("Test special offer updates meal's special status and triggers notification")
    void testSetSpecialOffer() throws NotFoundException {
    	Meal meal = restaurant.findMealUsingName("Meal1");
    	double priceWithoutDiscount = meal.getPrice() / (1-restaurant.getGenericDiscount()); // Initial Price, using only the generic discount factor
        assertFalse(meal.isMealOfTheWeek(), "Initially, meal should not be meal of the week");
        restaurant.setSpecialOffer(meal);
        assertTrue(meal.isMealOfTheWeek(), "Meal should be set as meal of the week");
        assertEquals(priceWithoutDiscount*(1-restaurant.getSpecialDiscount()), meal.getPrice());
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

}


