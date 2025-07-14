package com.lunsir.subject.common.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author lunSir
 * @create 2024-10-14 14:29
 */
@Getter
public enum SubjectLikedStateEnum {
    UNLIKE(0,"未点赞"),
    LIKE(1,"点赞");

    private Integer code;

    private String desc;

    SubjectLikedStateEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static SubjectLikedStateEnum getSubjectLikedStateEnumByCode(Integer code){

        for (SubjectLikedStateEnum stateEnum : SubjectLikedStateEnum.values()) {
            if (Objects.equals(stateEnum.getCode(), code)){
                return stateEnum;
            }
        }
        throw new RuntimeException("states的值不合法！！！");
    }


}
