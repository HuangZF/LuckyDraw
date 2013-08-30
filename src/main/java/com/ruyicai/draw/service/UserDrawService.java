package com.ruyicai.draw.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.draw.dao.UserDrawDAO;
import com.ruyicai.draw.domain.PrizeInfo;
import com.ruyicai.draw.domain.UserDraw;
import com.ruyicai.draw.exception.RuyicaiException;

@Service
public class UserDrawService {

	
	@Autowired
	UserDrawDAO userDrawDAO;
	
	/**
	 * 增加用户中奖信息
	 * @param pi
	 * @param userno
	 * @param payObj
	 * @param gainObj
	 */
	@Transactional
	public void addUserDrawInfo(PrizeInfo pi, String userno, String payObj, String gainObj)
	{
		UserDraw ud = new UserDraw();
		ud.setUserno(userno);
		ud.setPrizeId(pi.getId());
		ud.setPayObject(payObj);
		ud.setGainObject(gainObj);
		ud.setDrawDate(new Date());
		userDrawDAO.createUserDraw(ud);
	}

	/**
	 * 获取用户抽奖信息列表.
	 * @param params
	 * @return
	 * @throws RuyicaiException
	 */
	public List<UserDraw> queryUserDrawList(String params) throws RuyicaiException
	{
		return userDrawDAO.findUserDrawList(params);
	}
}
