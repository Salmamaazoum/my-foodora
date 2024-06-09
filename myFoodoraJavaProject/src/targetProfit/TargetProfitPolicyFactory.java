package targetProfit;

import Exceptions.unknownProfitPolicyException;
import appSystem.AppSystem;

public class TargetProfitPolicyFactory {
	public TargetProfitPolicyFactory() {
	}
	
	public TargetProfitPolicy chooseTargetProfitPolicy (String profitPolicy) throws unknownProfitPolicyException {
		TargetProfitPolicy targetProfitPolicy = null;
		switch(profitPolicy){
			case("deliveryCost"):
				targetProfitPolicy = new DeliveryCostTargetPolicy();
				break;
			case("markup"):
				targetProfitPolicy = new MarkupTargetPolicy();
				break;
			case("serviceFee"):
				targetProfitPolicy = new ServiceFeeTargetPolicy();
				break;
			default:
				throw new unknownProfitPolicyException("unknown Profit Policy");
		}
		return (targetProfitPolicy);
	}
}
