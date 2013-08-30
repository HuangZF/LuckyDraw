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
public class UserDrawDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 记录用户抽奖信息
	 * @param userMap
	 * @return
	 */
	@Transactional
	public UserDraw createUserDraw(UserDraw userDraw) {
		entityManager.persist(userDraw);
		return userDraw;
	}
	
	/**
	 * 获取用户抽奖信息列表
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserDraw> findUserDrawList(String params) {
		String sql = "select ud.id,ud.userno,ud.prize_id,ud.pay_object,ud.gain_object,ud.draw_date " +
				"from user_draw ud order by ud.id desc";
		Query q = entityManager.createNativeQuery(sql, PrizeInfo.class);
		List<UserDraw> returnList = q.getResultList();
		return returnList;
	}
}
