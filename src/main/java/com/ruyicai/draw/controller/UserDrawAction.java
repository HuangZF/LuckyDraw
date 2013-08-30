package com.ruyicai.draw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruyicai.draw.service.UserDrawService;

public class UserDrawAction implements ServletRequestAware, ServletResponseAware{

	private Logger logger = Logger.getLogger(UserDrawAction.class);

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
	UserDrawService userDrawService;
	
	public String queryDrawInfo()
	{
		
		return null;
	}
	
	
}
