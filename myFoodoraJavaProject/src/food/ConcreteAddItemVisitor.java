package food;

public class ConcreteAddItemVisitor implements AddItemVisitor{
	
	@Override
	
	public void addItemToMeal (FoodItem item, HalfMeal halfMeal) throws BadMealCompositionCreationException {
		
		if (halfMeal.getMealComposition().size()==2) {
			throw new BadMealCompositionCreationException("attempt to add third item to a half-meal");
		}
		
		if (halfMeal.getMealComposition().size()==1) {
			if (item.getCategory().equals(halfMeal.getMealComposition().get(0).getCategory())) {
				throw new BadMealCompositionCreationException("attempt to create half-meal with duplicate categories");
			}
			if ( (!halfMeal.getMealComposition().get(0).getCategory().equals(FoodItem.foodCategory.MAINDISH)) && (!item.getCategory().equals(FoodItem.foodCategory.MAINDISH))  ) {
				throw new BadMealCompositionCreationException("attempt to create a half-meal with no main-dish");
			}
	
		}
		if ( (halfMeal.isVegetarian()) && (!item.getIsVegetarian())) {
			throw new BadMealCompositionCreationException("attempt to add non-vegetarian item to a vegetarian meal");
		}
		
		if ( (halfMeal.isGlutenFree()) && (!item.getIsGlutenFree())) {
			throw new BadMealCompositionCreationException("attempt to add gluten-containing item to a gluten-free meal");
		}
		
		halfMeal.getMealComposition().add(item);
	}
	
	
	@Override
	public void addItemToMeal (FoodItem item, FullMeal fullMeal) throws BadMealCompositionCreationException {
		if (fullMeal.getMealComposition().size()==3) {
			throw new BadMealCompositionCreationException("attempt to add fourth item to a full-meal");
		}
		for (FoodItem existingItem : fullMeal.getMealComposition()) {
            if (item.getCategory().equals(existingItem.getCategory())) {
                throw new BadMealCompositionCreationException("attempt to create full-meal with duplicate categories");
            }
		}
		
		if ( (fullMeal.isVegetarian()) && (!item.getIsVegetarian())) {
			throw new BadMealCompositionCreationException("attempt to add non-vegetarian item to a vegetarian meal");
		}
		
		if ( (fullMeal.isGlutenFree()) && (!item.getIsGlutenFree())) {
			throw new BadMealCompositionCreationException("attempt to add gluten-containing item to a gluten-free meal");
		}
		
		fullMeal.getMealComposition().add(item);
	}
}
