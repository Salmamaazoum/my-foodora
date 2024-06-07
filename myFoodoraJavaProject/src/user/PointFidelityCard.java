package user;

public class PointFidelityCard extends FidelityCard {
	
	/*
	 * Depends on the customer, the number of points accumulated so far.
	 */
	private int points = 0;
	
	/*
	 * the price to reach in order to receive 1 point
	 */
	
	private static double amountReduction = 10 ;
	
	/**
	 * the number of points to reach in order to get a discount in the next order
	 */
	
	private static int targetPoints = 100 ;
	
	/**
	 * the rate of the discount applied when the targetPoints value is reached
	 */
	
	private static double discountFactor = 0.1 ;
	
	public PointFidelityCard() {
		super(FidelityCardType.POINT);
	}
	
	public double computeOrderReduction(Order order) {
		double reduction = 0;
		if (this.points>=targetPoints) {
			reduction = order.getPrice()*0.1;
		}
		return reduction;
	}
	
	
}
