package user;
import java.util.*;
public class LotteryFidelityCard extends FidelityCard {
	
	private static double probability;
	
	private Calendar lastTimeUsed;
	
	public LotteryFidelityCard() {
		super(FidelityCardType.LOTTERY);
	}

	public static double getProbability() {
		return probability;
	}

	public static void setProbability(double probability) {
		LotteryFidelityCard.probability = probability;
	}
	
	
}
