package com.lunsir.subject.common.context;



import org.apache.commons.collections4.MapUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author lunSir
 * @create 2024-10-10 21:28
 */
public class LoginContextHolder {
    public static final InheritableThreadLocal<Map<String,Object>> THREADLOCAL =
            new InheritableThreadLocal();

    /**
     * 获取ThreadLocal中的map
     * @return
     */
    public static Map<String,Object> getThreadLocalMap(){
        Map<String, Object> map = THREADLOCAL.get();
        if (MapUtils.isEmpty(map)){
            // ConcurrentHashMap 保证线程安全
            map = new ConcurrentHashMap<>();
            THREADLOCAL.set(map);
        }
        return map;
    }

    public static Object get(String key){
        return getThreadLocalMap().get(key);
    }

    public static void put(String key,Object val){
        getThreadLocalMap().put(key,val);
    }

    public static void remove(){
        THREADLOCAL.remove();
    }

    /**
     * 封装灵活的方法
     */
    public static String getLoginId(){
        Map<String, Object> localMap = getThreadLocalMap();
        Object loginId = localMap.get("loginId");
        if (Objects.isNull(loginId)){
            return null;
        }
        return (String) loginId;
    }

}
