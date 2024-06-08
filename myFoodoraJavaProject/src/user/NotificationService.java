package user;
import java.util.*;
import food.Meal;

public class NotificationService {
	private static NotificationService instance = null;
	
	public static ArrayList<Observer> subscribers = new ArrayList<Observer>();
	
	public static boolean changed;
	
	private NotificationService() {
		
	};
	
	public static NotificationService getInstance() {
		if(instance==null) {
			instance = new NotificationService();
			
		}
		return instance;
	}
	
	public void addObserver(Observer c) {
		subscribers.add(c);
	}
	
	public void removeObserver(Observer c) {
		if (subscribers.contains(c)) {
			subscribers.remove(c);
		}
	}
	
	public void notifySubscribers(Meal meal, Restaurant restaurant,Offer offer) {
		if (changed) {
			for (Observer c: subscribers) {
				c.update(restaurant,offer,meal);
			}
		}
		changed=false;
	}
	
	public void setOffer(Meal meal, Restaurant restaurant,Offer offer) {
		changed=true;
		this.notifySubscribers(meal,restaurant,offer);
		
	}
	
	
}

