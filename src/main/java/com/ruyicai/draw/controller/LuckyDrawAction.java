package com.ruyicai.draw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruyicai.draw.domain.PrizeInfo;
import com.ruyicai.draw.domain.UserDraw;
import com.ruyicai.draw.service.PrizeService;
import com.ruyicai.draw.util.PrizeConfig;
import com.ruyicai.draw.util.RandomProbability;
import com.ruyicai.draw.util.ResponseJson;


public class LuckyDrawAction implements ServletRequestAware, ServletResponseAware{

	private Logger logger = Logger.getLogger(LuckyDrawAction.class);

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Autowired
	PrizeService prizeService;

	/**
	 * 抽奖活动
	 * @return
	 */
	public String drawActivity()
	{

		// 返回map
		Map<String, String> retMap = new HashMap<String, String>();

		//get prize info
		PrizeConfig prize = PrizeConfig.getInstance();
		if(! prize.initialized)
		{
			synchronized(this){
				if(! prize.initialized)
				{
					List<PrizeInfo> list = prizeService.queryPrizeConfigList();
					prize.init(list);
				}
			}
		}

		// list 已按概率升序排序
		// get proArr
		List<PrizeInfo> pcList = prize.getList();
		if(pcList.size() > 0)
		{
			int[] proArr = new int[pcList.size()];
			for(int i=0;i<pcList.size();i++)
			{
				proArr[i] = pcList.get(i).getAriseProbability();
			}

			// 根据随机概率获取中奖项,奖品项所在list中的位置
			// the PrizeConfig's position in the List
			int prizePos = RandomProbability.getDrawRandomProbability(proArr);

			// 获取奖品信息
			PrizeInfo pc1 = pcList.get(prizePos);
			logger.info("中奖信息：奖品id="+pc1.getId()+",奖品名称="+ pc1.getName()
					+",奖品等级="+pc1.getLevel()+",奖品发生概率="+pc1.getAriseProbability()
					+",奖品数量="+pc1.getNum()+",奖品延迟率="+pc1.getDelayProbability());

			// 用户中奖信息
			UserDraw ud = new UserDraw();
			ud.setUserno("0132123");
			ud.setPrizeId(pc1.getId());
			ud.setDrawTime(new Date());

			// 减少奖品信息及插入用户中奖信息
			// 同时也更新单例对象中的奖品信息
			// 保证在同一事务中处理
			prizeService.updatePrizeInfo(pc1, ud, prizePos);

			retMap.put("id", String.valueOf(pc1.getId()));
			retMap.put("name", pc1.getName());
			retMap.put("level", pc1.getLevel());
			retMap.put("returnCode", "0000");

			// 返回获取信息
		}else
		{
			System.out.println("抽奖结束!");
			retMap.put("returnCode", "0001");
		}

		ResponseJson.printJson(this.response, retMap);
		return null;
	}

}
