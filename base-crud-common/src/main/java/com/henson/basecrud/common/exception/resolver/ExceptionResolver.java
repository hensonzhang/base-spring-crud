package com.henson.basecrud.common.exception.resolver;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.henson.basecrud.common.exception.handler.ExceptionHandler;
import com.henson.basecrud.common.result.ResponseUtils;
import com.henson.basecrud.common.result.ResultUtils;
import com.henson.basecrud.common.util.ModuleUtil;


public class ExceptionResolver implements HandlerExceptionResolver {

    private static final Log LOGGER =  LogFactory.getLog(ExceptionResolver.class);

    public static final String DEFAULT_ERROR_PAGE = "/public/error";

    protected String errorPage = DEFAULT_ERROR_PAGE;

    protected List<ExceptionHandler> exceptionHandlers;





    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        HandlerMethod handlerMethod = null;
        try {
            handlerMethod = (HandlerMethod) handler;
            if (handler == null) {
                LOGGER.error("异常处理,统一异常处理,handlerMethod没找到");
            }
            if (CollectionUtils.isEmpty(exceptionHandlers)) {
                return defaultHandler(request,response,handlerMethod,ex);
            }
            ExceptionHandler exceptionHandler = findExceptionHandler(ex);
            if(exceptionHandler==null){
                return defaultHandler(request,response,handlerMethod,ex);
            }
            return exceptionHandler.handler(request,response,handlerMethod,ex);
        }catch (Exception e){
            return defaultHandler(request,response,handlerMethod,e);
        }
    }


    /**
     * 默认异常处理方式，处理。
     * @return
     */
    protected ModelAndView defaultHandler(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex){
      ResponseUtils.renderJson(response,ResultUtils.createFailJsonResult(ModuleUtil.current(),"001"));
      return null;
    }





    /**
     * 查找相应的异常处理器
     *
     * @param e
     * @return
     */
    private ExceptionHandler findExceptionHandler(Exception e) {
        if (CollectionUtils.isEmpty(this.exceptionHandlers)||e==null) {
            return null;
        }

        for (ExceptionHandler exceptionHandler : this.exceptionHandlers) {
            Type[] types = exceptionHandler.getClass().getGenericInterfaces();
            if (types == null) {
                return null;
            }
            for (Type type : types) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    if (parameterizedType.getRawType() == ExceptionHandler.class) {//WITH 1.7
                        Class clazs = (Class) parameterizedType.getActualTypeArguments()[0];
                        if (clazs.isAssignableFrom(e.getClass())||e.getClass() == clazs) {
                            return exceptionHandler;
                        }
                    }
                }
            }
        }
        return null;
    }



    public void setExceptionHandlers(List<ExceptionHandler> exceptionHandlers) {
        this.exceptionHandlers = exceptionHandlers;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

}