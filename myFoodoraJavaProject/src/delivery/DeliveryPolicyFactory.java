package delivery;

import Exceptions.unknownDeliveryPolicyException;

public class DeliveryPolicyFactory {
	
	public DeliveryPolicyFactory() {
	}
	
	public DeliveryPolicy chooseDeliveryPolicy (String deliveryPolicyChoice) throws unknownDeliveryPolicyException  {
		DeliveryPolicy deliveryPolicy = null;
		switch(deliveryPolicyChoice){
			case("FastestPolicy"):
				deliveryPolicy = new FastestDeliveryPolicy();
				break;
			case("fairOccupationPolicy"):
				deliveryPolicy = new FairOccupationDeliveryPolicy();
				break;
			default:
				throw new unknownDeliveryPolicyException("unknown delivery policy!");
		}
		return (deliveryPolicy);
	}
}
