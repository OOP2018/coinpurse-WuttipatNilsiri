package coinpurse;
/**
 * Coin Class contain basic method for coin
 * @author wuttipat nilsiri
 *
 */
public class Coin extends AbstractValuable{
		
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
		return getValue()+"-"+getCurrency()+" coin";
	}
	
}