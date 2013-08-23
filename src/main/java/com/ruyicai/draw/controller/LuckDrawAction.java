package com.ruyicai.draw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruyicai.draw.domain.PrizeConfig;
import com.ruyicai.draw.service.PrizeService;
import com.ruyicai.draw.util.Prize;


public class LuckDrawAction implements ServletRequestAware, ServletResponseAware{

	private Logger logger = Logger.getLogger(LuckDrawAction.class);
	
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
	    
		//get prize info
		Prize prize = Prize.getInstance();
		if(! prize.initialized)
		{
			List<PrizeConfig> list = prizeService.queryPrizeConfigList();
			prize.init(list);
		}
		
		
		
		return null;
	}
}
