package com.henson.basecrud.common.exception.common;

import com.henson.basecrud.common.Module;
import com.henson.basecrud.common.exception.AppException;
import com.henson.basecrud.common.result.ResultEntity;

/**
 * 
 * @Title: InternalException.java
 * @Package com.henson.basecrud.common.exception.common
 * @Description: 内部异常
 * @Author henson
 * @Email hensonzhangzs@gmail.com
 * @Date 2018年7月3日 下午1:40:15
 * @Version V1.0
 *
 */
public class InternalException extends AppException {

    public InternalException(Module module, String code, String message) {
        super(module, code, message);
    }

    public InternalException(Module module, String code, String message, String description) {
        super(module, code, message, description);
    }

    public InternalException(ResultEntity resultEntity) {
        super(resultEntity);
    }
}
