package com.ruyicai.draw.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import com.ruyicai.draw.domain.PrizeInfo;

public class RandomProbability {

	/**
	 * 获取随机概率在概率数组中的位置(即奖品在list中的位置)
	 * @param arry 按升序排序的整型数组
	 * @return
	 */
	public static int getDrawRandomProbability(int[] proArr)
	{
		boolean reGet = false;  // 重新获取
		int resultPosition = proArr.length - 1; // 初始为概率最大奖品的位置
		int maxProbability = getMaxInt(proArr); // 最大概率
		for(int i=0;i<proArr.length;i++)
		{
			int randomPro = getRandomPro(1,maxProbability);
			// 奖品发生概率与其延迟率共同影响结果
			if(randomPro < proArr[i])   // 小于其概率
			{
				PrizeConfig p = PrizeConfig.getInstance();
				List<PrizeInfo> piList = p.getList();
				double sum = p.getSum();
				double num = piList.get(i).getNum();
				double currentDelayPro = div(num, sum, 10);
				double delayPro = piList.get(i).getDelayProbability();
				if(Double.compare(currentDelayPro, delayPro) >= 0) // 大于或等于其延迟率
				{
					resultPosition = i;
					return resultPosition;
				}else
				{
					reGet = true;
					break;
				}
			}else
			{
				maxProbability -= proArr[i];
			}
		}
		// 需重新获取
		if(reGet)
		{
			resultPosition = getDrawRandomProbability(proArr);
		}

		return resultPosition;
	}

	/**
	 * 返回整型数组最大值.
	 * @param arry
	 * @return
	 */
	public static int getMaxInt(int[] arry)
	{
		int maxValue = 0;
		if(arry.length >= 0 )
		{
			maxValue = arry[0];
			for(int i=1;i<arry.length;i++)
			{
				if(maxValue < arry[i])
				{
					maxValue = arry[i];
				}
			}
		}
		return maxValue;
	}

	/**
	 * 在最值区间产生随机概率
	 * @param minProbability
	 * @param maxProbability
	 * @return
	 */
	public static int getRandomPro(int minProbability, int maxProbability)
	{
		int r = new Random().nextInt(maxProbability - minProbability);
		return minProbability + r;
	}

	/** 
	 * 两Double数相除.
	 * 
	 * @param dividend 
	 *            被除数 
	 * @param divisor 
	 *            除数 
	 * @param scale 
	 *            表示需要精确到小数点以后几位。 
	 * @return 两个参数的商
	 */
	public static Double div(Double dividend, Double divisor, Integer scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(dividend));
		BigDecimal b2 = new BigDecimal(Double.toString(divisor)); 
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
