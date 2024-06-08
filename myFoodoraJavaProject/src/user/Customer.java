package user;
import java.text.SimpleDateFormat;
import java.util.*;

public class Customer extends User  {
	
	private String surname;
	
	private Coordinate address;
	
    private String email;
    
    private String phone;
    
    private ArrayList<Order> orderHistory = new ArrayList<Order>();
    
    private FidelityCard fidelityCard;
    
    private boolean consensus;

	public Customer( String name, String username, String password, String surname, Coordinate address, String email, String phone) {
        super( name, username, password);
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.fidelityCard= new BasicFidelityCard();
    }
	
	public Customer( String name, String username, String password, String surname, Coordinate address) {
        super( name, username, password);
        this.surname = surname;
        this.address = address;
        this.fidelityCard= new BasicFidelityCard();
    }
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
    public Coordinate getAddress() {
		return address;
	}

	public void setAddress(Coordinate address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	

	public boolean consensus() {
		return consensus;
	}

	public void setConsensus(boolean consensus) {
		this.consensus = consensus;
	}

	public ArrayList<Order> getOrderHistory() {
		return orderHistory;
	}
	
	


	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}

	public void setFidelityCard(FidelityCard fidelityCard) {
		this.fidelityCard = fidelityCard;
	}

	
	/*
	 * Pay for an order by displaying a confirmation message
	 */
	public void payOrder(double price) {
	    // Use String.format to cleanly format the message and handle floating point representation
	    System.out.println(String.format("You have paid €%.2f for your order.", price));
	    System.out.println("");

	    // Check if the fidelity card is of type PointFidelityCard
	    if (this.fidelityCard instanceof PointFidelityCard) {
	        PointFidelityCard pointCard = (PointFidelityCard) this.fidelityCard;
	        int numberAddedPts = pointCard.computeNumberAddedPoints(price);
	        pointCard.updatePoints(price);

	        // Format the points message to be clearer and grammatically correct
	        if (numberAddedPts>=1) {
	        	String pointsMessage = String.format("You earned %d %s!", numberAddedPts, numberAddedPts == 1 ? "fidelity point" : "fidelity points");
	        	System.out.println(pointsMessage);
	        }
	        
	    }
	}

	
	public void endOrder(Order order) {
	    System.out.println("Finalizing your order...");
	    double price = order.getFinalPrice(); 
	    order.submitOrder(price); 

	    if (price > 0) {
	        System.out.println("Payment in process...");
	       
	        this.payOrder(price); 
	    } else if (price == 0) {
	        System.out.println("No payment needed. Your order seems to be free!");
	    } else {
	        System.out.println("Error: Negative price encountered. Please contact support.");
	    }
	}
	
	/*
	 * Display the history of orders
	 */
	
	public void displayOrders() {
	   
	    System.out.println("Completed Orders (" + this.orderHistory.size()+")");

	    if (this.orderHistory.isEmpty()) {
	        System.out.println("No completed orders yet.");
	        return;
	    }

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    int orderNumber = 1; 

	    for (Order order : this.orderHistory) {
	        
	        System.out.println("\nOrder #" + orderNumber++ + ":");
	        
	        Calendar orderDate = order.getOrderDate();
	        String formattedDate = dateFormat.format(orderDate.getTime());
	        System.out.println("Delivered • " + formattedDate + " • Total: €" + String.format("%.2f", order.getFinalPrice()));
	        
	        System.out.println(order.toString());
	    }
	}
	
	/*
	 * Register fidelity card
	 */
	
	public void registerFidelityCard(String cardType) {
		FidelityCard card = FidelityCardFactory.createFidelityCard(cardType);
		if (card!=null)
			this.setFidelityCard(card);
	}
	
	/*
	 * Unregister fidelity card plan
	 */
	
	public void unregisterFidelityCard() {
		this.setFidelityCard(new BasicFidelityCard());
	}
	
	public void displayFidelityCard() {
		System.out.println(this.fidelityCard);
	}
	
	@Override
	public String toString() {
		return "Customer: Username=" + getUsername()+ ", Name=" + getName() + ", surname=" + surname + ", address=" + address + ", email=" + email + ", phone=" + phone;
	}	
	
}
