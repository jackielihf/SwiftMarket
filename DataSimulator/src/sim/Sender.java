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
	
	@Override
	public void run() {

		System.out.println("Sender start to run...");
		Cache cache = Cache.getInstance();
		while(flag){
			
			
			try {
//				writer.println("hello"+System.currentTimeMillis());
//				writer.flush();				
//				Thread.sleep(1000);
				
				//cache.wait(); //wait for new data
				Vector<Stock> info = cache.getCache(); //read data from cache
				
				String data = "";
				for(int i=0;i<info.size();i++){
					Stock st = info.get(i);
					data+=st.name+" "+st.price+" "+ st.volume+ " "+ st.timeStr+"\n";
				}
				
				//send data to the socket client
				writer.write(data);
				writer.flush();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		onClose();
		
	}
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
