package com.henson.basecrud.common.standard;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSONObject;


public class RequestUtil {

	public static InputBody request2Body(HttpServletRequest request) {
		try {
			return JSONObject.parseObject(StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8")),
					InputBody.class);
		} catch (Exception e) {
			throw new HttpMessageNotReadableException("Could not read document: " + e.getMessage(), e);
		}
	}

	public static String getRequestToken(InputBody inputBody) {
		if (inputBody == null) {
			return null;
		}
		PubRequest pubrequest = inputBody.getPubRequest();
		if (pubrequest == null) {
			return null;
		}
		return pubrequest.getToken();
	}
}
