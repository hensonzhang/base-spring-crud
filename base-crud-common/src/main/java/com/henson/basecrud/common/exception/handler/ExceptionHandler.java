package com.henson.basecrud.common.exception.handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;


public interface ExceptionHandler<T> {

    /**
     * 当处理成功后有返回
     * @param ex
     */
    public ModelAndView handler(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, T ex) throws Exception;
}
