package com.ruyicai.draw.service;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.draw.dao.PrizeInfoDAO;
import com.ruyicai.draw.domain.PrizeInfo;
import com.ruyicai.draw.exception.RuyicaiException;
import com.ruyicai.draw.util.RandomProbability;

@Service
public class DrawPrizeService {

	private Logger logger = Logger.getLogger(DrawPrizeService.class);

	private final static String TRY_DRAW_CODE = "0001";

	@Autowired
	PrizeInfoDAO prizeInfoDAO;
	
	@Autowired
	UserDrawService userDrawService;

	/**
	 * 根据概率随机获取奖品信息.
	 * 
	 * @param activeTimes 活动期次
	 * @return
	 */
	public PrizeInfo getPrizeInfoByRandomProbability(String activeTimes, String userno, String payObj, String gainObj) throws RuyicaiException
	{
		List<PrizeInfo> piList = queryPrizeConfigList(activeTimes);
		int piPosition = RandomProbability.getPrizeRandomPosition(piList);
		PrizeInfo returnPi = piList.get(piPosition);
		try{
			processPrizeInfo(returnPi, userno, payObj, gainObj);
		}catch(Exception e)
		{
			if(TRY_DRAW_CODE.equals(e.getMessage())) // try again
			{
				returnPi = getPrizeInfoByRandomProbability(activeTimes, userno, payObj, gainObj);
			}
		}
		logger.info("用户:"+ userno +"-->中奖信息为：奖品id=" + returnPi.getId() 
				+ ",奖品名称=" + returnPi.getName() + ",奖品等级=" + returnPi.getLevel()
				+ ",奖品剩余数量=" + returnPi.getRemainNum() + ",中奖时间=" + new Date());

		return returnPi;
	}

	/**
	 * 获取奖品列表信息.
	 * 
	 * @param activeTimes 活动期次
	 * @return
	 * @throws RuyicaiException
	 */
	public List<PrizeInfo> queryPrizeConfigList(String activeTimes) throws RuyicaiException
	{	
		return prizeInfoDAO.findPrizeInfoList(activeTimes);
	}

	/**
	 * 更新奖品信息</br>
	 * 记录用户中奖信息.
	 * 
	 * @param pi
	 * @param userno
	 * @param payObj
	 * @param gainObj
	 * @throws RuyicaiException
	 */
	@Transactional
	public void processPrizeInfo(PrizeInfo pi, String userno, String payObj, String gainObj) throws RuyicaiException{

		// 更新奖品信息
		int result = prizeInfoDAO.updatePrizeInfo(pi.getId());
		if(result == 0)
		{
			throw new RuyicaiException(TRY_DRAW_CODE);
		}

		// 记录用户中奖信息
		userDrawService.addUserDrawInfo(pi, userno, payObj, gainObj);

		// ------------------
		// 相关业务逻辑
		// 异步处理
		// ------------------

	}

}
