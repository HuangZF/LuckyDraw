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
import com.ruyicai.draw.service.DrawPrizeService;
import com.ruyicai.draw.util.PrizeConfig;
import com.ruyicai.draw.util.ResponseCode;
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
	DrawPrizeService drawPrizeService;

	/**
	 * 抽奖活动
	 * @return
	 */
	public String drawActivity()
	{
		// 返回map json信息
		Map<String, String> retMap = new HashMap<String, String>();
		String respCode = ResponseCode.OK.value;

		try
		{
			// get prizeconfig singleton instance
			PrizeConfig pc = getSingletonInstance();
			// 根据随机概率获取奖品信息
			Map<Integer, PrizeInfo> piMap = pc.getPrizeInfo();
			if(piMap != null)
			{
				int prizePos = 0;
				PrizeInfo pi = null;
				for(Integer key : piMap.keySet())
				{
					prizePos = key;
					pi = piMap.get(key);
				}
				if(pi != null && !"".equals(pi))
				{
					logger.info("中奖信息：奖品id="+pi.getId()+",奖品名称="+ pi.getName()
							+",奖品等级="+pi.getLevel()+",奖品发生概率="+pi.getAriseProbability()
							+",奖品数量="+pi.getNum()+",奖品延迟率="+pi.getDelayProbability());

					// 用户中奖信息
					UserDraw ud = new UserDraw();
					ud.setUserno("0132123");
					ud.setPrizeId(pi.getId());
					ud.setDrawTime(new Date());

					// 减少奖品信息及插入用户中奖信息
					// 同时也更新单例对象中的奖品信息
					// 保证在同一事务中处理
					drawPrizeService.updatePrizeInfo(pi, ud, prizePos);

					// 返回获取信息
					retMap.put("id", String.valueOf(pi.getId()));
					retMap.put("name", pi.getName());
					retMap.put("level", pi.getLevel());
				}else
				{
					respCode = ResponseCode.Draw_Expired.value;
				}
			}else
			{
				respCode = ResponseCode.Draw_Expired.value;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("drawPrizeInfo erroe:",e);
			respCode = ResponseCode.ERROR.value;
		}

		retMap.put("respCode", respCode);
		ResponseJson.respJsonMap(this.response, retMap, "GBK");
		return null;
	}
	
	/**
	 * 获取奖品配置单例信息.
	 * @return
	 */
	private PrizeConfig getSingletonInstance()
	{
		PrizeConfig pc = PrizeConfig.getInstance();
		if(! pc.initialized)
		{
			synchronized(pc)
			{
				if(! pc.initialized)
				{
					List<PrizeInfo> list = drawPrizeService.queryPrizeConfigList();
					pc.init(list);
				}
			}
		}
		return pc;
	}

}
