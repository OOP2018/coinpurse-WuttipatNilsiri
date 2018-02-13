package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * MoneyUtil class that contain method for filter by currency and sort coin
 * @author ARZ
 *
 */
public class MoneyUtil {
	/**
	 * filter Valuable by Currency in list
	 * @param coinlist : List<Coin>
	 * @param currency : String
	 * @return new list that unmodifiable
	 */
	public static List<Valuable> filterByCurrency(final List<Valuable> coinlist, String currency) {
		if (currency == null) {
			throw new IllegalArgumentException("Currency cannot be null");
		}
		List<Valuable> temp = new ArrayList<>();
		for (Valuable c : coinlist) {
			if (c.getCurrency().equalsIgnoreCase(currency)) {
				temp.add(c);
			}
		}
		return Collections.unmodifiableList(temp); 
													
	}
	
	/**
	 * sort Valuable in the list
	 * @param coins 
	 */
	public static void sortByValue(List<Valuable> coins) {
		coins.sort(new ValueComparator());
	}
	
	public static void main(String[] args) {
		String currency = "Baht";
		System.out.println("Filter coins by currency of " + currency);
		List<Valuable> coins = makeInternationalCoins();
		int size = coins.size();
		System.out.print(" INPUT: ");
		printList(coins, " ");
		List<Valuable> rupees = filterByCurrency(coins, currency);
		System.out.print("RESULT: ");
		printList(rupees, " ");
		if (coins.size() != size)
			System.out.println("Error: you changed the original list.");

		System.out.println("\nSort coins by Value");
		coins = makeInternationalCoins();
		System.out.print(" INPUT: ");
		printList(coins, " ");
		sortByValue(coins);
		System.out.print("RESULT: ");
		printList(coins, " ");

		


	}
	/** for test */
	public static List<Valuable> makeInternationalCoins() {
		List<Valuable> money = new ArrayList<Valuable>();
		money.addAll(makeCoins("Baht", 0.25, 1.0, 2.0, 5.0, 10.0, 10.0));
		money.addAll(makeCoins("Ringgit", 2.0, 50.0, 1.0, 5.0));
		money.addAll(makeCoins("Rupee", 0.5, 0.5, 10.0, 1.0));
		// randomize the elements
		Collections.shuffle(money);
		return money;
	}

	/** Make a list of coins using given values. */
	public static List<Coin> makeCoins(String currency, double... values) {
		List<Coin> list = new ArrayList<Coin>();
		for (double value : values)
			list.add(new Coin(value, currency));
		return list;
	}

	/** Print the list on the console, on one line. */
	public static void printList(List items, String separator) {
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next());
			if (iter.hasNext())
				System.out.print(separator);
		}
		System.out.println(); // end the line
	}
	
}
