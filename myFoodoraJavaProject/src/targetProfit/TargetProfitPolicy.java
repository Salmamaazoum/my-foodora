package targetProfit;

import Exceptions.NonReachableTargetProfitException;
import appSystem.AppSystem;

public interface TargetProfitPolicy {

	public abstract void meetTargetProfit (AppSystem appSystem, double targetProfit) throws NonReachableTargetProfitException;
	
}
