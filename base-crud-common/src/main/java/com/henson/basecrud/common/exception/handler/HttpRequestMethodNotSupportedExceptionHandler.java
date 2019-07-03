package com.henson.basecrud.common.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.henson.basecrud.common.Module;
import com.henson.basecrud.common.result.ResponseUtils;
import com.henson.basecrud.common.result.ResultUtils;
import com.henson.basecrud.common.standard.Result2Body;


public class HttpRequestMethodNotSupportedExceptionHandler implements ExceptionHandler<HttpRequestMethodNotSupportedException> {
    @Override
    public ModelAndView handler(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, HttpRequestMethodNotSupportedException ex) {
        ResponseUtils.renderJson(response, JSONObject.toJSONString(Result2Body.tranform(ResultUtils.createFailResult(Module.COMMON, "0001"))));
        return new ModelAndView();
    }
}
