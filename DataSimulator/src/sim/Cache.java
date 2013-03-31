/**
 * 
 */
package sim;

import java.util.*;
import java.util.concurrent.locks.*;

/**
 * @author jackie
 * cache the data
 */
public class Cache {
	
	private static Cache instance = null;
	private volatile Vector<Stock> stockInfos;  //cache the updated data of stocks
	private ReentrantReadWriteLock lock;       //readwrite lock
	
	private volatile Vector<Stock> allStocks;  //data of all the stocks
	
	private Cache(){
		stockInfos = new Vector<Stock>();
		lock = new ReentrantReadWriteLock();
		//all the stocks
		allStocks = new Vector<Stock>();
	}
	
	public synchronized static Cache getInstance()
	{
		if(instance == null)
			instance = new Cache();
		return instance;
	}
	
	/**
	 * set cache
	 * @param info
	 */
	public void setUpdateCache(Vector<Stock> info){
		lock.writeLock().lock();
		stockInfos.clear();
		for(int i=0;i<info.size();i++){
			stockInfos.add(info.get(i));
		}
		lock.writeLock().unlock();
	}

	/**
	 * read cache, deeply copy
	 * @return
	 */
	public Vector<Stock> getUpdateCache(){
		Vector<Stock> res = new Vector<Stock>();
		lock.readLock().lock();
		for(int i=0;i<stockInfos.size();i++){
			res.add(stockInfos.get(i));
		}
		lock.readLock().unlock();
		return res;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Vector<Stock> getAllStocks(){
		return allStocks;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
