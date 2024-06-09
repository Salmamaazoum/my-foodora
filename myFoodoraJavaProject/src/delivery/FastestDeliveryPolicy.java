package delivery;

import java.util.List;

import Exceptions.NotFoundException;
import user.Coordinate;
import user.Courier;
import user.Customer;
import user.Order;

public class FastestDeliveryPolicy implements DeliveryPolicy {

	@Override
	public void allocateCourierToOrder(List<Courier> couriers, Order order) throws NotFoundException {
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
		
		if(nearestCourier!=null) {
			order.setCourier(nearestCourier);
			nearestCourier.setOnDuty(true);
			nearestCourier.incrementDeliveryCounter();
		}
		
		else {
			throw new NotFoundException("No courier is currently available. Please try later ! ☺");
		}
	}

}
