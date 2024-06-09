package targetProfit;

import Exceptions.NonReachableTargetProfitException;
import appSystem.AppSystem;

public class DeliveryCostTargetPolicy implements TargetProfitPolicy {
	

	@Override
	public void meetTargetProfit (AppSystem appSystem, double targetProfit) throws NonReachableTargetProfitException {
		int numberOfOrders = AppSystem.getOrders().size();
		double totalIncome = appSystem.getTotalIncomeLastMonth();
		double markupPercentage = appSystem.getMarkupPercentage();
		double serviceFee = appSystem.getServiceFee();
		
		double deliveryCost = - (targetProfit - totalIncome*markupPercentage)/numberOfOrders + serviceFee; 
		if (totalIncome==0){
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
		if (markupPercentage >= 0){
			appSystem.setDeliveryCost(deliveryCost);
		}else{
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
	}
}
