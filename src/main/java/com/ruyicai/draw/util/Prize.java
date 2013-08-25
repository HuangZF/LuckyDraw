package com.ruyicai.draw.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.ruyicai.draw.domain.PrizeInfo;

public class Prize {

	private Logger logger = Logger.getLogger(Prize.class);
	
	// 奖品信息列表
	private List<PrizeInfo> list = new ArrayList<PrizeInfo>();
	
	// 奖品总数
	private int sum = 0;

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

	public void init(List<PrizeInfo> list) {
		resetPrize(list);
	}

	protected void resetPrize(List<PrizeInfo> list) {
		lock.writeLock().lock();
		try {
			
			if(list != null && list.size() > 0)
			{
				for(PrizeInfo pi : list)
				{
					if(pi.getNum() > 0)
					{
						this.list.add(pi);
						setSum(getSum() + pi.getNum());
					}
				}
			}
			
			initialized = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("resetPrize error:", e);
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	
	public synchronized List<PrizeInfo> getList() {
		return list;
	}

	public synchronized void setList(List<PrizeInfo> list) {
		this.list = list;
	}

	public synchronized int getSum() {
		return sum;
	}

	public synchronized void setSum(int sum) {
		this.sum = sum;
	}
}
