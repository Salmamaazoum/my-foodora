package user;

import java.util.ArrayList;


public class CourierSorter {
	public ArrayList<Courier> sort (ArrayList<Courier> couriers){
		ArrayList<Courier> sortedCouriers = new ArrayList<Courier>(couriers);
		sortedCouriers.sort(new CourierComparator());
		return sortedCouriers;
	}
}
