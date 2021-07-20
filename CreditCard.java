import java.util.Calendar;
import java.util.Random;


public class CreditCard{
	
	
    //use me for generating random numbers!
    private static final Random rand = new Random();
    
    private String accountName;
    private static int generalaccountNum = 3000;
    private int accountNum;
    private int CVV = rand.nextInt(1000);
    private double Balance;
    private double creditLimit;
    private double defaultLimit = 500;
    private int expirationMonth;
    private int expirationYear;
    
    public CreditCard(String accountName, int expirationMonth,
    	int expirationYear, double creditLimit) {
    this.accountName = accountName;
    	accountNumSetter();
    	if (creditLimit <= 0) {
    		this.creditLimit = defaultLimit;
    	}
    	else {
    		this.creditLimit = creditLimit;
    	}
    	dateHelper(expirationMonth, expirationYear);
    }
    
    public CreditCard(String accountName, int expirationMonth, int expirationYear) {
    	this.accountName = accountName;
    	accountNumSetter();
    	dateHelper(expirationMonth, expirationYear);
    	this.creditLimit = defaultLimit;
    	
    }
    
    public CreditCard(String accountName) {
    		this.accountName = accountName;
    		accountNumSetter();
    		this.expirationMonth = getTodaysMonth();
    		this.expirationYear = getTodaysYear() + 5;
    		this.creditLimit = defaultLimit;
    		
    }
    
    // Prints the information about the Card
    public String toString() {
    	return accountName + " (#" + accountNum + "): Current Balance = $"
    	+ Balance+ "/$" + creditLimit + ",  expires: " + expirationMonth + "/" +
    	expirationYear;
    }
    
    //sets the new account number
    public void accountNumSetter() {
    		accountNum = generalaccountNum;
    		generalaccountNum++;
    }
    // helps assign the expiration date of the new CC
    public void dateHelper(int expirationMonth, int expirationYear) {
    	if ((expirationYear >= (getTodaysYear() + 10)
    	|| expirationYear <= (getTodaysYear() - 10))
    	|| (expirationMonth < 0 || expirationMonth > 12)) {
    		this.expirationMonth = getTodaysMonth();
    		this.expirationYear = getTodaysYear() + 5;
    		}
    	else {
    		this.expirationMonth = expirationMonth;
    		this.expirationYear = expirationYear;
    	}
    }
    
    //Checks if Credit Card is expired or not
    public boolean isnotExpired(int expirationMonth, int expirationYear) {
    	if ((expirationMonth > getTodaysMonth() && expirationYear == getTodaysYear())
    		|| (expirationYear > getTodaysYear())) {
    	return true;
    		}
    		return false;
    }
    
    //Makes a charge if possible within the card
    public boolean makeCharge(double amount, int cvv) {
    	boolean charge = (CVV == cvv &&
    		amount > 0 && Balance + amount < creditLimit);
    	boolean valid = isnotExpired(this.expirationMonth, this.expirationYear);
    	if (charge && valid) {
    		Balance += amount;
    		return true;
    	}
    	return false;
    }
    
    public boolean makePayment(double amount) {
    	boolean payment = amount > 0 && Balance - amount > 0;
    	if (payment) {
    		Balance -= amount;
    		return true;
    	}
    	return false;
    }
    
    public void extendExpiration(int months) {
    	int years = months / 12;
    	
    		if (months > 0) {
    			this.expirationMonth = expirationMonth + (months % 12);
    			this.expirationYear = expirationYear + years;
    		}
    }
    
    //Looksup the CVV value if given credentials are valid.
    public static int lookupCVV(CreditCard card, String name, int accountNumber) {
    		boolean check = card.accountName == name &&
    		card.accountNum == accountNumber;
    		if (check) {
    			return card.CVV;
    		}
    		return -1;
    }
    
    //helper for seeing which date is the earlier one
    public static CreditCard earlierDate(CreditCard c1, CreditCard c2) {
    		if (c1.expirationYear > c2.expirationYear) {
    			return c2;
    		}
    		else if (c1.expirationYear < c2.expirationYear) {
    			return c1;
    		}
    		else {
    			if (c1.expirationMonth > c2.expirationMonth) {
    				return c2;
    			}
    			else {
    				return c1;
    			}
    		}
    }
    
    //destroys the merging cards and sets primitives to -1
    public static void destroyMerge(CreditCard c1, CreditCard c2) {
    		c1.accountName = "Merged with #" + c2.accountNum;
    		c2.accountName = "Merged with #" + c1.accountNum;
    		c1.Balance=-1; c2.Balance=-1;
    		c1.creditLimit=-1; c2.creditLimit=-1;
    		c1.expirationMonth=-1; c2.expirationMonth=-1;
    		c1.expirationYear=-1; c2.expirationYear=-1;
    		c1.CVV=-1; c2.CVV=-1;
    }
    //Merges cards and sets the others to waste
    public static CreditCard mergeCards(CreditCard c1, CreditCard c2) {
    		double newbal = c1.Balance + c2.Balance;
    		double newlimit = c1.creditLimit + c2.creditLimit;
    		String newname = c1.accountName + " and " + c2.accountName;
    		CreditCard cDate = earlierDate(c1, c2);
    		int newmonth = cDate.expirationMonth, newyear = cDate.expirationYear;
    		CreditCard c3 = new CreditCard(newname, newmonth, newyear, newlimit);
    		c3.Balance = newbal;
    		destroyMerge(c1, c2);
    		return c3;
    }
    		
    		
    		
    
    
    //***   Try us!  See what these methods' returned values look like!  ****
    
    public static int getTodaysMonth(){
    	Calendar currentDate = Calendar.getInstance();
    	return currentDate.get(currentDate.MONTH) + 1;
    }
    
    public static int getTodaysYear(){
    	Calendar currentDate = Calendar.getInstance();
    	return currentDate.get(currentDate.YEAR);
    }
    //************************************************************************
    // other accessor methods
    public String getName() {
    	this.accountName = accountName;
    	return accountName;
    }
    
    public double getCreditLimit() {
    	this.creditLimit = creditLimit;
    	return creditLimit;
    }
    
    public int getExpireMonth() {
    	this.expirationMonth = expirationMonth;
    	return expirationMonth;
    }
    
    public int getExpireYear() {
    	this.expirationYear = expirationYear;
    	return expirationYear;
    }
    
    public double getBalance() {
    	this.Balance = Balance;
    	return Balance;
    }
    
    
    
    
    
    
}
