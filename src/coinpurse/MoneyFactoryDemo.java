package coinpurse;

import java.util.ResourceBundle;

public class MoneyFactoryDemo {
	
	public static void main(String[] args) {
		MoneyFactory mf = MoneyFactory.getInstance();
		ResourceBundle bundle = ResourceBundle.getBundle("purse");
		String classFactory = bundle.getString("moneyfactory");
		
		try {
			mf = (MoneyFactory) Class.forName(classFactory).newInstance();
		} catch (ClassCastException e) {
			System.out.println(classFactory + " is not type MoneyFactory");
		} catch (Exception e) {
			System.out.println("Error creating MoneyFactory " + e.getMessage());
		}
		
		if (mf == null)
			System.exit(1);
		else
			MoneyFactory.setMoneyFactory(mf);
		
//		MoneyFactory.setMoneyFactory(new ThaiMoneyFactory());
//		MoneyFactory mf = MoneyFactory.getInstance();
		
		
		MoneyFactory.setMoneyFactory(mf);
		Purse p = new Purse(5);
		p.insert(mf.createMoney(0.25));
		p.insert(mf.createMoney(1));
		p.insert(mf.createMoney(50));
		p.insert(mf.createMoney(45));
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