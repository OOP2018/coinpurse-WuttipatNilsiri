package coinpurse;
/**
 * Thai Money Factory will defaul currency Ringgit
 * @author Wuttipat nilsiri
 *
 */
public class ThaiMoneyFactory extends MoneyFactory {
	final private String CURRENCY = "Baht";
	int countgen = 99999999;
	
	public ThaiMoneyFactory(){}
	/**
	 * Create Money if value more than 20 it will create banknote 
	 */
	@Override
	public Valuable createMoney(double value) {
		if (value >= 20){
			countgen++;
			return new BankNote(value,CURRENCY,countgen);
		}
		else 
			return new Coin(value,CURRENCY);
	}
	/**
	 * to know defaul currency Ringgit
	 */
	public String getCurrecy(){
		return CURRENCY;
	}

}
