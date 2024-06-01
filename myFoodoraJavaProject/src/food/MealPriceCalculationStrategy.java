package food;

public interface MealPriceCalculationStrategy {
	public double calculatePrice(Meal meal, double discount);
}
