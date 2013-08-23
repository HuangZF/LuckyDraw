package com.ruyicai.draw.util;

public class RandomProbability {

	/**
	 * 获取随机概率.
	 * @param arry
	 * @return
	 */
	public static int getDrawRandomProbability(int[] arry)
	{
		int result = 0;
		int maxProbability = getMaxString(arry);
		
		for(int i=0;i<arry.length;i++)
		{
			int randomPro = 0;
//			getDrawRandomPro(1,maxProbability);
			if(randomPro < arry[i])
			{
				result = i;
				break;
			}else
			{
				maxProbability -= arry[i];
			}
		}
		
		return result;
	}
	
	
	/**
	 * 返回整形数组最大值.
	 * @param arry
	 * @return
	 */
	public static int getMaxString(int[] arry)
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
	
	
}
