package com.ruyicai.draw.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.ruyicai.draw.domain.PrizeInfo;
import com.ruyicai.draw.exception.RuyicaiException;

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
	protected void resetPrize(List<PrizeInfo> list) throws RuyicaiException{
		lock.writeLock().lock();
		try {
			if(list != null && list.size() > 0)
			{
				for(PrizeInfo pi : list)
				{
					if(pi.getSum() > 0)
					{
						this.list.add(pi);
						setSum(getSum() + pi.getSum());
					}
				}
			}
			initialized = true;
		}finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * 根据随机概率获取中奖信息.
	 * <p>以抢占方式获取</p>
	 * <p>同时更新奖品单例中的奖品数量</p>
	 * @return
	 */
	public Map<Integer, PrizeInfo> getPrizeInfo() throws RuyicaiException
	{
		lock.writeLock().lock();
		try
		{
			if(this.list.size() > 0)
			{
				Map<Integer, PrizeInfo> piMap = new HashMap<Integer, PrizeInfo>();

				// 根据随机概率获取中奖项,奖品项所在list中的位置
				int prizePos = RandomProbability.getPrizeRandomPosition(list);

				// 获取奖品信息
				PrizeInfo pi = list.get(prizePos);
				
				// 更新单例中奖品信息
				// 某一奖品数量-1
				System.out.println("奖品数量->前："+pi.getRemainNum());
				pi.setRemainNum(pi.getRemainNum() - 1);
				System.out.println("奖品数量->后："+ pi.getRemainNum());
				
				// 如果某一奖品数量为0，则将此奖品从list中去除
				if(pi.getRemainNum() == 0)
				{
					removePrizeInfo(prizePos);
				}
				
				// 总奖品数-1
				setSum(getSum() - 1);
				System.out.println("奖品总数:" + getSum());
				
				piMap.put(prizePos, pi);
				
				return piMap;
			}
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
	public void removePrizeInfo(int prizePos) throws RuyicaiException
	{
		lock.writeLock().lock();
		try
		{
			this.list.remove(prizePos);
		}finally
		{
			lock.writeLock().unlock();
		}
	}

	// -------------不需要同步,因均在锁内调用
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
