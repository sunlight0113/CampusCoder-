package com.lunsir.auth.common.enums;

import lombok.Getter;

/**
 * @author lunSir
 * @create 2024-03-02 15:45
 */
@Getter
public enum StateFlagEnum {
    Start(0,"启用"),
    END(1,"禁用");
    private Integer code;
    private String desc;
    StateFlagEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static StateFlagEnum getByCode(Integer codeVal){

        for(StateFlagEnum resultCodeEnum : StateFlagEnum.values()){
            if (resultCodeEnum.getCode().equals(codeVal)){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
