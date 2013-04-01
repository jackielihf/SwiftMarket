/**
 * 
 */
package sim;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author jackie
 *
 */
public class Sender implements Runnable {

	
	private Socket socket;
	public boolean flag = true;
	public PrintWriter writer;
	public boolean hasSendAll = false;
	
	private Sender(){}
	public Sender(Socket _socket){
		socket = _socket;
		//get outputStream
		try {
			writer = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * send data of all the stocks to client 
	 */
	public boolean sendAllStocks(Cache cache)
	{
		Vector<Stock> allStocks = cache.getAllStocks();
		if(allStocks.size()<1) return false;
		synchronized(allStocks){
			String data = MyJson.someStocksJson("all", allStocks)+"\n";
			writer.write(data);
			writer.flush();
		}
		return true;
	}
	
	@Override
	public void run() {

		System.out.println("a sender working with a socket starts to run ...");
		
		Cache cache = Cache.getInstance(); //get cache object
		
		//at the begin of connection, send info of all the stocks
		while(!hasSendAll){
			hasSendAll = sendAllStocks(cache);
			if(!hasSendAll){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		//send data to clients when new data comes
		while(flag){
			
			
			try {
				
				Vector<Stock> info;
				synchronized(cache){					
					cache.wait(); //wait for new data
					info = cache.getUpdateCache(); //read the newest data from cache
				}
				
				//
				String data = "";
				//transfer to JSON String
				data = MyJson.someStocksJson("update", info)+"\n";
				
				//send data to the socket client
				writer.write(data);
				writer.flush();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		onClose();
		
	}
	/**
	 * close stream and socket
	 */
	public void onClose(){
		if(writer!=null)
			writer.close();
		if(socket!=null){
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}


}
