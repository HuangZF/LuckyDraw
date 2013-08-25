package com.ruyicai.draw;

import com.ruyicai.draw.controller.LuckyDrawAction;

/**
 * start
 *
 */
public class AppStart 
{
	public static void main( String[] args )
	{
		System.out.println( "开始抽奖!" );

		Thread t = new AppStart().new DrawThread();
		t.start();
	}

	class DrawThread extends Thread
	{
		public void run() {
			LuckyDrawAction lda = new LuckyDrawAction();
			lda.drawActivity();
		}
	}
}
