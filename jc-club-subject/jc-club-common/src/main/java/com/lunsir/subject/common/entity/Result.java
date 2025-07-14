package com.lunsir.subject.common.entity;

import com.google.common.base.Preconditions;
import com.lunsir.subject.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * @author lunSir
 * @create 2024-03-02 15:43
 */
@Data
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T date;

    public static <T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        result.setDate(data);
        result.setSuccess(true);
        return result;
    }
    public static Result ok(){
        Result result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> fail(T data){
        Result<T> result = new Result();
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        result.setDate(data);
        result.setSuccess(false);
        return result;
    }
    public static <T> Result<T> fail(T data,String message){
        Result<T> result = new Result();
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(message);
        result.setDate(data);
        result.setSuccess(false);
        return result;
    }
    public static Result fail(){
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        return result;
    }

}
