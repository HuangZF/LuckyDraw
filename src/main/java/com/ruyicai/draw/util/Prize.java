package com.ruyicai.draw.util;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.ruyicai.draw.domain.PrizeConfig;

public class Prize {

	private Logger logger = Logger.getLogger(Prize.class);
	
	private String id;
	private String name;
	private String level;
	private int num;
	private int probability;
	private int delay;
	
	// 概率数组
	private int[] proArr;
	
	
	public volatile boolean  initialized = false;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private static final Prize INSTANCE = new Prize();		

	private Prize() {	
		init(null);
		initialized = false;
	}

	public static final Prize getInstance() {
		return INSTANCE;
	}

	public void init(List<PrizeConfig> list) {
		resetPrize(list);
	}

	protected void resetPrize(List<PrizeConfig> list) {
		lock.writeLock().lock();
		try {
			for(int i=0;i<list.size();i++)
			{
//				proArr[i] = list.get(i);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("resetPrize error:", e);
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	
	
}
