package targetProfit;

import Exceptions.NonReachableTargetProfitException;
import appSystem.AppSystem;

public class MarkupTargetPolicy implements TargetProfitPolicy {
	

	@Override
	public void meetTargetProfit (AppSystem appSystem, double targetProfit) throws NonReachableTargetProfitException {
		int numberOfOrders = AppSystem.getOrders().size();
		double totalIncome = appSystem.getTotalIncomeLastMonth();
		double serviceFee = appSystem.getServiceFee();
		double deliveryCost = appSystem.getDeliveryCost();
		
		double markupPercentage = (targetProfit - numberOfOrders*(serviceFee - deliveryCost))/totalIncome; 
		if (totalIncome==0){
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
		if (markupPercentage >= 0){
			appSystem.setMarkupPercentage(markupPercentage);
		}else{
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
	}

}
