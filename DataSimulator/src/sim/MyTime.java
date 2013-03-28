/**
 * 
 */
package sim;

import java.util.Calendar;

/**
 * @author jackie
 *
 */
public class MyTime {
	
	public static String getCurTimeStr(){
		Calendar ca = Calendar.getInstance();
		return ca.getTime().toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
