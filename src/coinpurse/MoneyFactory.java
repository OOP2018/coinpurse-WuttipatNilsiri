package coinpurse;

public abstract class MoneyFactory {
	static MoneyFactory instance = null;
	final static MoneyFactory defualFactory = new ThaiMoneyFactory();
	
	
	
	public static MoneyFactory getInstance(){
		if (instance == null){
			System.out.println("plz get money factory");
			setMoneyFactory(defualFactory);
		}
		return instance;
	}
	public static void setMoneyFactory(MoneyFactory mf){
		instance = mf;
	}
	public abstract Valuable createMoney(double value);
	
	public abstract String getCurrecy();
	
	public Valuable createMoney(String value){
		double parsedvalue = Double.parseDouble(value);
		return createMoney(parsedvalue);
	}
}
