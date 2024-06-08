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

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public foodCategory getCategory() {
		return category;
	}

	public void setCategory(foodCategory category) {
		this.category = category;
	}

	

	public boolean getIsVegetarian() {
		return isVegetarian;
	}

	public void setIsVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	


	public int getOrderedFrequency() {
		return orderedFrequency;
	}

	public void setOrderedFrequency(int orderedFrequency) {
		this.orderedFrequency = orderedFrequency;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIsGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}

	public boolean getIsGlutenFree() {
		return isGlutenFree;
	}

	public int getId() {
		return id;
	}
	
	public void updateOrderedFrequency() {
		this.orderedFrequency++;
	}

	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", name=" + name + ", isVegetarian=" + isVegetarian + ", price=" + price
				+ ", isGlutenFree=" + isGlutenFree + ", category=" + category + "]";
	}
	
	
	
	
	
	



	
}
