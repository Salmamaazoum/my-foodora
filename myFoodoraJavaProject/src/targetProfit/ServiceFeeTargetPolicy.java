package targetProfit;

import Exceptions.NonReachableTargetProfitException;
import appSystem.AppSystem;

public class ServiceFeeTargetPolicy implements TargetProfitPolicy {
	
	@Override
	public double meetTargetProfit (AppSystem appSystem, double targetProfit) throws NonReachableTargetProfitException {
		int numberOfOrders = AppSystem.getOrders().size();
		double totalIncome = appSystem.getTotalIncomeLastMonth();
		double markupPercentage = appSystem.getMarkupPercentage();
		double deliveryCost = appSystem.getDeliveryCost();
		
		double serviceFee = (targetProfit - totalIncome*markupPercentage)/numberOfOrders + deliveryCost; 
		if (totalIncome==0){
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
		if (markupPercentage >= 0){
			return(serviceFee);
		}else{
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
	}
}
