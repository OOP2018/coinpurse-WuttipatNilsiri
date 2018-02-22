package coinpurse;
/**
 * Thai Money Factory will default currency Ringgit
 * @author Wuttipat nilsiri
 *
 */
public class ThaiMoneyFactory extends MoneyFactory {
	final private String CURRENCY = "Baht";
	final private String SUBCURRENCY = "Stang";
	int countgen = 99999999;
	
	public ThaiMoneyFactory(){}
	/**
	 * Create Money if value more than 20 it will create banknote 
	 */
	@Override
	public Valuable createMoney(double value) {
		if(isBankNote(value)){
			countgen++;
			return new BankNote(value,CURRENCY,countgen);
		}
		else if(isCoin(value)){
			return new Coin(value,CURRENCY);
		}
		else 
			throw new IllegalArgumentException("invalid input" + value);
	
	}
	/**
	 * to know default currency Ringgit
	 */
	public String getCurrecy(){
		return CURRENCY;
	}
	@Override
	public String getSubCurrecy() {
		return SUBCURRENCY ;
	}
	
	boolean isBankNote(double value){
		return value == 20 || value == 50 || value == 100 || value == 500 || value == 1000;
	}
	
	boolean isCoin(double value){
		return value == 1 || value == 5 || value == 10 || value == 0.25 || value == 0.5;
	}

}
