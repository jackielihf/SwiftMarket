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
	
	private static int tcpPort = 9000;  //default tcp server port
	
	public Simulator(){}
	
	public void start(){
		produceService = new Thread(new Producer());
		tcpService = new Thread(new MySocketServer(tcpPort));
		
		produceService.start();  //start data producer
		tcpService.start();  //start data sending service
	}
	public void stop(){
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//process arguments
		for(int i=0;i<args.length;i++){
			String opt = args[i].trim().toLowerCase();
			//tcp port
			if(opt.equals(new String("-p"))){
				if(i+1<args.length){
					tcpPort = Integer.parseInt(args[i+1]);
				}
			}
		}
		
		//start services
		System.out.println("data simulator started...");
		new Simulator().start();
		
		
		//for test
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
