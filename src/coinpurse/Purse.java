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

	List<Coin> money;
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
    	money = new ArrayList<Coin>(this.capacity);
    }

    /**
     * Count and return the number of coins in the purse.
     * This is the number of coins, not their value.
     * @return the number of coins in the purse
     */
    public int count() { return money.size(); }
    
    /** 
     *  Get the total value of all items in the purse.
     *  @return the total value of items in the purse.
     */
    public double getBalance() {
		double sum = 0;
    	for(Coin coin : money){
    		if(coin != null){
			sum = coin.getValue() + sum;
    		}
		}
    	return sum;
	}

    
    /**
     * Return the capacity of the coin purse.
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
     * Insert a coin into the purse.
     * The coin is only inserted if the purse has space for it
     * and the coin has positive value.  No worthless coins!
     * @param coin is a Coin object to insert into purse
     * @return true if coin inserted, false if can't insert
     */
    public boolean insert( Coin coin ) {
        if(!isFull() && coin != null && coin.getValue() > 0){
        	money.add(coin);
        	return true;
        }else
        	return false;
    }
    
    /**  
     *  Withdraw the requested amount of money.
     *  Return an array of Coins withdrawn from purse,
     *  or return null if cannot withdraw the amount requested.
     *  @param amount is the amount to withdraw
     *  @return array of Coin objects for money withdrawn, 
	 *    or null if cannot withdraw requested amount.
     */
    public Coin[] withdraw( double amount ) {
    	 if( amount < 0 ){
             return null;
         }
         Collections.sort(money);
         ArrayList<Coin> templist = new ArrayList<Coin>();
         if(getBalance() >= amount){
             for(int i = money.size() -1 ; i >= 0 ; i--){
                 if(amount - money.get(i).getValue() >= 0){
                     amount = amount - money.get(i).getValue();
                     templist.add(money.get(i));
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
         Coin [] array = new Coin[ templist.size() ]; // create the array
         templist.toArray(array);
         return array;
	}
  
    /** 
     * toString returns a string description of the purse contents.
     * It can return whatever is a useful description.
     */
    public String toString() {

    	return "purse with money : "+this.getBalance()+" size :"+this.getCapacity();
    }

}

