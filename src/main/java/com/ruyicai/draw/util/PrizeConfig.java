package com.ruyicai.draw.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.ruyicai.draw.domain.PrizeInfo;

public class PrizeConfig {

	private Logger logger = Logger.getLogger(PrizeConfig.class);

	// 奖品信息列表
	private List<PrizeInfo> list = new ArrayList<PrizeInfo>();

	// 奖品总数
	private int sum = 0;

	public volatile boolean  initialized = false;
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	private static class PrizeConfigSingletonHolder {
		private static final PrizeConfig INSTANCE = new PrizeConfig();
	}

	private PrizeConfig() {
		init(null);
		initialized = false;
	}

	public static final PrizeConfig getInstance() {
		return PrizeConfigSingletonHolder.INSTANCE;
	}

	public void init(List<PrizeInfo> list) {
		resetPrize(list);
	}

	/**
	 * 设置奖品信息.
	 * @param list
	 */
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
			logger.error("resetPrizeConfig error:", e);
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * 获取奖品信息.
	 * @return
	 */
	public Map<Integer, PrizeInfo> getPrizeInfo()
	{
		lock.writeLock().lock();
		try
		{
			if(this.list.size() > 0)
			{
				Map<Integer, PrizeInfo> piMap = new HashMap<Integer, PrizeInfo>();

				// 根据随机概率获取中奖项,奖品项所在list中的位置
				// the PrizeConfig's position in the List
				int prizePos = RandomProbability.getPrizeRandomPosition(list);

				// 获取奖品信息
				PrizeInfo pi = list.get(prizePos);
				piMap.put(prizePos, pi);
				
				return piMap;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("getPrizeInfo erroe:",e);
		}finally
		{
			lock.writeLock().unlock();
		}
		return null;
	}
	
	/**
	 * 删除奖品信息
	 * @param prizePos 奖品在list中的位置
	 */
	public void removePrizeInfo(int prizePos)
	{
		lock.writeLock().lock();
		try
		{
			this.list.remove(prizePos);
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("removePrizeInfo error:", e);
		}finally
		{
			lock.writeLock().unlock();
		}
	}


	public synchronized int getSum() {
		return sum;
	}

	public synchronized void setSum(int sum) {
		this.sum = sum;
	}
}
