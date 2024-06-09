package user;

import java.util.ArrayList;

public class RestaurantSorter {
	public ArrayList<Restaurant> sort (ArrayList<Restaurant> restaurants){
		ArrayList<Restaurant> sortedRestaurants = new ArrayList<Restaurant>(restaurants);
		sortedRestaurants.sort(new RestaurantComparator());
		return sortedRestaurants;
	}
}
