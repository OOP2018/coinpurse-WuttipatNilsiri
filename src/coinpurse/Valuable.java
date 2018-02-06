package coinpurse;
/**
 * An interface for objects having a monetary value and currency.
 * @author wuttipat nilsiri
 */
public interface Valuable extends Comparable<Valuable> {
	/*
	 * Get the monetary value of this object, in it's own currency
	 * @return the value of this object
	 */
	public double getValue();
	/**
	 * to know the currency
	 * @return the currency of this object
	 */	
	public String getCurrency();
	
	
	
	
}