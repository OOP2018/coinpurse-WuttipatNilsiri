package coinpurse;
/**
 * Bank note with basic method for Banknote such as getvalue or currency
 * @author wuttipat nilsiri
 *
 */

public class Banknote implements Valuable {
	
	//variables
	private String currency = "Baht";
	private double value;
	//A unique parameter for BankNote.
	private long serialNumber = 1000000;
	
	/**
	 * A banknote with given value and serialNumber.
	 * @param value
	 * @param serialNumber
	 */
	public Banknote(double value, long serialNumber){
		this.value = value;
		this.serialNumber = serialNumber;
	}
	
	/**
	 * Initialize a bank note with a currency.
	 * @param value - initial value of bank note
	 * @param currency - currency of bank note
	 */
	public Banknote(double value, String currency, long serialNumber)
	{
		this.value = value;
		this.currency = currency;
		this.serialNumber = serialNumber;
	}
	
	/**
	 * @return value of the banknote.
	 */
	public double getValue() {
		return getValue() ;

	}

	/**
	 * @return currency of the banknote
	 */
	public String getCurrency() {
		return getCurrency();
	}
	/**
	 * @return serialNumber of the BankNote
	 */
	public long getSerialNumber(){
		return this.serialNumber;
	}
	/**
	 * Check if the value of the BankNote are equal.
	 * @return true if the object are equal.
	 * @return false if the object are not equal.
	 */
	public boolean equals(Object obj){
		if(obj.getClass() != this.getClass() || obj == null){
			return false;
		}
		Banknote other = (Banknote) obj;
		return this.getValue() == other.getValue() && this.getCurrency().equals(other.getCurrency());
	}
	
	/**
	 * Convert BankNote to string.
	 * @return BankNote as a string.
	 */
	public String toString(){
		return value+"--"+currency+" ["+serialNumber+"]";

	}

	@Override
	public int compareTo(Valuable other) {
		if(this.value < other.getValue()){
			return -1;
		}
		else if(this.value > other.getValue()){
			return 1;
		}
		else{
			return 0;
		}
	}

	

}
