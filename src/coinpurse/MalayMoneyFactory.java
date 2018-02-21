package coinpurse;
/**
 * Malay Money Factory will defaul currency Ringgit
 * @author Wuttipat nilsiri
 *
 */
public class MalayMoneyFactory extends MoneyFactory {

	final private String CURRENCY = "Ringgit";
	final private String SUBCURRENCY = "Sen";
	int countgen = 99999999;
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
	public String getSubCurrecy(){
		return SUBCURRENCY;
	}
	
}
