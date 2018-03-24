package coinpurse;


import java.util.*;

import strategy.GreedyWithdraw;
import strategy.WithdrawStrategy;

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
	
	private WithdrawStrategy str = new GreedyWithdraw();
	private Comparator<Valuable> comp = new ValueComparator();

	/** 
	 *  Create a purse with a specified capacity.
	 *  @param capacity is maximum number of coins you can put in purse.
	 */
	public Purse( int capacity) {
		this.capacity = capacity;
		money = new ArrayList<Valuable>(this.capacity);
	}
	
	public void setStr(WithdrawStrategy str) {
		this.str = str;
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
	 *  Withdraw the requested amount of money. from Valuable list 
	 *  Return an array of Valuables withdrawn from purse,
	 *  or return null if cannot withdraw the amount requested.
	 *  @param amount is the amount to withdraw
	 *  @return array of Coin objects for money withdrawn, 
	 *    or null if cannot withdraw requested amount.
	 */

	 public Valuable[] withdraw( Valuable amount ) {
		   	if(amount == null || amount.getValue() <= 0 ) return null;	
//		    	 double cash = amount.getValue();
		    	 Collections.sort(money, comp);
		    	 
		    	 List<Valuable> m =  MoneyUtil.filterByCurrency(money,amount.getCurrency());
		    	 List<Valuable> temp = new ArrayList<Valuable>();
		    	
		//   
		    	 if(getBalance() >= amount.getValue()){
//		    		 for (Valuable value : m){
//		    			 if (cash - value.getValue() >= 0){
//		    				 cash -= value.getValue();
//		    				 temp.add(value);
//		    			 } 
		    		 temp = str.withdraw(amount, m);
		   		 }
		     		
//		    	 }
		    	 if (temp == null ) return null;
		    	 
		    	 for (Valuable value : temp){
		    		 money.remove(value);
		    	 }
		   	 
		    	 Valuable[] array = new Valuable[temp.size()];// create the array
		         temp.toArray(array);
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
		Valuable money = new BankNote(amount,"Baht");
		return withdraw(money);
	}
	/**  
	 *  Withdraw the requested amount of money. type Valuable
	 *  Return an array of Valuables withdrawn from purse,
	 *  or return null if cannot withdraw the amount requested.
	 *  @param amount is the amount to withdraw
	 *  @return array of Coin objects for money withdrawn, 
	 *    or null if cannot withdraw requested amount.
	 */

	//	public Valuable[] withdraw(Valuable amountin ) {
	//		double amount = amountin.getValue();
	//		if( amount < 0 ){
	//			return null;
	//		}
	//		money.sort(new ValueComparator());
	//		List<Valuable> filteredmoney = MoneyUtil.filterByCurrency(money,amountin.getCurrency());
	//		return withdraw(amount,filteredmoney);
	//	}

	/** 
	 * toString returns a string description of the purse contents.
	 * It can return whatever is a useful description.
	 */
	public String toString() {
		System.out.println(money);
		return "purse with money : "+" size :"+this.getCapacity();

	}

	public List<Valuable> getMoney() {
		// TODO Auto-generated method stub
		return money;
	}

}

