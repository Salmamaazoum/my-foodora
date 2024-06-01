package myFoodoraJavaProject;
import java.util.*;

public class Menu {
	
	private ArrayList<FoodItem> items;
	
	public Menu() {
		this.items = new ArrayList<FoodItem>();
	}
	
	public Menu(ArrayList<FoodItem> items) {
		this.items=items;
	}

	public ArrayList<FoodItem> getItems() {
		return items;
	}
	
	public void addItem(FoodItem item) {
		if (!items.contains(item)) {
			items.add(item);
		}
		
	}
	
	public void removeItem(FoodItem item) {
		if (items.contains(item)) {
			items.remove(item);
		}
	}
	
	

}
