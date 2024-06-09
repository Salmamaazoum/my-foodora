package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import FidelityCards.LotteryFidelityCard;
import food.HalfMeal;
import food.Meal;
import user.Coordinate;
import user.Customer;
import user.Order;
import user.Restaurant;

class LotteryFidelityCardTest {

    private LotteryFidelityCard card;
    private Order order;
    private HalfMeal meal;
    private Customer customer;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        card = new LotteryFidelityCard();
        meal = new HalfMeal("Pizza") {
            @Override
            public double getPrice() {
                return 20.0;
            }
        };customer = new Customer("John", "john123", "password", "Doe");
        restaurant = new Restaurant("Pizza Place","allopizza","c");
        
        order = new Order("Pizza Place",customer) {
            @Override
            public Calendar getOrderDate() {
                Calendar date = Calendar.getInstance();
                date.set(2024, Calendar.JUNE, 9); 
                return date;
            }

            @Override
            public double getFirstPrice() {
                return 40.0;
            }

            @Override
            public HashMap<Meal, Integer> getOrderMeals() {
                HashMap<Meal, Integer> meals = new HashMap<>();
                meals.put(meal, 1);
                return meals;
            }
        };
    }

    @Test
    @DisplayName("Test date comparison for different days")
    void testAreDifferentDays() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2024, Calendar.JUNE, 9);
        Calendar cal2 = (Calendar) cal1.clone();
        cal2.add(Calendar.DAY_OF_MONTH, 1);

        assertTrue(LotteryFidelityCard.areDifferentDays(cal1, cal2), "Should return true for different days");
    }

    @Test
    @DisplayName("Test no reduction when used on the same day")
    void testNoReductionOnSameDay() {
        Calendar sameDay = order.getOrderDate();
        card.computeOrderReduction(order); 
        card.setLastTimeUsed(sameDay); 
        double reduction = card.computeOrderReduction(order);
        assertEquals(0.0, reduction, "No reduction should be applied when used again on the same day");
    }

    @Test
    @DisplayName("Test toString outputs correctly when never used")
    void testToStringNeverUsed() {
        String expectedOutput = "Lottery Fidelity Card :\nLast time used: Never used\n";
        assertEquals(expectedOutput, card.toString(), "Output should match expected for never used");
    }


}
