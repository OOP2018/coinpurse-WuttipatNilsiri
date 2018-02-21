package coinpurse;
/**
 * Coin Class contain basic method for coin
 * @author wuttipat nilsiri
 *
 */
public class Coin extends Money{
	final String malacurr = "Ringgit";
	final String thaicurr = "Baht";
	/**
	 * Create new Coin
	 * @param value
	 * @param currency
	 */
	Coin(double value,String currency){
		super(value,currency);
	}
	
	/**
	 * Create Coin with only currency
	 * @param value of coin
	 * @param curr currency of coin
	 */
	public Coin(double value){
		super(value);
	}

	@Override
	public String toString(){
		if (getCurrency().equals(thaicurr) && getValue() < 1) return getValue()*100+"-"+"Stang"+" coin";
		if (getCurrency().equals(malacurr) && getValue() < 1) return getValue()*100+"-"+"Sen"+" coin";	
		else return getValue()+"-"+getCurrency()+" coin";
		
	}
	//not use now
	public String toString(MoneyFactory mf){
		if (getValue() < 1) return getValue()*100+"-"+mf.getSubCurrecy()+" coin";
		else return getValue()+"-"+getCurrency()+" coin";
	}
	
}