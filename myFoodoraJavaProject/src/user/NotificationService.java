package user;
import java.util.*;
import food.Meal;

public class NotificationService {
	private static NotificationService instance = null;
	
	public static ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	public static boolean changed;
	
	private NotificationService() {
		
	};
	
	public static NotificationService getInstance() {
		if(instance==null) {
			instance = new NotificationService();
			
		}
		return instance;
	}
	
	public void addSubscriber(Subscriber c) {
		subscribers.add(c);
	}
	
	public void removeSubscriber(Subscriber c) {
		if (subscribers.contains(c)) {
			subscribers.remove(c);
		}
	}
	
	public void notifySubscribers(Meal meal, Restaurant restaurant) {
		if (changed) {
			for (Subscriber c: subscribers) {
				c.update(meal,restaurant);
			}
		}
		changed=false;
	}
	
	public void setOffer(Meal meal, Restaurant restaurant) {
		changed=true;
		this.notifySubscribers(meal,restaurant);
		
	}
	
	
}

