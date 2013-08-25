package com.ruyicai.draw.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 返回json信息
 * @author ryc
 *
 */
public class ResponseJson {

	private static Logger logger = Logger.getLogger(ResponseJson.class);
	
	/**
	 * 打印JSON信息
	 */
	public static void printJson(HttpServletResponse response, Map<String, String> retMap) {
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(JsonUtil.toJson(retMap));
			response.getWriter().flush();
			response.getWriter().close();
		}  catch (Exception e) {
			logger.error("发生异常：", e);
			e.printStackTrace();
		}
	}

	/**
	 * 打印GBK JSON List信息
	 */
	public static void printJsonGBK(HttpServletResponse response, List<String> list) {
		try {
			response.setCharacterEncoding("GBK");
			response.getWriter().write(JsonUtil.toJson(list));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			logger.error("发生异常：", e);
		}
	}
}
