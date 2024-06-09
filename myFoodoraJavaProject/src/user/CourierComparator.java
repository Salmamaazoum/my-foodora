package user;

import java.util.Comparator;


public class CourierComparator implements Comparator<Courier> {
	@Override
	public int compare(Courier c1, Courier c2) {
		return c2.getDeliveryCounter()-c2.getDeliveryCounter();
	}
}
