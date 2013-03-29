/**
 * 
 */
package sim;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jackie
 *
 */
public class MySocketServer implements Runnable{
	
	
	private ServerSocket serverSocket;
	private ExecutorService executorService;   //thread pool
	private int poolSize = 5;                  //the size of thread pool
	public int port = 9000;                    //listen port 
	//public int interval = 1000;
	
	public MySocketServer(){
		try {
			serverSocket = new ServerSocket(port);
			executorService = Executors.newFixedThreadPool(poolSize);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		
		
		while(true){
			Socket socket = null;
			try {
				
				socket = serverSocket.accept(); //listen and accept when a client connects server
				System.out.println("SocketServer accepts a connection");
				executorService.execute(new Sender(socket)); //handle the connection
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		new Thread(new Producer()).start();
		new Thread(new MySocketServer()).start();
		try {
			Socket client = new Socket("127.0.0.1",9000);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while(true){
				String line = br.readLine();
				
				if(line!=null) System.out.println(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}


}
