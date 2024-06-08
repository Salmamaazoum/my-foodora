package user;

public abstract class FidelityCard {
	protected FidelityCardType type;
	
	public FidelityCard() {
		
	}
	
	public FidelityCard(FidelityCardType type) {
		this.type = type;
	}

	public FidelityCardType getType() {
		return type;
	}

	public void setType(FidelityCardType type) {
		this.type = type;
	}
	
	public abstract double computeOrderReduction (Order order);
	
	public abstract double computeOrderPrice(Order order);
	
	
	
}
