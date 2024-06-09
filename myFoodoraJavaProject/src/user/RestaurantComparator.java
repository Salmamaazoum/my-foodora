package user;

import java.util.Comparator;

public class RestaurantComparator implements Comparator<Restaurant> {
	@Override
	public int compare(Restaurant r1, Restaurant r2) {
		return r2.getOrderCounter()-r1.getOrderCounter();
	}
}
