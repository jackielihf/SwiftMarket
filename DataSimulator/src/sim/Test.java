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
		
		
		Vector<String> vec = new Vector<String>();
		vec.add("hello");
		Vector<String> vec2 = mod(vec);
		vec.set(0, "world");
		System.out.println(vec2.get(0));

	}
	public static void change(Stock st){
		st.price = 20.0;
	}
	public static Vector<String> mod(Vector<String> vec){
		Vector<String> res = new Vector<String>();
		res.add(vec.get(0));
		return res;
	}

}
