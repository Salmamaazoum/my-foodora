package user;

import food.Meal;

public class PhoneSubscriber implements Subscriber {
	private String username;
	
	private int id;
	
	private String phone;
	
	public PhoneSubscriber(String name, int id, String phone) {
		this.username=name;
		this.id=id;
		this.phone=phone;
	}
	
	public void update(Meal meal, Restaurant restaurant) {
		System.out.println("Phone notification sent to " + username + ": Special offer for meal " + meal.getName()+ " offered by : "+restaurant.getName());
	}
}
