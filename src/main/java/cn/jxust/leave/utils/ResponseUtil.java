package cn.jxust.leave.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 完成字符串响应的同时设置字符编码。
 * @author tyeerth
 * @date 2019/11/11 - 10:23
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response, Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
