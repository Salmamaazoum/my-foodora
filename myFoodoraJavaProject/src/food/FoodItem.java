package food;

public class FoodItem {
	
    private static int idCounter = 0;

    private int id;
	
	private String name = "";
	
	private boolean isVegetarian ;
	
	private double price;
	
	public boolean isGlutenFree;
	
	public enum foodCategory {
		STARTER, MAINDISH , DESSERT
	}
	
	private foodCategory category;
	
	public FoodItem() {
		this.id = ++idCounter;
	}
	
	public FoodItem(String name, foodCategory category, boolean isVegetarian, boolean isGlutenFree,double price) {
		this.id = ++idCounter;
		this.isVegetarian=isVegetarian;
		this.name=name;
		this.price=price;
		this.isGlutenFree=isGlutenFree;
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


	public void setIsGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}

	public boolean getIsGlutenFree() {
		return isGlutenFree;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", name=" + name + ", isVegetarian=" + isVegetarian + ", price=" + price
				+ ", isGlutenFree=" + isGlutenFree + ", category=" + category + "]";
	}
	
	
	
	
	
	



	
}
