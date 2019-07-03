package com.henson.basecrud.common.exception.handler;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.henson.basecrud.common.exception.ExceptionProcessible;
import com.henson.basecrud.common.result.ResponseUtils;
import com.henson.basecrud.common.standard.Result2Body;


public class ExceptionProcessibleHandler implements ExceptionHandler<ExceptionProcessible> {


    public static final String DEFAULT_ERROR_PAGE = "/public/error";

    protected String errorPage = DEFAULT_ERROR_PAGE;

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionProcessibleHandler.class);

    @Override
    public ModelAndView handler(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, ExceptionProcessible ex) throws Exception {
        if (isResponseBody(handler)) {
            return handleResponseBody(ex, request, response);
        }
        ModelAndView view = new ModelAndView(errorPage);
        view.addObject(ex.getResultEntity());
        return view;

    }

    /**
     * @param handlerMethod
     * @return
     */
    private boolean isResponseBody(HandlerMethod handlerMethod) {
        if (handlerMethod == null) {
            return false;
        }
        Method method = handlerMethod.getMethod();
        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
        if (ResponseEntity.class == method.getReturnType() || responseBodyAnn != null) {//restful
            return true;
        }
        return false;
    }


    /**
     * return json exception
     *
     * @param exInfo
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private ModelAndView handleResponseBody(ExceptionProcessible exInfo,
                                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		String retex = JSONObject.toJSONString(Result2Body.tranform(exInfo.getResultEntity()));
		LOGGER.error("异常信息:{}", retex);
		ResponseUtils.renderJson(response, retex);
        return new ModelAndView();
    }

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
}
