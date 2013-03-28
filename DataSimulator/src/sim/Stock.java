/**
 * 
 */
package sim;

/**
 * @author jackie
 *
 */
public class Stock {
	
	public String code;     //the code of stock
	public String name;     //the name of stock
	public double price;    //the current price of stock
	public int volume;      //the volume of a trading
	public String timeStr;  //the current time
	
	public Stock(){}
	public Stock(String co, String na,double pri,int vol, String t)
	{
		code = co;
		name = na;
		price = pri;
		volume = vol;
		timeStr = t;
	}

}
