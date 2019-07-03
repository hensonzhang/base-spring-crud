
package com.henson.basecrud.common.result;


import java.lang.reflect.Constructor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.henson.basecrud.common.Module;
import com.henson.basecrud.common.exception.AppException;
import com.henson.basecrud.common.exception.AppRunTimeException;
import com.henson.basecrud.common.standard.OutputBody;
import com.henson.basecrud.common.util.ResourceConfig;
import com.henson.basecrud.common.util.ResourcesUtil;

public abstract class ResultUtils {

    /**
     * 创建一个消息
     * @param module
     * @param status
     * @param messageCode
     * @return
     */
   public static ResultEntity createResult(Module module,String status,String messageCode,Object data){

       OutputBody.PubResponse pubResponse = new OutputBody.PubResponse();
       pubResponse.setModule(module);
       pubResponse.setStatus(status);
       
       //TODO 后面动态读
       pubResponse.setVersion("1.0.0");
       pubResponse.setEncryType("MD5");
       
       if(status==OutputBody.PubResponse.STATUS_FAIL) {
           pubResponse.setMessageCode(messageCode);
           pubResponse.setMessage(ResourcesUtil.getValue(ResourceConfig.MESSAGE+((module!=Module.COMMON)?"_"+module.getModuleName().toLowerCase():""), messageCode));
       }
       return createResult(pubResponse,data);
   }


    /**
     * 创建一个消息
     * @param pubResponse
     * @return
     */
   public static ResultEntity createResult( OutputBody.PubResponse pubResponse,Object data){
       ResultEntity resultEntity = new ResultEntity();
		resultEntity.setPubResponse(pubResponse);
       resultEntity.setBody(data);
       return resultEntity;
   }

    /**
     * 创建一个成功消息
     * @param data
     * @return
     */
   public static ResultEntity createSuccessResult(Module module,Object data){
      return createResult(module,OutputBody.PubResponse.STATUS_SUCCESS,null,data);
   }



	/**
	 * 创建一个成功消息
	 * 
	 * @param data
	 * @return
	 */
	public static String createSuccessJsonResult(Module module, Object data) {
		return JSONObject.toJSONString(createSuccessResult(module, data), SerializerFeature.WriteMapNullValue);
	}

	public static String createSuccessJsonResultNotNull(Module module, Object data) {
		return JSONObject.toJSONString(createSuccessResult(module, data));
	}


    /**
     * 创建一个失败消息
     * @param messageCode
     * @return
     */
    public static ResultEntity createFailResult(Module module,String  messageCode){
        return createResult(module,OutputBody.PubResponse.STATUS_FAIL,messageCode,null);
    }

    /**
     * 创建一个失败消息
     * @param messageCode
     * @return
     */
    public static String createFailJsonResult(Module module,String  messageCode){
        return JSONObject.toJSONString(createFailResult(module,messageCode));
    }


    /**
     * 抛出一个app 异常
     */
    public static void throwAppException(Module module,String messageCode) throws AppException {
       throw new AppException(createFailResult(module,messageCode));
    }


    /**
     * 抛出一个 自定义app 异常
     */
    public static void throwAppException(Module module,String messageCode,Class<? extends AppException> clasz) throws AppException {
        try {
        if(clasz==null){
            return;
        }
        Constructor<? extends AppException> constructor = clasz.getConstructor(ResultEntity.class);
        throw constructor.newInstance(createFailResult(module,messageCode));
        } catch (Exception e) {
            throwAppException(module,messageCode);
        }
    }

    /**
     * 抛出一个app runtime 异常
     * @param module
     * @param messageCode
     */
    public static void throwAppRuntimeException(Module module,String messageCode){
        throw new AppRunTimeException(createResult(module,OutputBody.PubResponse.STATUS_FAIL,messageCode,null));
    }

    /**
     * 抛出一个自定义app runtime 异常
     * @param module
     * @param messageCode
     */
    public static void throwAppRuntimeException(Module module,String messageCode,Class<? extends AppRunTimeException> clasz){

        try {
            if(clasz==null){
                return;
            }
            Constructor<AppRunTimeException> constructor = (Constructor<AppRunTimeException>) clasz.getConstructor(ResultEntity.class);
            throw constructor.newInstance(createFailResult(module,messageCode));
        } catch (ReflectiveOperationException e) {
            throwAppRuntimeException(module,messageCode);
        }

    }
    
}
