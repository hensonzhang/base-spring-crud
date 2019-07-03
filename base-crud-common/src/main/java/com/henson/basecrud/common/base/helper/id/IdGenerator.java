package com.henson.basecrud.common.base.helper.id;

import com.henson.basecrud.common.util.UUIDBuild;


public class IdGenerator<T> {

    protected String generator(T t) {
        return UUIDBuild.getUUID();
    }
}
