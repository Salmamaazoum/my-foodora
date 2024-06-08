package FidelityCards;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map.Entry;

import food.Meal;
import user.Order;

public class LotteryFidelityCard extends FidelityCard {
	
	private static double probability=0.5;
	
	private Calendar lastTimeUsed = null; // never used before
	
	public LotteryFidelityCard() {
		super(FidelityCardType.LOTTERY);
	}
	

	public static double getProbability() {
		return probability;
	}

	public static void setProbability(double probability) {
		LotteryFidelityCard.probability = probability;
	}
	
	public static boolean areDifferentDays(Calendar cal1, Calendar cal2) {
        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);

        int year2 = cal2.get(Calendar.YEAR);
        int month2 = cal2.get(Calendar.MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);

        return (year1 != year2 || month1 != month2 || day1 != day2);
    }
	
	
	public double computeOrderReduction (Order order) {
		double reduction = 0;
		
		if (lastTimeUsed == null || areDifferentDays(order.getOrderDate(), this.lastTimeUsed)) {
			
			if (Math.random()<probability) {
				
				this.lastTimeUsed = (Calendar) order.getOrderDate().clone();
				
				double maxMealPrice = 0 ;
				
				for (Entry<Meal, Integer> entry : order.getOrderMeals().entrySet()) {
					if (entry.getKey().getPrice()>maxMealPrice) {
						maxMealPrice = entry.getKey().getPrice();
					}
				}
				
				reduction = maxMealPrice;
				if(reduction!=0)
					System.out.println("It's your lucky day! You earned a free meal using your fidelity card ☺");
			}
		}
		return reduction;
	}
	
	public double computeOrderPrice(Order order) {
		double reduction = this.computeOrderReduction(order);
		double price = order.getFirstPrice();
		return price - reduction;
	}

	@Override
	public String toString() {
	    String lastUsedStr = (lastTimeUsed == null) ? "Never used" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastTimeUsed.getTime());
	    return "Lottery Fidelity Card :" + "\n" + "Last time used: " + lastUsedStr + "\n";
	}
	
	
	
}
