/**
 * 
 */
package sim;

import java.util.*;
import java.util.concurrent.locks.*;

/**
 * @author jackie
 *
 */
public class Cache {
	
	private static Cache instance = null;
	public volatile Vector<Stock> stockInfos;  //cache data
	private ReentrantReadWriteLock lock;       //readwrite lock
	
	private Cache(){
		stockInfos = new Vector<Stock>();
		lock = new ReentrantReadWriteLock();
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
	public void setCache(Vector<Stock> info){
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
	public Vector<Stock> getCache(){
		Vector<Stock> res = new Vector<Stock>();
		lock.readLock().lock();
		for(int i=0;i<stockInfos.size();i++){
			res.add(stockInfos.get(i));
		}
		lock.readLock().unlock();
		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
