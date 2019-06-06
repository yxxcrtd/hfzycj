package com.zycj.business.park.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class HtmlUtil {
	public static void writerJson(HttpServletResponse response, String jsonStr) {
		response.setContentType("application/json");
		writer(response, jsonStr);
	}

	public static void writerHtml(HttpServletResponse response, String htmlStr) {
		writer(response, htmlStr);
	}

	public static void writer(HttpServletResponse response, String str) {
		try {
			StringBuffer result = new StringBuffer();
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
