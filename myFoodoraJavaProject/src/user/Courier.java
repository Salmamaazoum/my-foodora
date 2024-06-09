package user;

import appSystem.AppSystem;

public class Courier extends User {
	
	private String surname;
	private Coordinate location;
	private String phone;
	private int deliveryCounter;
	private boolean OnDuty;
	
	public Courier( String name, String username, String password, String surname, Coordinate location, String phone) {
        super( name, username, password);
        this.surname = surname;
        this.location = location;
        this.phone = phone;
        this.deliveryCounter = 0;
	}
	
	public Courier( String name, String username, String password, String surname) {
        super( name, username, password);
        this.surname = surname;
        this.location = new Coordinate();
        this.deliveryCounter = 0;
    }
	
	public int getDeliveryCounter() {
		return this.deliveryCounter;
	}
	
	public void setDeliveryCounter(int deliveryCounter) {
		this.deliveryCounter = deliveryCounter;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean getOnDuty() {
		return OnDuty;
	}

	public void setOnDuty(boolean OnDuty) {
		this.OnDuty = OnDuty;
	}
	
	public void incrementDeliveryCounter() {
		deliveryCounter++;
	}
	
	
	@Override
	public String toString() {
		return "Customer: Username=" + getUsername()+ ", Name=" + getName() + ", surname=" + surname + ", location=" + location + ", deliveryCounter=" + deliveryCounter + ", isOnDuty=" + OnDuty;
	}
}
