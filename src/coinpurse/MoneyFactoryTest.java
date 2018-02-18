package coinpurse;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * Test the Purse using JUnit.
 * This is a JUnit 4 test suite.  
 * 
 * IDEs (Eclipse, Netbeans, IntelliJ, BlueJ) include JUnit 4,
 * but you have to tell the IDE to add it to your project as a "Library".
 * To run these tests, right click on this file (in Project panel)
 * and choose Run As -> JUnit test
 * 
 * @author  Wuttipat nilsiri
 */
public class MoneyFactoryTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
	private MoneyFactory mf;
	
    /**
     * Sets up the test fixture.
     * Called before every test method.
     */
    public void testSetandgetInstance() {
    	MoneyFactory.setMoneyFactory(new ThaiMoneyFactory());
    	mf = MoneyFactory.getInstance();
    }
    
    

    /** Insert some coins. Easy test. */
    @Test
    public void testInsert()
    {
    	testSetandgetInstance();
        Purse purse = new Purse(3);
        assertTrue( purse.insert(mf.createMoney(5)));
        assertTrue( purse.insert(mf.createMoney("10")));
        assertTrue( purse.insert(mf.createMoney(20)));
        assertEquals( 3, purse.count() );
        // purse is full so insert should fail
        assertFalse( purse.insert(mf.createMoney(1000)) );

    }



    @Test(timeout=1000)
    public void testIsFull()
    {   // borderline case (capacity 1)
    	testSetandgetInstance();
        Purse purse = new Purse(1);
        assertFalse( purse.isFull() );
        purse.insert( mf.createMoney(1) );
        assertTrue( purse.isFull() );
        // real test
        int capacity = 4;
        purse = new Purse(capacity);
        for(int k=1; k<=capacity; k++) {
            assertFalse(purse.isFull());
            purse.insert( mf.createMoney(k) );
        }
        // should be full now
        assertTrue( purse.isFull() );
        assertFalse( purse.insert( mf.createMoney(5) ) );
    }

	/** Should be able to insert same coin many times,
	 *  since spec doesn't say anything about this.
	 */
	@Test(timeout=1000)
	public void testInsertSameCoin()
	{
		testSetandgetInstance();
		int capacity = 5;
		double value = 10.0;
		Purse purse = new Purse(capacity);
		assertTrue( purse.insert(mf.createMoney(value)) );
		assertTrue( purse.insert(mf.createMoney(value)) ); // should be allowed
		assertTrue( purse.insert(mf.createMoney(value)) ); // should be allowed
		assertTrue( purse.insert(mf.createMoney(value)) ); // should be allowed
		assertTrue( purse.insert(mf.createMoney(value)) ); // should be allowed
		assertEquals( purse.getBalance(), 5*value, TOL);
	}

	/** Add one coin and remove it. */
	@Test(timeout=1000)
	public void testEasyWithdraw() {
		testSetandgetInstance();
		Purse purse = new Purse(10);
		double [] values = {1, 20, 0.5, 10}; // values of coins we will insert
		
		for(double value : values) {
			Valuable coin = mf.createMoney(value);
			assertTrue(purse.insert(coin));
			assertEquals(value,  purse.getBalance(), TOL);
			Valuable [] result = purse.withdraw(value);
			assertTrue( result != null );
			assertEquals( 1, result.length );
			assertSame(  coin, result[0] ); // should be same object
			assertEquals( 0, purse.getBalance(), TOL );
		}
	}
	

	/** Add 4 coins and then withdraw in pairs, but not in same order. */
	@Test(timeout=1000)
	public void testMultiWithdraw() {
		testSetandgetInstance();
		Purse purse = new Purse(10);
		Valuable[] coins = { mf.createMoney(5.0), mf.createMoney(10.0), mf.createMoney(1.0), mf.createMoney(5.0) };
		// insert them all
		for(Valuable coin: coins) assertTrue( purse.insert(coin) );
		
		double amount1 = coins[1].getValue() + coins[3].getValue();
		double amount2 = coins[0].getValue() + coins[2].getValue();
		assertEquals(amount1+amount2, purse.getBalance(), TOL );
		
		Valuable [] wd1 = purse.withdraw(amount1);
		assertEquals(amount1, sum(wd1), TOL );
		
		assertEquals(amount2, purse.getBalance(), TOL );
		Valuable [] wd2 = purse.withdraw(amount2);
		
		// should be empty now
		assertEquals(0, purse.getBalance(), TOL );
	}
	

	/** Withdraw full amount in purse, using varying numbers of objects. */
	@Test(timeout=1000)
	public void testWithdrawEverything() {
		testSetandgetInstance();
		Purse purse = new Purse(10);
		// Coins we want to insert and then withdraw.
		// Use values such that greedy will succeed, but not monotonic
		List<Valuable> coins = Arrays.asList(
				mf.createMoney(1.0), mf.createMoney(0.5), mf.createMoney(10.0), mf.createMoney(0.25), mf.createMoney(5.0)
				);
		// num = number of coins to insert and then withdraw
		for(int num=1; num <= coins.size(); num++) {
			double amount = 0.0;
			List<Valuable> subList = coins.subList(0, num);
			for(Valuable c: subList) {
				purse.insert(c);
				amount += c.getValue();
			}
			// balance should be exactly what we just inserted
			assertEquals( amount, purse.getBalance(), TOL);
			// can we withdraw it all?
			Valuable[] result = purse.withdraw(amount);
			String errmsg = String.format("couldn't withdraw %.2f but purse has %s",
					amount, Arrays.toString(subList.toArray()) );
			assertNotNull( errmsg, result );
			// is the amount correct?
			assertEquals("Withdraw wrong amount", amount, sum(result), TOL);
			// should not be anything left in the purse
			assertEquals( 0.0, purse.getBalance(), TOL);
		}
	}
	
	@Test(timeout=1000)
	public void testSatang(){
		testSetandgetInstance();
		Purse p = new Purse(3);
		p.insert(mf.createMoney(0.25));
		p.insert(mf.createMoney("0.5"));
		for (Valuable money : p.getMoney()){
			assertEquals("Baht", money.getCurrency());
		}
	}
	
	@Test(timeout=1000)
	public void testSington(){
		testSetandgetInstance();
		MoneyFactory mf1 = MoneyFactory.getInstance();
		MoneyFactory mf2 = MoneyFactory.getInstance();
		assertTrue(mf1 == mf2);
	}
	
	@Test(timeout=1000)
	public void testSerial(){
		testSetandgetInstance();
		Valuable money1 = mf.createMoney(20);
		Valuable money2 = mf.createMoney("20");
		
		assertNotEquals(money1.toString(), money2.toString());
	}
	
	@Test(timeout=1000)
	public void testImpossibleWithdraw() {
		testSetandgetInstance();
		Purse purse = new Purse(10);
		assertNull( purse.withdraw(1) );
		purse.insert( mf.createMoney(20) );
		assertNull( purse.withdraw(1) );
		assertNull( purse.withdraw(19) );
		assertNull( purse.withdraw(21) );
		purse.insert( mf.createMoney(20) ); // now it has 20 + 20
		assertNull( purse.withdraw(30) );
	}
	
	/**
	 * Sum the value of some coins.
	 * @param wd1 array of coins
	 * @return sum of values of the coins
	 */
	private double sum(Valuable[] wd1)  {
		testSetandgetInstance();
		if (wd1 == null) return 0.0;
		double sum = 0;
		for(Valuable c: wd1) if (c != null) sum += c.getValue();
		return sum;
	}
}