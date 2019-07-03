package com.henson.basecrud.common.standard;

import com.henson.basecrud.common.result.ResultEntity;


public class Result2Body {

    /**
     *
     * @param entity
     * @return
     */
    public static OutputBody tranform(ResultEntity entity){
       if(entity==null){
           return new OutputBody();
       }
        return entity;
    }

}
