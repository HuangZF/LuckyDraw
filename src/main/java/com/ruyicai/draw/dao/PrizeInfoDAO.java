package com.ruyicai.draw.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.draw.domain.PrizeInfo;

@Component
public class PrizeInfoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 获取奖品列表信息
	 * @param activeTimes 活动期次
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PrizeInfo> findPrizeInfoList(String activeTimes) {
		String sql = "select pi.id,pi.name,pi.level,pi.sum,pi.remain_num,pi.arise_probability," +
				"pi.delay_probability,pi.start_date,pi.end_date,pi.active_times " +
				"from prize_info pi where pi.remain_num != '0' and pi.active_times = ? order by pi.arise_probability asc";
		Query q = entityManager.createNativeQuery(sql, PrizeInfo.class);
		q.setParameter(1, activeTimes);
		List<PrizeInfo> returnList = q.getResultList();
		return returnList;
	}
	
	/**
	 * 获取奖品信息
	 * @param id 奖品id
	 * @return
	 */
	public PrizeInfo findPrizeInfoById(Integer id) {
		PrizeInfo prizeInfo = entityManager.find(PrizeInfo.class, id);
		return prizeInfo;
	}

	/**
	 * 更新奖品信息.
	 * @param pi
	 * @return
	 */
	@Transactional
	public PrizeInfo merge(PrizeInfo pi) {
		PrizeInfo merged = this.entityManager.merge(pi);
		this.entityManager.flush();
		return merged;
	}
	
	/**
	 * 更新奖品信息.
	 * @param id
	 * @return
	 */
	@Transactional
	public int updatePrizeInfo(int id)
	{
		String sql = "update prize_info p set p.remain_num = (p.remain_num - 1)  where (p.remain_num -1)  >= 0 and p.id = ?";
		Query q = this.entityManager.createNativeQuery(sql);
		q.setParameter(1, id);
		return q.executeUpdate();
	}

}
