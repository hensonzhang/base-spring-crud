package com.henson.basecrud.common.base.enums;

public enum OpEnum {
    EQ("等于", "eq"),
    N_EQ("不等于", "n_eq"),
    LT("小于", "lt"),
    GT("大于", "gt"),
    LIKE("模糊匹配", "like");

    private String name;
    private String code;


    OpEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }


}
