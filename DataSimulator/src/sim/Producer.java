/**
 * 
 */
package sim;

import java.util.*;
import java.math.*;
/**
 * @author jackie
 *
 */
public class Producer {

	public int totalStock = 10;     //the number of stocks
	public int interval = 1000;     //interval to produce
	public double pp = 0.5;          //probability of producing data for a stock
	public double deltaPrice = 0.1;
	public int deltaVolume = 1000;
	
	public Vector<Stock> allStocks; //all the stocks
	
	public Producer()
	{
		allStocks = new Vector<Stock>();
	}
	/**
	 * create some stock objects
	 */
	private void createStocks()
	{
		String code = "6000";
		String name = "能源金融";
		String startTime = MyTime.getCurTimeStr(); 
		
		for(int i=0;i<totalStock;i++){
			allStocks.add(new Stock(code+i,name+i,10,0,startTime)); //10 RMB,0 volume
		}
	}
	
	/**
	 * produce some trade data randomly
	 * @return 
	 */
	public Vector<Stock> produce()
	{
		Vector<Stock> infos = new Vector<Stock>();
		for(int i=0;i<allStocks.size();i++){
			Stock st = allStocks.get(i);
			if(Math.random()>pp){
				//change the trading info of the stock
				Stock info = changeInfo(st);
				infos.add(info);
			}
		}
		return infos;
	}
	
	/**
	 * change trade info 
	 * @return
	 */
	public Stock changeInfo(Stock st){
				
		st.price += (Math.random()-0.5)*deltaPrice*st.price;
		st.volume = (int)(Math.random()*deltaVolume);
		st.timeStr = MyTime.getCurTimeStr();		
		return st;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Producer producer = new Producer();
		producer.createStocks();
		Vector<Stock> list =producer.allStocks; 
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).code+" "+list.get(i).name);
		}

		producer.createStocks();
		for(int i=0;i<10;i++)
		{
			Vector<Stock> info = producer.produce();
			for(int j=0;j<info.size();j++){
				Stock st = info.get(j);
				System.out.println(st.name+" "+st.price+" "+ st.volume+ " "+ st.timeStr);
			}
		}
	}

}
