package com.ruyicai.draw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruyicai.draw.domain.PrizeInfo;
import com.ruyicai.draw.service.DrawPrizeService;
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
			String activeTimes = "0";
			String userno = "00001119";
			
			String payObj = "100积分";
			String gainObj = "2元充值";
			
			logger.info("抽奖开始->用户编号：" + userno + ", 活动期次: " + activeTimes);
			// 根据概率随机获取奖品
			PrizeInfo pi = drawPrizeService.getPrizeInfoByRandomProbability(activeTimes, userno, payObj, gainObj);

			// 返回获取信息
			retMap.put("id", String.valueOf(pi.getId()));
			retMap.put("name", pi.getName());
			retMap.put("level", pi.getLevel());
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("drawPrizeInfo erroe:",e);
			respCode = ResponseCode.ERROR.value;
		}

		logger.info("抽奖结束->respCode:" + respCode);
		retMap.put("respCode", respCode);
		ResponseJson.respJsonMap(this.response, retMap, "GBK");
		return null;
	}


}
