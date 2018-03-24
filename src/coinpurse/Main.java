package coinpurse;

import java.util.ResourceBundle;

import strategy.RecursiveWithdraw;

/**
 * A main class to create objects and connect objects together.
 * The user interface needs a reference to coin purse.
 * @author wuttipat nilsiri
 */
public class Main {

    /**
     * Configure and start the application.
     * @param args not used
     */
    public static void main( String[] args ) {
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
    	
        // 1. create a Purse
    	Purse purse = new Purse(10);
    	purse.setStr(new RecursiveWithdraw());
        // 2. create a ConsoleDialog with a reference to the Purse object
    	ConsoleDialog ui = new ConsoleDialog(purse);
        // 3. run the ConsoleDialog
    	ui.run();

    }
}
