package NotificationService;
import user.*;
import food.*;
public interface Observable {

		/**
		 * 
		 * @param observer new observer to be added
		 */
		public void registerObserver(Observer observer);
		
		/**
		 * 
		 * @param observer new observer to be removed
		 */
		public void removeObserver(Observer observer);
		
		/**
		 * notify about an offer
		 */
		public void notifyObservers(Meal meal,Restaurant restaurant, Offer offer);
		
		/*
		 * set the Offer change
		 */
		
		public void setOffer(Meal meal, Restaurant restaurant,Offer offer);

}
