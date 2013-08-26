package com.ruyicai.draw.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.draw.domain.PrizeInfo;
import com.ruyicai.draw.domain.UserDraw;

@Component
public class PrizeInfoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 获取所有的奖品信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PrizeInfo> findPrizeInfoList() {
		String sql = "select pi.id,pi.name,pi.level,pi.num,pi.arise_probability,pi.delay_probability from prize_info pi order by pi.arise_probability asc";
		Query q = entityManager.createNativeQuery(sql, PrizeInfo.class);
		List<PrizeInfo> returnList = q.getResultList();
		return returnList;
	}

	/**
	 * 更新奖品信息.
	 * @param prizeConfig
	 * @return
	 */
	@Transactional
	public PrizeInfo merge(PrizeInfo pi) {
		PrizeInfo merged = this.entityManager.merge(pi);
		this.entityManager.flush();
		return merged;
	}

	/**
	 * 增加用户抽奖信息
	 * @param userMap
	 * @return
	 */
	@Transactional
	public UserDraw createUserDraw(UserDraw userDraw) {
		entityManager.persist(userDraw);
		return userDraw;
	}

	/**
	 * 更新奖品信息，同时添加用户抽奖信息.
	 * @param prizeConfig
	 * @param ud
	 */
	@Transactional
	public void updatePrizeInfo(PrizeInfo pi, UserDraw ud)
	{
		// 更新奖品信息
		merge(pi);

		// 添加用户抽奖信息
		createUserDraw(ud);
	}

}
