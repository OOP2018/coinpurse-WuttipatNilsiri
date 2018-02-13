package coinpurse;


import java.util.*;

/**
 *  A coin purse contains coins.
 *  You can insert coins, withdraw money, check the balance,
 *  and check if the purse is full.
 *  
 *  @author wuttipat nilsiri
 */
public class Purse {
	/** Collection of objects in the purse. */

	List<Valuable> money;
	/** Capacity is maximum number of items the purse can hold.
	 *  Capacity is set when the purse is created and cannot be changed.
	 */
	private final int capacity;

	/** 
	 *  Create a purse with a specified capacity.
	 *  @param capacity is maximum number of coins you can put in purse.
	 */
	public Purse( int capacity ) {
		this.capacity = capacity;
		money = new ArrayList<Valuable>(this.capacity);
	}

	/**
	 * Count and return the number of items in the purse.
	 * This is the number of items, not their value.
	 * @return the number of items in the purse
	 */
	public int count() { return money.size(); }

	/** 
	 *  Get the total value of all items in the purse.
	 *  @return the total value of items in the purse.
	 */
	public double getBalance() {
		double sum = 0;
		for(Valuable coin : money){
			if(coin != null){
				sum = coin.getValue() + sum;
			}
		}
		return sum;
	}


	/**
	 * Return the capacity of the purse.
	 * @return the capacity
	 */

	public int getCapacity() { 
		return capacity; 
	}

	/** 
	 *  Test whether the purse is full.
	 *  The purse is full if number of items in purse equals
	 *  or greater than the purse capacity.
	 *  @return true if purse is full.
	 */
	public boolean isFull() {

		return count() == capacity;
	}

	/** 
	 * Insert a item into the purse.
	 * The item is only inserted if the purse has space for it
	 * and the item has positive value.  No worthless coins!
	 * @param item is a Coin object to insert into purse
	 * @return true if item inserted, false if can't insert
	 */
	public boolean insert( Valuable coin ) {
		if(!isFull() && coin != null && coin.getValue() > 0){
			money.add(coin);
			money.sort(new ValueComparator());
			return true;
		}else
			return false;
	}

	/**  
	 *  Withdraw the requested amount of money. from list
	 *  Return an array of Valuables withdrawn from purse,
	 *  or return null if cannot withdraw the amount requested.
	 *  @param amount is the amount to withdraw
	 *  @return array of Coin objects for money withdrawn, 
	 *    or null if cannot withdraw requested amount.
	 */
	
	public Valuable[] withdraw(double amount, List<Valuable> list){
		if( amount < 0 ){
			return null;
		}
		ArrayList<Valuable> templist = new ArrayList<Valuable>();
		if(getBalance() >= amount){
			for(int i = list.size() -1 ; i >= 0 ; i--){
				if(amount - list.get(i).getValue() >= 0){
					amount = amount - list.get(i).getValue();
					templist.add(list.get(i));
				}
			}
		}
		// This code assumes you decrease amount each time you remove a coin.
		if ( amount > 0 ) {
			// failed. Don't change the contents of the purse.
			return null;
		}
		for(int i = 0 ; i < templist.size() ; i++){
			money.remove(templist.get(i));
		}
		Valuable [] array = new Valuable[ templist.size() ]; // create the array
		templist.toArray(array);
		return array;
	}
	/**  
	 *  Withdraw the requested amount of money.
	 *  Return an array of Valuables withdrawn from purse,
	 *  or return null if cannot withdraw the amount requested.
	 *  @param amount is the amount to withdraw
	 *  @return array of Coin objects for money withdrawn, 
	 *    or null if cannot withdraw requested amount.
	 */
	public Valuable[] withdraw( double amount ) {
		if( amount < 0 ){
			return null;
		}
		money.sort(new ValueComparator());
		return withdraw(amount,money);
	}
	/**  
	 *  Withdraw the requested amount of money. type Valuable
	 *  Return an array of Valuables withdrawn from purse,
	 *  or return null if cannot withdraw the amount requested.
	 *  @param amount is the amount to withdraw
	 *  @return array of Coin objects for money withdrawn, 
	 *    or null if cannot withdraw requested amount.
	 */

	public Valuable[] withdraw(Valuable amountin ) {
		double amount = amountin.getValue();
		if( amount < 0 ){
			return null;
		}
		money.sort(new ValueComparator());
		List<Valuable> filteredmoney = MoneyUtil.filterByCurrency(money,amountin.getCurrency());
		return withdraw(amount,filteredmoney);
	}

	/** 
	 * toString returns a string description of the purse contents.
	 * It can return whatever is a useful description.
	 */
	public String toString() {
		System.out.println(money);
		return "purse with money : "+" size :"+this.getCapacity();

	}

}

