package com.lunsir.subject.common.enums;

import lombok.Getter;

/**
 * @author lunSir
 * @create 2024-03-02 15:45
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(200,"成功"),
    FAIL(500,"失败");
    private Integer code;
    private String desc;
    ResultCodeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static ResultCodeEnum getByCode(Integer codeVal){

        for(ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()){
            if (resultCodeEnum.getCode().equals(codeVal)){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
