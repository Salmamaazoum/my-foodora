package food;

public class FoodItem extends MenuComponent {	
	
	public enum foodCategory {
		STARTER, MAINDISH , DESSERT
	}
	
	private foodCategory category;
	
	public FoodItem() {
		super();
	}
	
	public FoodItem(String name, foodCategory category, boolean isVegetarian, boolean isGlutenFree,double price) {
		super(name,isVegetarian,isGlutenFree,price);
		this.category=category;
	}


	public foodCategory getCategory() {
		return category;
	}

	public void setCategory(foodCategory category) {
		this.category = category;
	}



	@Override
	public String toString() {
	    return "FoodItem {\n" +
	           "  id: " + id + ",\n" +
	           "  name: " + name + ",\n" +
	           "  isVegetarian: " + (isVegetarian ? "Yes" : "No") + ",\n" +
	           "  price: $" + String.format("%.2f", price) + ",\n" +
	           "  isGlutenFree: " + (isGlutenFree ? "Yes" : "No") + ",\n" +
	           "  category: " + category + "\n" +
	           "}";
	}
	
	
	
	
	



	
}
