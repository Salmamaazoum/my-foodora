package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import Exceptions.NotFoundException;

import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import user.*;
import food.*;
import FidelityCards.*;
import NotificationService.*;
import appSystem.AppSystem;

public class CustomerTest {

    private Customer customer;
    private Restaurant restaurant;
    private Meal meal;
    private NotificationService notificationService;
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() throws NotFoundException, BadMealCompositionCreationException {
        customer = new Customer("John", "john123", "password", "Doe");
        AppSystem appSystem = AppSystem.getInstance();
        
        /*
         * Adding dishes to the menu of the restaurant
         */
        restaurant = new Restaurant("Pizza Place","allopizza","c");
    	restaurant.addDishRestaurantMenu("Salad", "starter", "vegetarian", "yes", "10");
		restaurant.addDishRestaurantMenu("Pasta", "maindish", "standard", "no", "25");
		
        meal = restaurant.createMeal("Pizza", "half");
        restaurant.addMeal(meal);
        
        restaurant.addDish2Meal("Pizza", "Salad");
        restaurant.addDish2Meal("Pizza", "Pasta");
        
        appSystem.getRestaurants().add(restaurant);
        appSystem.getCustomers().add(customer);
        
        notificationService = NotificationService.getInstance();
        notificationService.clearObservers();
        
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Check Observer Status Changes with Consensus")
    void testConsensusChangesObserverStatus() {
        // Initially, the customer should not be registered as an observer
        assertFalse(notificationService.getObservers().contains(customer), "Customer should not be initially registered as observer");

        // Enable consensus
        customer.setConsensus(true);
        assertTrue(notificationService.getObservers().contains(customer), "Customer should be registered as observer after consensus is true");

        // Disable consensus
        customer.setConsensus(false);
        assertFalse(notificationService.getObservers().contains(customer), "Customer should be unregistered as observer after setting consensus false");
    }

    @Test
    @DisplayName("Verify Notification Reception Outputs Correctly")
    void testNotificationReception() {
        
        Offer offer = Offer.mealOfTheWeek;

        // Enable consensus and set contact preference
        customer.setConsensus(true);
        customer.setContact_offers(Customer.Contact_offers.email,"john@example.com");

        // Notify observers
        notificationService.setOffer(meal,restaurant, offer);

        // Assert output content
        String expectedOutput = "New email received at john@example.com: Restaurant Pizza Place has a new special offer:  New meal of the week: Pizza";
        assertTrue(outContent.toString().contains(expectedOutput), "Output should contain notification details");
    }

    @Test
    @DisplayName("Verify Payment Processing Outputs Correct Information")
    void testOrderPaymentOutput() throws NotFoundException {

        Order order = new Order("Pizza Place",customer);
        order.addItem("Salad",1);
        
        AppSystem appSystem = AppSystem.getInstance();
        Courier courier = new Courier("Bouchaib","Jilali","bouchab","123");
        appSystem.getCouriers().add(courier);

        customer.endOrder(order);
      
        String expectedPaymentMessage = "You have paid €10.00 for your order.";
        assertTrue(outContent.toString().contains(expectedPaymentMessage), "Output should confirm payment was processed");
    }

    @Test
    @DisplayName("Verify Fidelity Card Type Changes with Registration")
    void testFidelityCardRegistration() {
        customer.registerFidelityCard("point");

        assertEquals(customer.getFidelityCard().getType().toString(), "POINT");
    }
}
