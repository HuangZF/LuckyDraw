package com.ruyicai.draw.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.draw.dao.PrizeConfigDAO;
import com.ruyicai.draw.domain.PrizeConfig;

@Service
public class PrizeService {
	
	private Logger logger = Logger.getLogger(PrizeService.class);
	
	@Autowired
	PrizeConfigDAO prizeConfigDAO;
	
	public List<PrizeConfig> queryPrizeConfigList()
	{	
		return prizeConfigDAO.findPrizeConfigList();
	}
}
