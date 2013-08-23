package com.ruyicai.draw.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.draw.domain.PrizeConfig;

@Component
public class PrizeConfigDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public PrizeConfig findPrizeConfig(Integer id) {
		PrizeConfig prizeConfig = entityManager.find(PrizeConfig.class, id);
		return prizeConfig;
	}
	
	public PrizeConfig findPrizeConfig(Integer id, boolean lock) {
		PrizeConfig prizeConfig = entityManager.find(PrizeConfig.class, id, lock ? LockModeType.PESSIMISTIC_WRITE
				: LockModeType.NONE);
		return prizeConfig;
	}
	
	@Transactional
	public PrizeConfig merge(PrizeConfig prizeConfig) {
		PrizeConfig merged = this.entityManager.merge(prizeConfig);
		this.entityManager.flush();
		return merged;
	}
	
	@Transactional
	public List<PrizeConfig> readBatch(String id) {
		if (StringUtils.isBlank(id)) {
			throw new IllegalArgumentException("the argument id is require");
		}
		String[] idArray = id.split(",");
		List<PrizeConfig> resultList = new ArrayList<PrizeConfig>();
		for (String iditem : idArray) {
			resultList.add(read(Integer.parseInt(iditem)));
		}
		return resultList;
	}
	
	@Transactional
	public PrizeConfig read(int id) {
		PrizeConfig prizeConfig = findPrizeConfig(id, true);
		return entityManager.merge(prizeConfig);
	}
	
	/**
	 * 获取所有的奖品信息
	 * @return
	 */
	@Transactional
	public List<PrizeConfig> findPrizeConfigList() {
		Query query = this.entityManager.createQuery("from prizeconfig");
		return query.getResultList();
	}
	
}
