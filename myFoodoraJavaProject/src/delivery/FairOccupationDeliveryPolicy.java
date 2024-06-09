package delivery;

import java.util.List;

import user.Coordinate;
import user.Courier;
import user.Customer;
import user.Order;

public class FairOccupationDeliveryPolicy implements DeliveryPolicy  {

	@Override
	public void allocateCourierToOrder(List<Courier> couriers, Order order) {
		double minDelivery = Double.POSITIVE_INFINITY;
		Courier lowestDeliveryCourier = null;
		for(Courier courier: couriers) {
			if (!courier.getOnDuty() && courier.getDeliveryCounter() < minDelivery ) {
				minDelivery = courier.getDeliveryCounter();
				lowestDeliveryCourier = courier;
			}
		}
		order.setCourier(lowestDeliveryCourier);
		lowestDeliveryCourier.setOnDuty(true);
		lowestDeliveryCourier.incrementDeliveryCounter();
		
	}


}
