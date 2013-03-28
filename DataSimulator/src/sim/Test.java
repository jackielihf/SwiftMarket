package sim;

import java.util.*;
import java.math.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Calendar ca = Calendar.getInstance();
		String str = ca.getTime().toString();
		
		System.out.println(str);
		
		System.out.println(Math.random());
		
		Stock st = new Stock("1001","sany",10,0,"today");
		
		change(st);
		System.out.println(st.price);

	}
	public static void change(Stock st){
		st.price = 20.0;
	}

}
