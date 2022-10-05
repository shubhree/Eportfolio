package ePortfolio;
import ePortfolio.Stock;
import ePortfolio.MutualFund;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;

/**
* Represents the eportfolio
* @author Shubhreet
* @author shubhree@uoguelph.ca
*/
public class PortFolio{

    private static Scanner reader = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    
    /**
    *main method
     */
    public static void main(String[] args){

/** 
 * Keeps track of all the stocks in the eportfolio
 */  
        ArrayList<Stock> stocks = new ArrayList<Stock>();                       //arraylist for maintaining stocks

/** 
 * Keeps track of all the mutual funds in the eportfolio
 */  
        ArrayList<MutualFund> mutualFunds = new ArrayList<MutualFund>();        //arraylist for maintaining mutual funds

        int investment;

        Stock stock;
        MutualFund mutualFund;
        String symbol;
        String name;
        int quantity;
        double price;
        double payment;

        int index;
        int option;
        int element;

        System.out.println("\nePortFolio\n\n");


        do{
            option = choice();

            switch(option){
                case 1:
                    investment = getInvestment();
                    switch(investment){
                        case 1:
                            symbol = readWord("Enter Symbol Name : ");
                            if(!containsStock(stocks, symbol)){
                               // System.out.println("Reading New Stock");
                                stock = readStock(symbol);
                                stocks.add(stock);
                            }else{

                                stock = getStock(stocks, symbol);
                                index = stocks.indexOf(stock);

                                quantity = readInt("\nEnter the Quantity : ");
                                price    = readDouble("\nEnter the Price    : ");
                                stock.buy(quantity, price);

                                stocks.set(index, stock);
                            }
                            break;
                        case 2:
                            symbol = readWord("\nEnter Symbol Name : ");
                            if(!containsMutualFund(mutualFunds, symbol)){
                                mutualFund = readMutualFund(symbol);
                                mutualFunds.add(mutualFund);
                            }else{

                                mutualFund = getMutualFund(mutualFunds, symbol);
                                index = mutualFunds.indexOf(mutualFund);

                                quantity = readInt("\nEnter the Quantity : ");
                                price    = readDouble("\nEnter the Price    : ");
                                mutualFund.buy(quantity, price);

                                mutualFunds.set(index, mutualFund);
                            }
                    }
                    break;

                case 2:

                    symbol   = readWord("\nEnter Symbol Name     : ");
                    quantity = readInt("\nEnter the Quantity     : ");
                    price    = readDouble("\nEnter the Actual Price : ");


                    if(containsStock(stocks, symbol)){
                        stock = getStock(stocks, symbol);
                        index = stocks.indexOf(stock);

                        if(quantity > stock.getQuantity()){
                            stock = stocks.remove(index);
                            System.out.println("\nStock Removed : " + stock);
                        }else{
                            payment = stock.sell(quantity, price);
                            System.out.println("\nPayment Received : " + payment);
                            stocks.set(index, stock);
                        }

                    }else if(containsMutualFund(mutualFunds, symbol)){
                            mutualFund = getMutualFund(mutualFunds, symbol);
                            index = mutualFunds.indexOf(mutualFund);

                            if(quantity > mutualFund.getQuantity()){
                                mutualFund = mutualFunds.remove(index);
                                System.out.println("\nMutual Fund Removed : " + mutualFund);
                            }else{
                                payment = mutualFund.sell(quantity, price);
                                System.out.println("\nPayment Received : " + payment);
                                mutualFunds.set(index, mutualFund);
                            }
                    }else
                        System.out.println("\nAsset doesn't exist");
                    break;

                case 3:
                    investment = getInvestment();
                    symbol     = readString("\nEnter Symbol Name : ");
                    price      = readDouble("\nEnter New Price : ");

                    switch(investment){
                        case 1:
                            stock = getStock(stocks, symbol);
                            stock.setPrice(price);
                            stocks.set(stocks.indexOf(stock), stock);
                            break;

                        case 2:
                            mutualFund = getMutualFund(mutualFunds, symbol);
                            mutualFund.setPrice(price);
                            mutualFunds.set(mutualFunds.indexOf(mutualFund), mutualFund);
                            break;
                    }
                    break;
                case 4:
                    HashMap<String, Double> assetGains = new HashMap<String, Double>();
                    for(Stock s: stocks)
                        assetGains.put(s.getSymbol(), s.getProfit());

                    for(MutualFund m: mutualFunds)
                        assetGains.put(m.getSymbol(), m.getProfit());

                    System.out.println(assetGains);
                    break;
                case 5:
                    int key   = getSearchKey();
                    switch(key){
                        case 1:
                            name = readString("\nEnter the Name : ");

                            for(Stock s: stocks)
                                if(contains(s.getName(), name))
                                    System.out.println(s + "\n");

                            for(MutualFund m: mutualFunds)
                                if(contains(m.getName(), name))
                                    System.out.println(m + "\n");
                            break;
                        case 2:
                            double lowRange  = readDouble("\nEnter the Min Price : ");
                            double highRange = readDouble("\nEnter the Max Price : ");

                            for(Stock s:stocks){
                                price = s.getPrice();
                                if(price >= lowRange && price <= highRange)
                                    System.out.println(s + "\n");

                            }

                            for(MutualFund m: mutualFunds){
                                price = m.getPrice();
                                if(price >= lowRange && price <= highRange)
                                    System.out.println(m + "\n");
                            }
                    }
                    break;

            }
        }while(option != 6);


        System.out.println("\n\n");
        return;

    }

    /**
    *input from the user
    */
    public static int choice(){

/**
* gets input from the user
 */
        int choice;

        do{
            System.out.println("\n\t\tASSET MENU\n");

            System.out.println("\t01. Buy");
            System.out.println("\t02. Sell");
            System.out.println("\t03. Update");
            System.out.println("\t04. Get Gain");
            System.out.println("\t05. Search");
            System.out.println("\t06. Quit");

            System.out.print("\nEnter the choice(1-6) : ");
            choice = reader.nextInt();

           // reader.skip("\n");

        }while(choice <1 || choice > 6);

        return choice;
    }

/**
* asks the user what he wants to buy/update
* returns the int as per stock or mutual fund chosen
*
* @return what the user wants to buy/update
 */
    public static int getInvestment(){

        int choice;

        do{
            System.out.println("\n\t\tINVESTMENT MENU\n");

            System.out.println("\n\t01. Stock");
            System.out.println("\t02. MutualFund");

            System.out.print("\nEnter the choice(1-2) : ");
            choice = reader.nextInt();

            

        }while(choice <1 || choice > 2);
        //reader.skip("\n");
        return choice;
    }

    /**
    *@param prompt
    *@return String
    */
    public static String readWord(String prompt){
        //reader.skip(" ");
        System.out.print(prompt);
        return reader.next();
    }

    /**
    *@param prompt
    *@return String
    */    
    public static String readString(String prompt){
        
        //reader.skip(" ");
        reader.nextLine();
        System.out.print(prompt);
        return reader.nextLine();
    }

    /**
    *@param prompt
    *@return double
    */
    public static double readDouble(String prompt){
        System.out.print(prompt);
        return reader.nextDouble();
    }

    /**
    *@param prompt
    *@return int
    */
    public static int readInt(String prompt){
        System.out.print(prompt);
        return reader.nextInt();
    }

    /**
    * Read a Stock Object
    * @param symbol the symbol name for stock
    * @return stock object
    */
    public static Stock readStock(String symbol){
        String name;
        int quantity;
        double price;

        name = readString("\nEnter the Name : ");
        quantity = readInt("\nEnter the Quantity : ");
        price = readDouble("\nEnter the Price : ");

        return new Stock(symbol, name, quantity, price);
    }

    /**
    * Read a Mutual Fund Object
    * @param symbol the symbol name for stock
    * @return mutual fund Object
    */
    
    public static MutualFund readMutualFund(String symbol){
        String name;
        int quantity;
        double price;

        name = readString("\nEnter the Name : ");
        quantity = readInt("\nEnter the Quantity : ");
        price = readDouble("\nEnter the Price : ");

        return new MutualFund(symbol, name, quantity, price);
    }

/**
* @param  stocks arraylist
* @param  symbol      the symbol name for stock
* @return             if found, stock object
 */
    public static Stock getStock(ArrayList<Stock> stocks, String symbol){

        for(Stock stock : stocks)
            if(stock.getSymbol().equals(symbol))
                return stock;

        return null;
    }

/**
* @param  mutualFunds arraylist
* @param  symbol      the symbol name for mutual fund
* @return             if found, mutual fund object
 */
    public static MutualFund getMutualFund(ArrayList<MutualFund> mutualFunds, String symbol){

        for(MutualFund mutualFund: mutualFunds)
            if(mutualFund.getSymbol().equals(symbol))
                return mutualFund;

        return null;
    }

/**
* checks if stock exists
* @param stocks arraylist
* @param symbol the symbol name for stock
* @return           boolean expression
 */
    public static boolean containsStock(ArrayList<Stock> stocks, String symbol){

        for(Stock stock: stocks)
            if(stock.getSymbol().equals(symbol))
                return true;

        return false;
    }

/**
* checks if mutual fund exists
* @param mutualFunds arraylist
* @param symbol the symbol name for mutual fund
* @return           boolean expression
 */
    public static boolean containsMutualFund(ArrayList<MutualFund> mutualFunds, String symbol){

        for(MutualFund mutualFund: mutualFunds)
            if(mutualFund.getSymbol().equals(symbol))
                return true;

        return false;
    }

/**
* matches the string
* @param mainString whole string
* @param subString  string after splitting
* @return           boolean expression
 */
    public static boolean contains(String mainString, String subString){

        String   main = mainString.toLowerCase();
        String[] subs = subString.toLowerCase().split(" ");

        for(String sub:subs)
            if(!main.contains(sub))
                return false;

        return true;
    }

/**
* asks user how to search 
* @return what users choses bw name and price range
*/
    public static int getSearchKey(){

        int choice;

        do{
            System.out.println("\n\t\tSEARCH KEY\n");

            System.out.println("\t01. Name");
            System.out.println("\t02. Price Range");

            System.out.print("\nEnter the choice(1-2) : ");
            choice = reader.nextInt();

        }while(choice <1 || choice > 2);
        //reader.skip("\n");
        
        return choice;

    }

}
