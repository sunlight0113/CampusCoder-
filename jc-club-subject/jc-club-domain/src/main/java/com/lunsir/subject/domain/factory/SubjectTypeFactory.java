package com.lunsir.subject.domain.factory;

import com.google.common.collect.Maps;
import com.lunsir.subject.common.enums.SubjectTypeEnum;
import com.lunsir.subject.domain.factory.handler.subject.SubjectTypeAdapter;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工厂类
 * @author lunSir
 * @create 2024-03-03 17:23
 */
@Component
public class SubjectTypeFactory implements InitializingBean {
    @Resource
    private List<SubjectTypeAdapter> subjectList;
    // 工厂对象
    private Map<SubjectTypeEnum,SubjectTypeAdapter> subjectTypeMap = Maps.newHashMap();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.subjectList.forEach(e->{
            this.subjectTypeMap.put(e.isSupported(),e);
        });
    }

    public SubjectTypeAdapter getObject(SubjectTypeEnum subjectTypeEnum){
        if(subjectTypeEnum == null) return null;
        return this.subjectTypeMap.get(subjectTypeEnum);
    }
}
