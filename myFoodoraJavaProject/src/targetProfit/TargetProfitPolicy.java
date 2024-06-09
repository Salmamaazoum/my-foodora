package targetProfit;

import Exceptions.NonReachableTargetProfitException;
import appSystem.AppSystem;

public interface TargetProfitPolicy {

	public abstract double meetTargetProfit (AppSystem appSystem, double targetProfit) throws NonReachableTargetProfitException;
	
}
