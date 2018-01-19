package coinpurse;

import java.util.Comparator;

public class CompareByValue implements Comparator<Coin> {
	/**
	 * let compare Coin 1 with Coin 2
	 */
	@Override
	public int compare(Coin arg0, Coin arg1) {
		return arg0.compareTo(arg1);
	}

}
