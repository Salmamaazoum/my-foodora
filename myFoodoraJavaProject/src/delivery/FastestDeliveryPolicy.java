package delivery;

import java.util.List;

import user.Coordinate;
import user.Courier;
import user.Customer;
import user.Order;

public class FastestDeliveryPolicy implements DeliveryPolicy {

	@Override
	public void allocateCourierToOrder(List<Courier> couriers, Order order) {
		double minDistance = Double.POSITIVE_INFINITY;
		Courier nearestCourier = null;
		Coordinate position = order.getRestaurant().getLocation();
		for(Courier courier: couriers) {
			double currDistance = position.calculateDistance(courier.getLocation());
			if (!courier.getOnDuty() && currDistance < minDistance ) {
				minDistance = currDistance;
				nearestCourier = courier;
			}
		}
		order.setCourier(nearestCourier);
		nearestCourier.setOnDuty(true);
		nearestCourier.incrementDeliveryCounter();
	}

}
