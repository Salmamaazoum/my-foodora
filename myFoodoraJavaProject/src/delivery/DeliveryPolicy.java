package delivery;

import user.Order;

import java.util.List;

import Exceptions.NotFoundException;
import user.Courier;
import user.Customer;

public interface DeliveryPolicy {
	
	public abstract void allocateCourierToOrder(List<Courier> couriers, Order order) throws NotFoundException;
	
}
