package com.ruyicai.draw.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.draw.dao.PrizeConfigDAO;
import com.ruyicai.draw.domain.PrizeInfo;
import com.ruyicai.draw.domain.UserDraw;
import com.ruyicai.draw.util.Prize;

@Service
public class PrizeService {

	private Logger logger = Logger.getLogger(PrizeService.class);

	@Autowired
	PrizeConfigDAO prizeConfigDAO;

	/**
	 * 获取奖品信息.
	 * @return
	 */
	public List<PrizeInfo> queryPrizeConfigList()
	{	
		return prizeConfigDAO.findPrizeConfigList();
	}

	/**
	 * 更新奖品信息.
	 * @param prizeConfig
	 * @param ud
	 * @param prizePos
	 */
	public void updatePrizeInfo(PrizeInfo pi, UserDraw ud, int prizePos) {

		// 某一奖品数量-1
		System.out.println("前："+pi.getNum());
		pi.setNum(pi.getNum() - 1);
		System.out.println("后："+ pi.getNum());

		prizeConfigDAO.updatePrizeInfo(pi, ud);

		// 如果某一奖品数量为0，则将此奖品从list中去除
		Prize prize = Prize.getInstance();
		if(pi.getNum() == 0)
		{
			List<PrizeInfo> piList = prize.getList();
			piList.remove(prizePos);
		}
		// 总奖品数-1
		prize.setSum(prize.getSum() - 1);

		// 相关业务逻辑



	}
}
