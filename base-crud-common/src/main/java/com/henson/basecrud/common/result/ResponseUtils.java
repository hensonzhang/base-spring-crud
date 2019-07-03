package com.henson.basecrud.common.result;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
public class ResponseUtils {

	//send content
	public static void render(HttpServletResponse response, String contentType, String text){
		response.setContentType(contentType);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//send to JSON
	public static void renderJson(HttpServletResponse response, String text){
		render(response, "application/json;charset=UTF-8", text);
	}
	//sne to xml
	public static void renderXml(HttpServletResponse response, String text){
		render(response, "text/xml;charset=UTF-8", text);
	}

	public static void renderHtml(HttpServletResponse response, String text){
		render(response, "text/html;charset=UTF-8", text);
	}
	//send to text
	public static void renderText(HttpServletResponse response, String text){
		render(response, "text/plain;charset=UTF-8", text);
	}

	//send to stream
	public static void renderStream(HttpServletResponse response, InputStream inputStream) throws IOException {
		try{
		response.setContentType("application/octet-stream");
		OutputStream out = response.getOutputStream();
		byte[] temp = new byte[1024];
		int readLength;
		while (-1 != (readLength = inputStream.read(temp, 0, temp.length))) {
			out.write(temp, 0, readLength);
		}
	}finally{
	if(inputStream!=null){
	   inputStream.close();}
	}

	}
	
	
}
