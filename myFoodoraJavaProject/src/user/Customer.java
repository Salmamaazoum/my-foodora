package user;
import java.util.*;

public class Customer extends User  {
	
	private String surname;
	private Coordinate address;
    private String email;
    private String phone;
    private ArrayList<Order> orderHistory = new ArrayList<Order>();

	public Customer( String name, String username, String password, String surname, Coordinate address, String email, String phone) {
        super( name, username, password);
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
	
	public Customer( String name, String username, String password, String surname, Coordinate address) {
        super( name, username, password);
        this.surname = surname;
        this.address = address;
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
	
	

	public ArrayList<Order> getOrderHistory() {
		return orderHistory;
	}


	@Override
	public String toString() {
		return "Customer: Username=" + getUsername()+ ", Name=" + getName() + ", surname=" + surname + ", address=" + address + ", email=" + email + ", phone=" + phone;
	}	
	
}
