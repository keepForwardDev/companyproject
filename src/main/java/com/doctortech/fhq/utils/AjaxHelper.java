package com.doctortech.fhq.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * Ajax响应帮助类
 */
public class AjaxHelper {

	/**
	 * @Description 响应JSON内容字符串
	 * @param response
	 * @param object
	 * @throws IOException
	 */
	public static void jsonPrint( HttpServletResponse response, Object object, String charset ) throws IOException {
		outPrint(response, JSON.toJSONString(object), charset);
	}


	/**
	 * @Description 响应Ajax请求
	 * @param response
	 * @param content
	 *            响应内容
	 * @param charset
	 *            字符编码
	 * @throws IOException
	 */
	public static void outPrint( HttpServletResponse response, String content, String charset ) throws IOException {
		response.setContentType("text/html;charset=" + charset);
		PrintWriter out = response.getWriter();
		out.print(content);
		out.flush();
	}


}
