package Tests;
import food.*;

import user.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;

public class HalfMealTest {

    private HalfMeal halfMeal;

    @BeforeEach
    void setUp() {
        halfMeal = new HalfMeal("Lunch Special", true, false, true);
    }

    @Test
    @DisplayName("Test instantiation of HalfMeal")
    void testHalfMealInstantiation() {
        assertEquals("Lunch Special", halfMeal.getName(), "Meal name should match constructor input");
        assertTrue(halfMeal.isVegetarian(), "Meal should be vegetarian as set");
        assertFalse(halfMeal.isGlutenFree(), "Meal should not be gluten-free as set");
        assertTrue(halfMeal.isMealOfTheWeek(), "Meal should be marked as meal of the week");
        assertNotNull(halfMeal.getMealComposition(), "Meal composition should be initialized and not null");
    }

    @Test
    @DisplayName("Test adding a FoodItem to HalfMeal")
    void testAddItem() {
    	FoodItem item = FoodFactory.createDish("Grilled Vegetables", "starter", "vegetarian", "yes", "5.50");
        assertDoesNotThrow(() -> halfMeal.addItem(item), "Adding a compatible item should not throw exception");
        assertTrue(halfMeal.getMealComposition().contains(item), "Meal composition should include the added item");
        assertEquals(1, halfMeal.getMealComposition().size(), "Meal composition should have exactly one item after addition");
    }

    @Test
    @DisplayName("Test HalfMeal toString method for correct string output")
    void testToString() throws BadMealCompositionCreationException {
    	FoodItem item1 = FoodFactory.createDish("Grilled Vegetables", "maindish", "vegetarian", "yes", "5.50");
    	FoodItem item2 = FoodFactory.createDish("Fresh Juice", "starter", "vegetarian", "yes", "2.50");
        halfMeal.addItem(item1);
        halfMeal.addItem(item2);
        String expectedOutput = "The meal 'Lunch Special' composed of : Grilled Vegetables, Fresh Juice.";
        assertEquals(expectedOutput, halfMeal.toString(), "The string output should correctly describe the meal composition");
    }

    @Test
    @DisplayName("Test adding incompatible FoodItem throws exception")
    void testAddIncompatibleItem() {
    	FoodItem incompatibleItem = FoodFactory.createDish("Chicken Wings", "maindish", "standard", "no", "8.50");
        assertThrows(BadMealCompositionCreationException.class, () -> halfMeal.addItem(incompatibleItem),
            "Adding a non-vegetarian item to a vegetarian meal should throw exception");
    }
}

