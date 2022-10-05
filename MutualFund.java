package ePortfolio;
/**
* Represents a mutual fund
* @author Shubhreet
* @author shubhree@uoguelph.ca
*/
public class MutualFund{

    //Fields
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
/**
* Constructor for MutualFund
* @param symbol 
 */
    
    public MutualFund(String symbol){               //Parameterized Constructors
        this(symbol, "", 0, 0.00);
    }
/**
* Fully Parameterized Constructor for MutualFund
*@param symbol 
*@param name
*@param quantity 
*@param price
 */
    public MutualFund(String symbol, String name, int quantity, double price){
        this.symbol   = symbol;
        this.name     = name;
        this.quantity = quantity;
        this.price    = price;
    }

    
    /**
    *getter 
    *@return Symbol
     */
    public String getSymbol(){              //Getters
        return this.symbol;
    }

    /**
    *getter 
    *@return name
    */
    public String getName(){
        return name;
    }

    /**
    *getter
    *@return quantity
    */
    public int getQuantity(){
        return quantity;
    }

    /**
    *getter
    *@return price
    */
    public double getPrice(){
        return price;
    }

    /**
    *setter
    *@param symbol 
    */
    public void setSymbol(String symbol){              //Setters
        this.symbol = symbol;
    }

    /**
    *setter
    *@param name 
    */
    public void setName(String name){
        this.name = name;
    }

    /**
    *setter 
    *@param quantity
    */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
    *setter 
    *@param price
    */
    public void setPrice(double price){
        this.price = price;
    }


    /**
    * this function returns the calculated price by multiplying the quantity into price of each mutual fund
    *
    * @return the calculated price
     */
    public double getBookValue(){             //Other Object Methods
        return  this.quantity * this.price;
    }

/**
    * this function returns the calculated profit
    *
    *@return the calculated profit
    */
    public double getProfit(){
        return getBookValue() - 45.0 - this.bookValue;
    }
/**
* details for buying of the mutual fund
*
* @param  quantity  quantity of mutual funds bought
* @param  price     price at which mutual funds are bought
 */

    public void buy(int quantity, double price){
        this.quantity += quantity;
        this.price = price;
        this.bookValue = getBookValue();

        return;
    }
/**
* returns selling amount of the mutual fund
* 
* @param  quantity  quantity of mutual funds sold
* @param  price     price at which mutual funds are sold
* @return           amount at which mutual funds are sold
 */
    public double sell(int quantity, double price){

        this.price = price;
        double amount = quantity * this.price - 45.00;
        this.quantity -= quantity;
        this.bookValue = this.bookValue * this.quantity / (this.quantity + quantity);

        return amount;
    }

/**
* returns the updated string 
*
* @return updated string
 */
    public String toString(){
        String result = "\n[";

        result += "\nSymbol   : " + symbol +
                  "\nName     : " + name   +
                  "\nQuantity : " + quantity +
                  "\nPrice    : " + price + "\n]";
        return result;
    }

/**
* checks whether a mutual fund exists already
* 
* @return boolean expression
 */
    public boolean equals(Object obj){

        MutualFund mutualFund = (MutualFund) obj;

        return this.symbol.equals(mutualFund.getSymbol()) &&
               this.name.equals(mutualFund.getName()) &&
               Double.compare(this.price, mutualFund.getPrice()) == 0 &&
               Integer.compare(this.quantity,mutualFund.getQuantity()) == 0;
    }

}
