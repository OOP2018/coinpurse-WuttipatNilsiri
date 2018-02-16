package coinpurse;

public abstract class MoneyFactory {
	static MoneyFactory instance = null;
	final static MoneyFactory defualFactory = new ThaiMoneyFactory();
	
	
	/**
	 * getInstance factory
	 * @return MoneyFactory 
	 */
	public static MoneyFactory getInstance(){
		if (instance == null){
			System.out.println("plz get money factory");
			setMoneyFactory(defualFactory);
		}
		return instance;
	}
	/**
	 * set MoneyFactory 
	 * @param mf MoneyFactory that want to set
	 */
	public static void setMoneyFactory(MoneyFactory mf){
		instance = mf;
	}
	/**
	 * 
	 * Create Money with condition to create banknote instead coin 
	 * @param value of Money
	 * @return Valuable of the money
	 */
	public abstract Valuable createMoney(double value);
	/**
	 * to know Currency of Country
	 * @return Currency of Country
	 */
	public abstract String getCurrecy();
	/**
	 * 
	 * Create Money with condition to create banknote instead coin 
	 * @param value of Money
	 * @return Valuable of the money
	 */
	public Valuable createMoney(String value){
		double parsedvalue = Double.parseDouble(value);
		return createMoney(parsedvalue);
	}
}
