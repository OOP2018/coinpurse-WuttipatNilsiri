package coinpurse;
/**
 * Abstract Class for kind of Money that is valuable
 * @author Wuttipat nilsiri
 *
 */
public abstract class Money implements Valuable{
	private double value;
	private String currency = "Baht";
	private ValueComparator valueCom = new ValueComparator(); 
	/**
	 * intial Money with value in double type and Currency type 
	 * @param value of Money
	 * @param currency of Money 
	 */
	public Money(double value,String currency) {
		if (value > 0){			
			this.value = value;
			this.currency = currency;
		}
		else
			System.out.println("value invalid");
	}
	/**
	 * intial Money with value in double type
	 * @param value of Money
	 */
	public Money(double value){
		if (value > 0){	
			this.value = value;
		}
		else
			System.out.println("value invalid");
	}

	/** to know value of Coin */
	public double getValue(){
		return value;
	}
	
	/** to know currency of Coin */
	public String getCurrency(){
		return currency;
	}
	
	public boolean equals(Object obj){
		// (1) verify that obj is not null
		if (obj == null) return false;
		// (2) test that obj is the same class as "this" object
		if ( obj.getClass() != this.getClass() )
		return false;
		// (3) cast obj to this class's type
		Valuable other = (Valuable) obj;
		// (4) compare whatever values determine "equal"
		if ( this.getCurrency().equals(other.getCurrency()) && this.getValue() == other.getValue() )
			return true;
		return false; 
	}
	
	public int compareTo(Valuable o){
        return valueCom.compare(this, o);
	}
	
	public abstract String toString();
}
