package food;

public class FoodItem {
	
	private String name;
	
	public boolean isVegetarian ;
	
	private double price;
	
	public boolean isGlutenFree;
	
	private String category;
	
	public FoodItem(String name,String category, boolean isVegetarian, boolean isGlutenFree,double price) {
		
		this.isVegetarian=isVegetarian;
		this.name=name;
		this.price=price;
		this.isGlutenFree=isGlutenFree;
		this.category=category;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public void setGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}



	
}
