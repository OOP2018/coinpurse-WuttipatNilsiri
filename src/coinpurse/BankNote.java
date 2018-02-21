package coinpurse;
/**
 * Bank note with basic method for Banknote such as getvalue or currency
 * @author wuttipat nilsiri
 *
 */

public class BankNote extends Money {
	
	//variables
	//A unique parameter for BankNote.
	
	private long serialNumber = 1000000;
//	private long countgen = 0;
	
	/**
	 * A banknote with given value and serialNumber.
	 * @param value
	 * @param serialNumber
	 */
	public BankNote(double value, long serialNumber){
		super(value);
		this.serialNumber = serialNumber;
	}
	
	/**
	 * Initialize a bank note with a currency.
	 * @param value - initial value of bank note
	 * @param currency - currency of bank note
	 */
	public BankNote(double value, String currency, long serialNumber)
	{
		super(value,currency);
		this.serialNumber = serialNumber;
	}
	
	public BankNote(double value, String currency)
	{
		super(value,currency);
//		int range = (9999999 - 1000000) + 1;     
//    	double r = ((Math.random() * range) + 1000000);
	}
	
	
	/**
	 * @return serialNumber of the BankNote
	 */
	public long getSerial(){
		return this.serialNumber;
	}
	/**
	 * Check if the value of the BankNote are equal.
	 * @return true if the object are equal.
	 * @return false if the object are not equal.
	 */
	
	/**
	 * Convert BankNote to string.
	 * @return BankNote as a string.
	 */
	public String toString(){
		return getValue()+"--"+getCurrency()+" ["+serialNumber+"]";

	}

	
	

	

}
