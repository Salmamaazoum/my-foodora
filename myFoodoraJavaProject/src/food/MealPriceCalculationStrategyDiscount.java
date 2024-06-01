package food;

public class MealPriceCalculationStrategyDiscount implements MealPriceCalculationStrategy {

	public double calculatePrice(Meal meal,double discount) {
		double totalPrice = 0;
		for (FoodItem item : meal.getMealComposition()) {
			totalPrice+=item.getPrice();
		}
		return totalPrice*(1-discount);
	}


}
