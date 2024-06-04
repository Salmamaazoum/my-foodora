package food;

public interface AddItemVisitor {
	public void addItemToMeal(FoodItem item, HalfMeal halfMeal) throws BadMealCompositionCreationException;
	public void addItemToMeal(FoodItem item, FullMeal fullMeal) throws BadMealCompositionCreationException;
}
