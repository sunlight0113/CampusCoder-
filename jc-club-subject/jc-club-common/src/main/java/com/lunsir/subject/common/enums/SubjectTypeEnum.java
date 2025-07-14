package com.lunsir.subject.common.enums;

import lombok.Getter;

/**
 * @author lunSir
 * @create 2024-03-02 15:45
 */
@Getter
public enum SubjectTypeEnum {
    RADIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答");
    private Integer type;
    private String desc;
    SubjectTypeEnum(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public static SubjectTypeEnum getByType(Integer codeVal){

        for(SubjectTypeEnum resultCodeEnum : SubjectTypeEnum.values()){
            if (resultCodeEnum.getType().equals(codeVal)){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
