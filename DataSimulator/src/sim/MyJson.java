/**
 * 
 */
package sim;

import java.util.Vector;

/**
 * @author jackie
 *
 */
public class MyJson {

	
	public static String stockJson(Stock st){
		String res = "{";
		res += "\"code\":"+ "\""+ st.code + "\",";
		res += "\"name\":"+ "\""+ st.name + "\",";
		res += "\"price\":"+ st.price + ",";
		res += "\"volume\":"+ st.volume + ",";
		res += "\"time\":"+ "\""+ st.timeStr + "\"";
		res += "}";
		return res;
	}
	/**
	 * 
	 * @param type = all or update
	 * @param list
	 * @return
	 */
	public static String someStocksJson(String type,Vector<Stock> list){
		String res = "{";
		//add "type" information
		res += "\"type\":" + "\"" + type +"\",";
		//stock array
		res += "\"content\":" + "[";
		for(int i=0;i<list.size()-1;i++){
			res += stockJson(list.get(i)) + ",";
		}
		if(list.size()>0) res += stockJson(list.get(list.size()-1));
		res += "]}";
		return res;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Stock st = new Stock("60000","BANK1",10.0,0,"9:30");
		System.out.println(stockJson(st));
		
		Vector<Stock> list = new Vector<Stock>();
		for(int i=0;i<3;i++){
			list.add(new Stock("6000"+i,"BANK"+i,10.0,0,"9:30"));
		}
		System.out.println(someStocksJson("all",list));

	}

}
