package com.henson.basecrud.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;


public class Beans{

    /**
     *
     * @param b
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T newObj(Object b,Class<T> targetClass){
        if(b==null){
            return null;
        }
        T instantiate = BeanUtils.instantiate(targetClass);
        BeanUtils.copyProperties(b,instantiate);
        return instantiate;
    }



    public static <T> List<T> newObj(List b, Class<T> targetClass){
      if(CollectionUtils.isEmpty(b)){
          return null;
      }
      List<T> temp = new ArrayList<>();

      for(Object o:b){
          temp.add(newObj(o,targetClass));
      }
      return temp;
    }
}
