package user;

import appSystem.AppSystem;

public class Courier extends User {
	
	private String surname;
	private Coordinate location;
	private String phone;
	private int deliveryCounter;
	
	public Courier( String name, String username, String password, String surname, Coordinate location, String phone) {
        super( name, username, password);
        this.surname = surname;
        this.location = location;
        this.phone = phone;
        this.deliveryCounter = 0;
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
	
    @Override
	public boolean addNewUser() {
		AppSystem appSystem = AppSystem.getInstance();
		return appSystem.addCourier(this);
	}
}
