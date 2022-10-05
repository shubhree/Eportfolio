package ePortfolio;
/**
* Represents a stock
* @author Shubhreet
* @author shubhree@uoguelph.ca
*/
public class Stock{

    //Fields
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;

    /**
    * Fully Parameterized Constructor for Stock
    *@param symbol 
    *@param name
    *@param quantity 
    *@param price
    */
    public Stock(String symbol, String name, int quantity, double price){          //Parameterized Constructor
        this.symbol   = symbol;
        this.name     = name;
        this.quantity = quantity;
        this.price    = price;
    }
    /**
    * Constructor for stock
    * @param symbol 
    */
    public Stock(String symbol){

        this(symbol, "", 0, 0.00);
    }


    /**
    *getter 
    *@return symbol
     */    
    public String getSymbol(){     //Getters
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

    public double getPrice(){
        return price;
    }


    /**
    *setter
    *@param symbol 
    */
    
    public void setSymbol(String symbol){             //Setters
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
    * this function returns the calculated price by multiplying the quantity into price of each stock
    *
    * @return the calculated price
     */
    public double getBookValue(){                           //Other Object Methods
        return  this.quantity * this.price + 9.99;
    }

/**
    * this function returns the calculated profit
    *
    * @return the calculated profit
    */
    public double getProfit(){
        return getBookValue() - 9.99 - this.bookValue;
    }

/**
* details for buying of the stocks
*
* @param  quantity  quantity of stocks bought
* @param  price     price at which stocks are bought
 */
    public void buy(int quantity, double price){             //Processing methods
        this.quantity += quantity;
        this.price = price;
        this.bookValue = getBookValue();
        return;
    }

/**
* returns selling amount of the stock
* 
* @param  quantity  quantity of stocks sold
* @param  price     price at which stocks are sold
* @return           amount at which stocks are sold
 */
    public double sell(int quantity, double price){
        this.price = price;
        double amount = quantity * this.price - 9.99;
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
* checks whether a stock exists already
* 
* @return boolean expression
 */
    public boolean equals(Object obj){
        Stock stock = (Stock) obj;
        return this.symbol.equals(stock.getSymbol()) &&
               this.name.equals(stock.getName()) &&
               Double.compare(this.price,stock.getPrice()) == 0 &&
               Integer.compare(this.quantity,stock.getQuantity()) == 0;
    }
}
