package Tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import FidelityCards.PointFidelityCard;
import user.Coordinate;
import user.Customer;
import user.Order;

public class CustomerTest {
    
    private Customer customer;
    private Order mockOrder;
    private PointFidelityCard mockPointFidelityCard;

    @BeforeEach
    public void setUp() {
        Coordinate address = new Coordinate(1.0, 1.0); // Assuming Coordinate is defined elsewhere
        customer = new Customer("John", "john123", "pass123", "Doe", address, "john@example.com", "1234567890");
        mockOrder = mock(Order.class);
        mockPointFidelityCard = mock(PointFidelityCard.class);

        when(mockOrder.getFinalPrice()).thenReturn(100.0);
        customer.setFidelityCard(mockPointFidelityCard);
    }

    @Test
    public void testCustomerCreation() {
        assertEquals("John", customer.getName());
        assertEquals("Doe", customer.getSurname());
        assertNotNull(customer.getAddress());
    }

    @Test
    public void testSetAndGetEmail() {
        customer.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", customer.getEmail());
    }

    @Test
    public void testPayOrderWithPointFidelityCard() {
        when(mockPointFidelityCard.computeNumberAddedPoints(100.0)).thenReturn(10);

        customer.payOrder(mockOrder);
        
        verify(mockPointFidelityCard).updatePoints(100.0);
        verify(mockOrder, never()).submitOrder();
    }

    @Test
    public void testEndOrder() {
        doNothing().when(mockOrder).submitOrder();
        customer.endOrder(mockOrder);
        
        verify(mockOrder).submitFromOrder();
        verify(mockPointFidelityCard).updatePoints(100.0);
    }

    @Test
    public void testDisplayOrders() {
        customer.getOrderHistory().add(mockOrder);
        customer.displayOrders();
        assertEquals(1, customer.getOrderHistory().size());
        
    }

    @Test
    public void testToString() {
        String expected = "Customer: Username=john123, Name=John, surname=Doe, address=Coordinate[x=1.0, y=1.0], email=john@example.com, phone=1234567890";
        assertEquals(expected, customer.toString());
    }
}
