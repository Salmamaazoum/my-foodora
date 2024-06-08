package user;
import java.util.Comparator;

import food.*;

public class OrderedFrequencyComparator implements Comparator<MenuComponent>{
	
	@Override
	public int compare(MenuComponent m1, MenuComponent m2) {
		return m2.getOrderedFrequency()-m1.getOrderedFrequency();
	}
}
