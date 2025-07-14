package com.lunsir.auth.common.enums;

import lombok.Getter;

/**
 * @author lunSir
 * @create 2024-03-02 15:45
 */
@Getter
public enum DeletedFlagEnum {
    DELETED(1,"已删除"),
    NOT_DELETED(0,"未删除");
    private Integer code;
    private String desc;
    DeletedFlagEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static DeletedFlagEnum getByCode(Integer codeVal){

        for(DeletedFlagEnum resultCodeEnum : DeletedFlagEnum.values()){
            if (resultCodeEnum.getCode().equals(codeVal)){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
