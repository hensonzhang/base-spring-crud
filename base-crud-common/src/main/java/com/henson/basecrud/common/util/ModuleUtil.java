package com.henson.basecrud.common.util;

import com.henson.basecrud.common.Module;


public class ModuleUtil {

    public static Module current(){
        try {
            return Module.getModuleByCode(Integer.valueOf(ResourcesUtil.getValue(ResourceConfig.MODULE, "module.code")));
        }catch (Exception e){
            return null;
        }
        }
}
