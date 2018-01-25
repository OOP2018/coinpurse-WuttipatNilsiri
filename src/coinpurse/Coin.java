package coinpurse;
/**
 * Coin Class contain basic method for coin
 * @author wuttipat nilsiri
 *
 */
public class Coin implements Comparable<Coin>{
	
	private double value;
	private String currency;
	
	/**
	 * Create new Coin
	 * @param value
	 * @param currency
	 */
	public Coin(double value,String currency){
		if (value > 0){
			this.value = value;
			this.currency = currency;
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
	
	/**
	 * Create Coin with only currency
	 * @param value of coin
	 * @param curr currency of coin
	 */
	public Coin(double value){
		this.value = value;
		currency = "bath";
	}
	
	public String toString(){
		return value+"-"+currency+" coin";
	}
	
	/** test whatever this coin is equals object arg */
	@Override
	public boolean equals(Object obj){
		// (1) verify that obj is not null
		if (obj == null) return false;
		// (2) test that obj is the same class as "this" object
		if ( obj.getClass() != this.getClass() )
		return false;
		// (3) cast obj to this class's type
		Coin other = (Coin) obj;
		// (4) compare whatever values determine "equal"
		if ( this.getCurrency().equals(other.getCurrency()) && this.getValue() == other.getValue() )
			return true;
		return false; 
	}
//		Coin castedArg = null;
//		if(arg != null){
//			if(arg instanceof Coin){
//				castedArg = (Coin) arg;
//			}
//			else 
//				return false;
//			
//		}
//		else
//			 return false;
//		
//		return this.value == castedArg.value 
//				&& this.currency.equals(castedArg.currency);
//	}
	
	/** compare by value */
	@Override
////	public int compareTo(Coin arg0) {
//		return (int) ((this.value - arg0.getValue())*1000);
//		
//	}
	public int compareTo(Coin coin){
        if(this.value < coin.getValue()){
            return -1;
        }
        else if(this.value > coin.getValue()){
            return 1;
        }
        else{
            return 0;
        }
	}

	
}