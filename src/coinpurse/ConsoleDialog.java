package coinpurse;
 
import java.util.Scanner;

/** 
 * User Interface for the Coin Purse. 
 * This class provides simple interactive dialog for inserting
 * and removing money to/from the purse, and displaying the
 * balance.
 */
public class ConsoleDialog {
	// default currency for this dialog
	public static final String CURRENCY = "Baht";
    // use a single java.util.Scanner object for reading all input
    private static Scanner console = new Scanner( System.in );
    // Long prompt shown the first time
    final String FULL_PROMPT = "\nEnter d (deposit), w (withdraw), ? (inquiry), or q (quit): ";
    // Shorter prompt shown subsequently
    final String SHORT_PROMPT = "\nEnter d, w, ?, or q: ";
    
	// The dialog receives a Purse object by dependency injection (as parameter to constructor)
    // so don't create a Purse here.
    private Purse purse;
    
    /** 
     * Initialize a new Purse dialog.
     * @param purse is the Purse to interact with.
     */
    public ConsoleDialog(Purse purse ) {
    	this.purse = purse;
    }
    
    /** Run the user interface. */
    public void run() {
        String choice = "";
        String prompt = FULL_PROMPT;
        loop: while( true ) {
            System.out.printf("Purse contains %s\n", purse.toString() );
            if ( purse.isFull() ) System.out.println("Purse is FULL.");
            // print a list of choices
            System.out.print(prompt);
            choice = console.next().trim().toLowerCase();
            prompt = SHORT_PROMPT;
 
            switch(choice) {
            case "d":
            case "deposit":
            	depositDialog();
            	break;
            case "w":
            case "withdraw":
            	withdrawDialog();
            	break;
            case "?":
            	System.out.println( purse.toString() );
            	break;
            case "q":
            	break loop; // leave the while loop
            default:
            	System.out.println( "\""+choice+"\" is not a valid choice.");
            	prompt = FULL_PROMPT;
            }
        }
        System.out.println("Goodbye. The purse still has "+purse.getBalance()+" "+CURRENCY);
    }

    /** 
     * Ask the user how many coins to deposit into purse, then deposit them.
     * Show result of success or failure.
     * The user can type the values on same line as he typed "d", e.g. "d 5 10 1"
     * so check for that.
     */
    long count = 99999999;
    public void depositDialog() {
    	// Check to see if user typed values on the same line as "d".
    	// If so then use them without prompting for more.
    	String inline = console.nextLine().trim();
    	if (inline.isEmpty()) {
    		System.out.print("Enter value of coin(s) to deposit on one line [eg: 5 0.5 1]: ");
    		inline = console.nextLine();
    	}
        // parse input line into numbers
        Scanner scanline = new Scanner(inline);
        while( scanline.hasNextDouble() ) {
            double value = scanline.nextDouble();
            String currency = null;
            if (scanline.hasNextLine()){
            	currency = scanline.nextLine().trim();
            }
            Valuable money;
            if (currency != null){
            	if(value >= 20 ){
                	money = makeBankNote(value,currency);
                }
                else{
                	money = makeCoin(value,currency);
                }
            }
            else{
            	if(value >= 20 ){
                	money = makeBankNote(value);
                }
                else{
                	money = makeCoin(value);
                }
            }
            System.out.printf("Deposit %s... ", money.toString() );
            boolean ok = purse.insert(money);
            System.out.println( (ok? "ok" : "FAILED") );
        }
        if ( scanline.hasNext() )
            System.out.println("Invalid input: "+scanline.next() );
        scanline.close();
    }
    
    /** Ask how much money (Baht) to withdraw and then do it.
     *  After withdraw, show the values of the coins we withdrew.
     */
    public void withdrawDialog() {
    	// Check to see if user typed amount to withdraw on the same line as "w".
    	// If so then use that value without prompting.
    	String inline = console.nextLine().trim();
    	if (inline.isEmpty()) {
    		System.out.print("How much to withdraw? ");
    		inline = console.nextLine();
    	}
    	// get the amount
    	Scanner scanline = new Scanner(inline);
        
        if ( scanline.hasNextDouble() ) {
             double amount = scanline.nextDouble( );
             
             String currency = null;
             if (scanline.hasNextLine()){
             	currency = scanline.nextLine().trim();
             }
             Valuable [] money;
             Valuable amoutV;
             if (currency != null){
            	 amoutV =  new Banknote(amount,currency);
            	 money = purse.withdraw(amoutV);
             }
             else{
            	currency = CURRENCY;
            	amoutV =  new Banknote(amount,CURRENCY);
        	 	money = purse.withdraw(amoutV);
             }
             
             if ( money == null ) 
                System.out.printf("Sorry, couldn't withdraw %.2f %s\n", amount, currency);
             else {
                System.out.print("You withdrew:");
                for(int k=0; k<money.length; k++) {
                	System.out.print((k==0?" ":", ") + money[k].toString() );
                }
                System.out.println();
            }
        }
        else System.out.printf("Invalid amount: "+inline );
        scanline.close();
    }
    
    /** Make a Coin (or BankNote or whatever) using requested value. */
    private Valuable makeCoin(double value) {
    	return new Coin(value, CURRENCY);
    }
    
    private Valuable makeBankNote(double value) {
    	++count;
    	return new Banknote(value, CURRENCY,count);
    }
    
    private Valuable makeCoin(double value,String currency) {
    	return new Coin(value, currency);
    }
    
    private Valuable makeBankNote(double value,String currency) {
    	++count;
    	return new Banknote(value, currency,count);
    }
    

}
