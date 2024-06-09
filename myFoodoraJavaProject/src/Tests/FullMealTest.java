package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import food.BadMealCompositionCreationException;
import food.FoodItem;
import food.FullMeal;
import user.FoodFactory;

class FullMealTest {

    private FullMeal fullMeal;

    @BeforeEach
    void setUp() {
        fullMeal = new FullMeal("Lunch Special", true, false, true);
    }

    @Test
    @DisplayName("Test instantiation of FullMeal")
    void testHalfMealInstantiation() {
        assertEquals("Lunch Special", fullMeal.getName(), "Meal name should match constructor input");
        assertTrue(fullMeal.isVegetarian(), "Meal should be vegetarian as set");
        assertFalse(fullMeal.isGlutenFree(), "Meal should not be gluten-free as set");
        assertTrue(fullMeal.isMealOfTheWeek(), "Meal should be marked as meal of the week");
        assertNotNull(fullMeal.getMealComposition(), "Meal composition should be initialized and not null");
    }

    @Test
    @DisplayName("Test adding a FoodItem to FullMeal")
    void testAddItem() {
    	FoodItem item = FoodFactory.createDish("Grilled Vegetables", "starter", "vegetarian", "yes", "5.50");
        assertDoesNotThrow(() -> fullMeal.addItem(item), "Adding a compatible item should not throw exception");
        assertTrue(fullMeal.getMealComposition().contains(item), "Meal composition should include the added item");
        assertEquals(1, fullMeal.getMealComposition().size(), "Meal composition should have exactly one item after addition");
    }

    @Test
    @DisplayName("Test FullMeal toString method for correct string output")
    void testToString() throws BadMealCompositionCreationException {
    	FoodItem item1 = FoodFactory.createDish("Grilled Vegetables", "maindish", "vegetarian", "yes", "5.50");
    	FoodItem item2 = FoodFactory.createDish("Fresh Juice", "starter", "vegetarian", "yes", "2.50");
    	FoodItem item3 = FoodFactory.createDish("Tiramisu", "dessert", "vegetarian", "no", "4.50");
    	fullMeal.addItem(item1);
    	fullMeal.addItem(item2);
    	fullMeal.addItem(item3);
        String expectedOutput = "The meal 'Lunch Special' is composed of : Grilled Vegetables, Fresh Juice, Tiramisu.";
        assertEquals(expectedOutput, fullMeal.toString(), "The string output should correctly describe the meal composition");
    }

    @Test
    @DisplayName("Test adding incompatible FoodItem throws exception")
    void testAddIncompatibleItem() {
    	FoodItem incompatibleItem = FoodFactory.createDish("Chicken Wings", "maindish", "standard", "no", "8.50");
        assertThrows(BadMealCompositionCreationException.class, () -> fullMeal.addItem(incompatibleItem),
            "Adding a non-vegetarian item to a vegetarian meal should throw exception");
    }
    
    @Test
    @DisplayName("Test adding fourth item throws exception")
    void testAddIncompatibleItemExceedSize() throws BadMealCompositionCreationException {
    	FoodItem item1 = FoodFactory.createDish("Grilled Vegetables", "maindish", "vegetarian", "yes", "5.50");
    	FoodItem item2 = FoodFactory.createDish("Fresh Juice", "starter", "vegetarian", "yes", "2.50");
    	FoodItem item3 = FoodFactory.createDish("Tiramisu", "dessert", "vegetarian", "no", "4.50");
    	fullMeal.addItem(item1);
    	fullMeal.addItem(item2);
    	fullMeal.addItem(item3);
        assertThrows(BadMealCompositionCreationException.class, () -> fullMeal.addItem(item3),
            "Adding a non-vegetarian item to a vegetarian meal should throw exception");
    }
}
