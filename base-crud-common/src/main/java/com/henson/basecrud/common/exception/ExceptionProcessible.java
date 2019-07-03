package com.henson.basecrud.common.exception;


import java.io.Serializable;

import com.henson.basecrud.common.result.ResultEntity;

public interface ExceptionProcessible extends Serializable{

    public ResultEntity getResultEntity();
    public void setResultEntity(ResultEntity resultEntity);
}
