package user;

public class FidelityCardFactory {
	
	public static FidelityCard createFidelityCard(String cardType) {
		FidelityCard card = null;
		if ( cardType.equalsIgnoreCase(FidelityCardType.BASIC.toString()) ){
			card=  new BasicFidelityCard();
		}
		if ( cardType.equalsIgnoreCase(FidelityCardType.POINT.toString()) ){
			card = new PointFidelityCard();
		}
		if ( cardType.equalsIgnoreCase(FidelityCardType.LOTTERY.toString()) ){
			card = new LotteryFidelityCard();
		}
		
		if (card == null) {
			System.out.println("The fidelity card type must be either basic, point or lottery.");
		}
		
		return card;
		
	}
}
