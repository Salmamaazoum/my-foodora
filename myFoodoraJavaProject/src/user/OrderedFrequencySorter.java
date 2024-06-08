package user;
import food.*;
import java.util.*;

public class OrderedFrequencySorter {
	
	public ArrayList<MenuComponent> sort (ArrayList<MenuComponent> menuComponents){
		ArrayList<MenuComponent> sortedMenuComponents = new ArrayList<MenuComponent>(menuComponents);
		sortedMenuComponents.sort(new OrderedFrequencyComparator());
		return sortedMenuComponents;
	}
}
