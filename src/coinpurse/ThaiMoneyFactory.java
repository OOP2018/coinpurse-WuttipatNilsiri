package coinpurse;

public class ThaiMoneyFactory extends MoneyFactory {
	final private String CURRENCY = "Baht";
	int countgen = 99999999;
	
	public ThaiMoneyFactory(){}
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
