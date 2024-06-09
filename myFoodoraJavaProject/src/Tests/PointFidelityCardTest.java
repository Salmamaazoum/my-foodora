package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import FidelityCards.PointFidelityCard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import user.Coordinate;
import user.Customer;
import user.Order;
import user.Restaurant;

public class PointFidelityCardTest {

    private PointFidelityCard card;
    private Order order;
    private Customer customer;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        card = new PointFidelityCard();
        customer = new Customer("John", "john123", "password", "Doe");
        restaurant = new Restaurant("Pizza Place","allopizza","c");
        
        order = new Order("Pizza Place",customer) {
            @Override
            public double getFirstPrice() {
                return 100.0; 
            }
        };
    }

    @Test
    @DisplayName("Test point accumulation based on price")
    void testPointAccumulation() {
        double price = 250.0; // Should result in 25 points if 10 is the amount for 1 point
        int expectedPoints = 25;
        card.updatePoints(price);
        assertEquals(expectedPoints, card.getPoints(), "Points should correctly accumulate based on the order price");
    }

    @Test
    @DisplayName("Test no discount applied when not enough points")
    void testNoDiscountApplied() {
        card.setPoints(90); // Less than target points of 100
        double price = card.computeOrderPrice(order);
        assertEquals(100.0, price, "No discount should be applied if points are less than target");
    }

    @Test
    @DisplayName("Test discount applied when enough points")
    void testDiscountApplied() {
        card.setPoints(100);
        double expectedDiscount = 10.0; 
        double expectedPrice = 90.0; 
        double price = card.computeOrderPrice(order);
        assertEquals(expectedPrice, price, "Discount should be applied reducing the price by 10%");
        assertEquals(0, card.getPoints(), "Points should be reset after applying discount");
    }

    @Test
    @DisplayName("Test points subtraction after applying discount")
    void testPointsSubtraction() {
        card.setPoints(120); 
        card.computeOrderPrice(order);
        assertEquals(20, card.getPoints(), "Points should be subtracted by the target amount after discount is applied");
    }

    @Test
    @DisplayName("Test toString output")
    void testToString() {
        card.setPoints(50);
        String expectedOutput = "Point Fidelity Card: \nNumber of points gained so far: 50";
        assertEquals(expectedOutput, card.toString(), "The toString method should output the correct string format with points");
    }
}

