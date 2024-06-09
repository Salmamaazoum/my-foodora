package food;

public abstract class MenuComponent {
	
    protected static int idCounter = 0;

    protected int id;
	
	protected String name = "";
	
	protected boolean isVegetarian ;
	
	protected double price;
	
	protected boolean isGlutenFree;
	
	protected int orderedFrequency;
	
	public MenuComponent() {
		this.id=++idCounter;
	}
	
	public MenuComponent(String name, boolean isVegetarian, boolean isGlutenFree) {
		this.id = ++idCounter;
		this.name=name;
		this.isVegetarian=isVegetarian;
		this.isGlutenFree=isGlutenFree;
	}
	
	public MenuComponent(String name, boolean isVegetarian, boolean isGlutenFree,double price) {
		this.id = ++idCounter;
		this.isVegetarian=isVegetarian;
		this.name=name;
		this.price=price;
		this.isGlutenFree=isGlutenFree;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVegetarian() {
		return isVegetarian;
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

	public boolean isGlutenFree() {
		return isGlutenFree;
	}

	public void setGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}

	public int getOrderedFrequency() {
		return orderedFrequency;
	}

	
	public void updateOrderedFrequency() {
		this.orderedFrequency++;
	}

	
	
}
