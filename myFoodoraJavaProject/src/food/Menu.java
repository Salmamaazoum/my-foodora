package food;
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

	@Override
	public String toString() {
		if (!this.items.isEmpty()) {
			String s ="The dishes of the Menu are the following :";
			for (FoodItem item : this.items) {
				s+= item.getName() +" ";
			}
			return s;
		}
		else {
			return "The restaurant's menu is empty";
		}
	}
	
	
	

}
