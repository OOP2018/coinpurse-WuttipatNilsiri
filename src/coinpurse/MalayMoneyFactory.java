package coinpurse;

public class MalayMoneyFactory extends MoneyFactory {

	final private String CURRENCY = "Ringgit";
	int countgen = 99999999;
	
	@Override
	public Valuable createMoney(double value) {
		if (value >= 20){
			countgen++;
			return new BankNote(value,CURRENCY,countgen);
		}
		else 
			return new Coin(value,CURRENCY);
	}
	
	public String getCurrecy(){
		return CURRENCY;
	}
	
}
