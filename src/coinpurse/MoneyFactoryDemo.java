package coinpurse;



public class MoneyFactoryDemo {
	
	public static void main(String[] args) {
		MoneyFactory.setMoneyFactory(new ThaiMoneyFactory());
		MoneyFactory mf = MoneyFactory.getInstance();
		
		
		MoneyFactory.setMoneyFactory(mf);
		Purse p = new Purse(5);
		p.insert(mf.createMoney(0.25));
		p.insert(mf.createMoney(1));
		p.insert(mf.createMoney(50));
		p.insert(mf.createMoney(0.5));
		p.insert(mf.createMoney("20"));
		for (Valuable money : p.getMoney()) {
				System.out.println(money);
		}
		System.out.println("--------------------");
		
		MoneyFactory mf1 = MoneyFactory.getInstance();
		MoneyFactory mf2 = MoneyFactory.getInstance();
		System.out.println(mf1.equals(mf2));
		
	}
}