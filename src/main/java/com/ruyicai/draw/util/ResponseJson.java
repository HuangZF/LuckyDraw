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
	 * 返回json map信息
	 * @param response
	 * @param retMap
	 * @param charSet
	 */
	public static void printJsonMap(HttpServletResponse response, Map<String, String> retMap ,String charSet) {
		try {
			response.setCharacterEncoding(charSet);
			response.getWriter().write(JsonUtil.toJson(retMap));
			response.getWriter().flush();
			response.getWriter().close();
		}  catch (Exception e) {
			logger.error("发生异常：", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回json list 信息.
	 * @param response
	 * @param list
	 * @param charSet
	 */
	public static void printJsonGBK(HttpServletResponse response, List<String> list, String charSet) {
		try {
			response.setCharacterEncoding(charSet);
			response.getWriter().write(JsonUtil.toJson(list));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			logger.error("发生异常：", e);
		}
	}
}
