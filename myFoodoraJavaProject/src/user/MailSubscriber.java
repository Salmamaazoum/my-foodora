package user;
import food.Meal;

public class MailSubscriber implements Subscriber{
	private String username;
	
	private int id;
	
	private String mail;
	
	public MailSubscriber(String name, int id, String mail) {
		this.username=name;
		this.id=id;
		this.mail=mail;
	}
	public void update(Meal meal,Restaurant restaurant) {
		System.out.println("Email notification sent to " + username + ": Special offer for meal " + meal.getName()+ " offered by : "+restaurant.getName());
	}
}
