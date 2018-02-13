package coinpurse;

public abstract class Money implements Valuable{
	private double value;
	private String currency = "Baht";
	
	public Money(double value,String currency) {
		if (value > 0){			
			this.value = value;
			this.currency = currency;
		}
		else
			System.out.println("value invalid");
	}
	
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
        if(this.value < o.getValue()){
            return -1;
        }
        else if(this.value > o.getValue()){
            return 1;
        }
        else{
            return 0;
        }
	}
	
	public abstract String toString();
}
