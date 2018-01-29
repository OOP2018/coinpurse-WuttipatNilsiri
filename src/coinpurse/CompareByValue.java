package coinpurse;

import java.util.Comparator;

public class CompareByValue implements Comparator<Valuable> {
	/**
	 * let compare Coin 1 with Coin 2
	 */
	@Override
	public int compare(Valuable arg0, Valuable arg1) {
		return arg0.compareTo(arg1);
	}

}
