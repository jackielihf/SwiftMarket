/**
 * Copy Right Reserve
 * Project:
 * JDK version: jdk1.7.0
 * Version: 1.0
 * Modification history:
 * @author jackie
 */
package sim;

import java.io.*;
import java.net.Socket;


/**
 * @author jackie
 * Description: data simulator, can be used to generate real-time data of stock market.
 */
public class Simulator {
	private Thread produceService;
	private Thread tcpService;
	
	public Simulator(){}
	
	public void start(){
		produceService = new Thread(new Producer());
		tcpService = new Thread(new MySocketServer());
		
		produceService.start();  //start data producer
		tcpService.start();  //start data sending service
	}
	public void stop(){
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("data simulator started...");
		
		new Simulator().start();
		
//		try {
//			Socket client = new Socket("127.0.0.1",9000);
//			
//			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
//			while(true){
//				String line = br.readLine();
//				
//				if(line!=null) System.out.println(line);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		

	}

}
