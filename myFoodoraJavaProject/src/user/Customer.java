package user;
import java.text.SimpleDateFormat;
import java.util.*;

import Exceptions.NotFoundException;
import FidelityCards.BasicFidelityCard;
import FidelityCards.FidelityCard;
import FidelityCards.FidelityCardFactory;
import FidelityCards.PointFidelityCard;
import NotificationService.NotificationService;
import NotificationService.Observer;
import NotificationService.Offer;
import food.Meal;

public class Customer extends User implements Observer {
	
	private String surname;
	
	private Coordinate address;
	
    private String email;
    
    private String phone;
    
    private ArrayList<Order> orderHistory = new ArrayList<Order>();
    
    private FidelityCard fidelityCard;
    
    private boolean consensus;
    
    public enum Contact_offers {
		email,
		phone;
	}
	
	/**
	 * contact to be used to send the offers
	 */
	protected Contact_offers contact_offers = Contact_offers.email;

	public Customer( String name, String username, String password, String surname,  String email, String phone) {
        super( name, username, password);
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.fidelityCard= new BasicFidelityCard();
        this.address=new Coordinate();
    }
	
	public Customer( String name, String username, String password, String surname) {
        super( name, username, password);
        this.surname = surname;
        this.fidelityCard= new BasicFidelityCard();
        this.address=new Coordinate();
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
	
	
	
	
	

	public Contact_offers getContact_offers() {
		return contact_offers;
	}

	public void setContact_offers(Contact_offers contact_offers, String mailOrPhone) {
		this.contact_offers = contact_offers;
		switch(contact_offers) {
		case email:
			this.email=mailOrPhone;
		case phone:
			this.phone=mailOrPhone;
		}
	}

	public boolean consensus() {
		return consensus;
	}

	public void setConsensus(boolean consensus) {
		if(!this.consensus) {
			if(consensus){
				NotificationService.getInstance().registerObserver(this);
			}
		}

		if(this.consensus) {
			if(!consensus){
				NotificationService.getInstance().removeObserver(this);
			}
		}
		
		this.consensus = consensus;
	}
	
	@Override
	public void update(Restaurant restaurant, Offer offer,Meal meal) {
		if(consensus) {
			switch(offer){
			case mealOfTheWeek:
				switch(this.contact_offers) {
				case email:
					System.out.println("New email received at " + this.email + ": Restaurant " + restaurant.getName() + " has a new special offer: "
							+ " New meal of the week: " + meal.getName());
					break;
				case phone:
					System.out.println("New sms received at " + this.phone + ": Restaurant " + restaurant.getName() + " has a new special offer: "
							+ " New meal of the week: " + meal.getName());
					break;
				}
				break;
				
			case genericDiscount:
				switch(this.contact_offers) {
				case email:
					System.out.println("New email received at " + this.email + ": Restaurant " + restaurant.getName() + " has a new special offer: "
							+ " New generic discount factor: " + restaurant.getGenericDiscount());
					break;
				case phone:
					System.out.println("New sms received at " + this.phone + ": Restaurant " + restaurant.getName() + " has a new special offer: "
							+ " New generic discount factor: " + restaurant.getGenericDiscount());
					break;
				}
				break;
				
			case specialDiscount:
				switch(this.contact_offers) {
				case email:
					System.out.println("New email received at " + this.email + ": Restaurant " + restaurant.getName() + " has a new special offer: "
							+ " New special discount factor: " + restaurant.getSpecialDiscount());
					break;
				case phone:
					System.out.println("New sms received at " + this.phone + ": Restaurant " + restaurant.getName() + " has a new special offer: "
							+ " New special discount factor: " + restaurant.getSpecialDiscount());
					break;
				}
				break;
			}
		}
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

	    System.out.println(String.format("You have paid €%.2f for your order.", price));
	    System.out.println("");

	    if (this.fidelityCard instanceof PointFidelityCard) {
	        PointFidelityCard pointCard = (PointFidelityCard) this.fidelityCard;
	        int numberAddedPts = pointCard.computeNumberAddedPoints(price);
	        pointCard.updatePoints(price);

	        if (numberAddedPts>=1) {
	        	String pointsMessage = String.format("You earned %d %s!", numberAddedPts, numberAddedPts == 1 ? "fidelity point" : "fidelity points");
	        	System.out.println(pointsMessage);
	        }
	        
	    }
	}

	
	public void endOrder(Order order) throws NotFoundException {
	    System.out.println("Finalizing your order...");
	    double price = order.getFinalPrice(); 
	    order.submitOrder(price); 

	    if (price > 0) {
	        System.out.println("Payment in process...");
	       
	        this.payOrder(order.getTotalPrice()); 
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
