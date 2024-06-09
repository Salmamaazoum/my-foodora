package Tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exceptions.NoPermissionException;
import Exceptions.NotFoundException;
import FidelityCards.PointFidelityCard;
import food.*;
import user.*;
import appSystem.*;

public class OrderTest {

    private Order order;
    private Restaurant restaurant;
    private Customer customer;
    private Meal meal;
    private FoodItem foodItem;
    private AppSystem appSystem;

    @BeforeEach
    void setUp() throws NoPermissionException {
    
        appSystem = AppSystem.getInstance();
        restaurant = new Restaurant("Allo Pizza", "delicious", "password");
        customer = new Customer("John Doe", "johndoe", "password", "Doe");

        appSystem.getRestaurants().add(restaurant);
        appSystem.setServiceFee(2.0);
        appSystem.setMarkupPercentage(0.1);
        appSystem.setDeliveryCost(5.0);

        meal = new HalfMeal("Margherita Pizza", true, false, false) { //Vegetarian, not gluten-free, not meal of the week
            @Override
            public double getPrice() {
                return 15.0;
            }
        };

        foodItem = new FoodItem("Tiramisu", FoodItem.foodCategory.STARTER, true, true, 2.0);
        restaurant.addMeal(meal);
        restaurant.addDishRestaurantMenu(foodItem);
        order = new Order("Allo Pizza", customer);
       
    }

    @Test
    @DisplayName("Order Initialization and ID Increment")
    void testOrderInitializationAndIDIncrement() {
        assertNotNull(order.getRestaurant(), "Restaurant should be set correctly in the order");
        assertNotNull(order.getCustomer(), "Customer should be set correctly in the order");
        assertTrue(order.getId() > 0, "Order ID should be incremented and set correctly");
    }

    @Test
    @DisplayName("Adding Items to Order and Calculating First Price")
    void testAddingItemsAndCalculatingFirstPrice() throws NotFoundException {
        order.addItem("Margherita Pizza", 2); 
        order.addItem("Tiramisu", 3);

        double expectedPrice = (2 * 15.0) + (3 * 2.0); // 2 Pizzas and 3 tiramisus
        assertEquals(expectedPrice, order.getFirstPrice(), "First price should be calculated correctly based on added items");
    }

    @Test
    @DisplayName("Final Price with Fidelity Card Discount")
    void testFinalPriceWithFidelityCard() throws NotFoundException {
        customer.setFidelityCard(new PointFidelityCard());
        ((PointFidelityCard)customer.getFidelityCard()).addPoints(100); // Enough points for a discount
        order.addItem("Margherita Pizza", 1);
        double finalPrice = order.getFinalPrice();

        double expectedDiscount = 15.0 * 0.1; // 10% of $15
        double expectedPrice = 15.0 - expectedDiscount;
        assertEquals(expectedPrice, finalPrice, "Final price should include fidelity card discount");
    }

    @Test
    @DisplayName("Submit Order Without Available Courier Throws NotFoundException")
    void testSubmitOrderUpdates() throws NotFoundException {
        order.addItem("Tiramisu", 1);
        double priceWithoutAdditionalFees = order.getFinalPrice();

        // Expect a NotFoundException when no courier is available
        assertThrows(NotFoundException.class, () -> {
            order.submitOrder(priceWithoutAdditionalFees);
        }, "A NotFoundException should be thrown if no couriers are available");

        // Optionally check if no order was added to the system due to the exception
        assertFalse(appSystem.getOrders().contains(order), "Order should not be added to the system orders list due to courier unavailability");
        assertFalse(customer.getOrderHistory().contains(order), "Order should not be added to the customer's history due to courier unavailability");
    }
    
}


   
