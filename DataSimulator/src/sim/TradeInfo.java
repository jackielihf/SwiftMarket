/**
 * 
 */
package sim;

import java.util.*;

/**
 * @author jackie
 *
 */
public class TradeInfo {
	public Stock target;    //target stock
	public double price;    //the price of stock
	public int volume;      //the volume of a trading
	public String timeStr;  //the current time
	public TradeInfo(){}
	public TradeInfo(Stock tar,double pri,int vol,String t){
		target = tar;
		price = pri;
		volume = vol;
		timeStr = t;
	}

}
