package com.henson.basecrud.common.exception;

import com.henson.basecrud.common.Module;
import com.henson.basecrud.common.result.ResultEntity;
import com.henson.basecrud.common.result.ResultUtils;
import com.henson.basecrud.common.standard.OutputBody;


public class AppRunTimeException extends RuntimeException implements ExceptionProcessible{

    //系统模块
    private Module module;
    //错误码 模块编码+本系统内部编码
    private String code;
    //异常描述
    private String description;

    protected ResultEntity resultEntity;


    public AppRunTimeException(Module module, String code, String message) {
        this(module, code, message, message);
    }

    public AppRunTimeException(Module module, String code, String message, String description) {
        super(message);
        this.module = module;
        this.code = code;
        this.description = description;

        OutputBody.PubResponse pubResponse = new OutputBody.PubResponse();
        pubResponse.setModule(module);
        pubResponse.setStatus(OutputBody.PubResponse.STATUS_FAIL);
        pubResponse.setMessageCode(code);
        pubResponse.setMessage(message);
        this.resultEntity = ResultUtils.createResult(pubResponse,null);
    }

    public AppRunTimeException(ResultEntity resultEntity){
		this(resultEntity.getPubResponse().getModule(), resultEntity.getPubResponse().getMessageCode(),
				resultEntity.getPubResponse().getMessage());

        this.resultEntity = resultEntity;
    }



    /**
     * 产生异常的模块
     *
     * @return
     */
    public Module getModule() {
        return module;
    }

    /**
     * 错误码
     *
     * @return
     */
    public String getCode() {
        return code;
    }



    /**
     * @param module the module to set
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     * 用户可读描述信息
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(": [");
        sb.append(module);
        sb.append("] - ");
        sb.append(code);
        sb.append(" - ");
        sb.append(getMessage());
        if (getDescription() != null) {
            sb.append(" - ");
            sb.append(getDescription());
        }
        return sb.toString();
    }

	@Override
	public ResultEntity getResultEntity() {
		return this.resultEntity;
	}

	@Override
	public void setResultEntity(ResultEntity resultEntity) {
		this.resultEntity = resultEntity;
	}
}
