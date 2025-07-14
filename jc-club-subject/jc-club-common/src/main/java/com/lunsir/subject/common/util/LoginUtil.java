package com.lunsir.subject.common.util;

import com.lunsir.subject.common.context.LoginContextHolder;

/**
 * @author lunSir
 * @create 2024-10-13 12:09
 */
public class LoginUtil {
    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
