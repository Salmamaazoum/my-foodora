package delivery;

import java.util.List;

import Exceptions.NotFoundException;
import user.Coordinate;
import user.Courier;
import user.Customer;
import user.Order;

public class FairOccupationDeliveryPolicy implements DeliveryPolicy  {

	@Override
	public void allocateCourierToOrder(List<Courier> couriers, Order order) throws NotFoundException {
		double minDelivery = Double.POSITIVE_INFINITY;
		Courier lowestDeliveryCourier = null;
		for(Courier courier: couriers) {
			if (!courier.getOnDuty() && courier.getDeliveryCounter() < minDelivery ) {
				minDelivery = courier.getDeliveryCounter();
				lowestDeliveryCourier = courier;
			}
		}
		
		if (lowestDeliveryCourier!=null) {
			order.setCourier(lowestDeliveryCourier);
			lowestDeliveryCourier.setOnDuty(true);
			lowestDeliveryCourier.incrementDeliveryCounter();
		}
		
		else 
			throw new NotFoundException("No courier is currently available. Please try later ! ☺");
	}


}
