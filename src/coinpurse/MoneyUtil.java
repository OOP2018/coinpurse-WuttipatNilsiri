package coinpurse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * MoneyUtil class that contain method for filter by currency and sort coin
 * @author Wuttipat Nilsiri
 *
 */
public class MoneyUtil {
	/**
	 * filter Valuable by Currency in list
	 * @param coinlist : List<Coin>
	 * @param currency : String
	 * @return new list that unmodifiable
	 */
	public static <E extends Valuable> List<E> filterByCurrency(final List<E> coinlist, String currency) {
		if (currency == null) {
			throw new IllegalArgumentException("Currency cannot be null");
		}
		List<E> temp = new ArrayList<>();
		for (E c : coinlist) {
			if (c.getCurrency().equalsIgnoreCase(currency)) {
				temp.add(c);
			}
		}
		return Collections.unmodifiableList(temp); 
													
	}
	
	public static <E extends Comparable<? super E> > E max(E ... args) {
		E max = null;
		for (E arg : args) {
			if (max == null) {
				max = arg;
			}
			else if (arg.compareTo(max) > 0) {
				max = arg;
			}
		}
		return max;
	}
	
	
	
	/**
	 * sort Valuable in the list
	 * @param coins inputed list 
	 */
	public static void sortMoney(List<? extends Valuable> money) {
		Comparator<Valuable> comp = new ValueComparator();
		money.sort(comp);
	}
	
	public static void main(String[] args) {
//		String currency = "Baht";
//		System.out.println("Filter coins by currency of " + currency);
//		List<Valuable> coins = makeInternationalCoins();
//		int size = coins.size();
//		System.out.print(" INPUT: ");
//		printList(coins, " ");
//		List<Valuable> rupees = filterByCurrency(coins, currency);
//		System.out.print("RESULT: ");
//		printList(rupees, " ");
//		if (coins.size() != size)
//			System.out.println("Error: you changed the original list.");
//
//		System.out.println("\nSort coins by Value");
//		coins = makeInternationalCoins();
//		System.out.print(" INPUT: ");
//		printList(coins, " ");
//		sortByValue(coins);
//		System.out.print("RESULT: ");
//		printList(coins, " ");
		Money m1 = new BankNote(100, "Baht");
		Money m2 = new BankNote(500, "Baht");
		Money m3 = new Coin(20, "Baht");
		Money max = MoneyUtil.max( m1, m2, m3 ); // should be max=m2
		System.out.println(max);
		List<BankNote> list = new ArrayList<BankNote>();
		list.add( new BankNote(10.0, "USD") );
		list.add( new BankNote(500.0, "Baht") );
		list.add(new BankNote(1.0, "Kuy"));
		list.add(new BankNote(2.0, "Kuy"));
		MoneyUtil.sortMoney( list );
		printList(list," ");
		printList(filterByCurrency(list,"Kuy")," ");
		List<Coin> coins = Arrays.asList( new Coin(5,"Baht"),
				 new Coin(0.1,"Ringgit"), new Coin(5,"Cent") );
				List<Coin> result = MoneyUtil.filterByCurrency(coins, "Baht"); // Error
		

		

		


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
