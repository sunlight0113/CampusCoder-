package com.lunsir.config;

import com.lunsir.service.StorageAdapter;
import com.lunsir.service.impl.AliyunStorageAdapter;
import com.lunsir.service.impl.MinioStorageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author lunSir
 * @create 2024-09-16 21:09
 */
@Slf4j
@RefreshScope
@Configuration
public class StorageConfig {

    @Value("${lunsir.storage.type}")
    private String storageType;

    @Bean
    @RefreshScope
    //@ConditionalOnProperty(prefix = "lunsir.storage",name = "type",havingValue = "minio")
    public StorageAdapter MinioStorageService(){
        if("minio".equals(storageType)){
            log.info("minio storage already is deployed !");
            return new MinioStorageAdapter();
        }else if("aliyun".equals(storageType)){
            log.info("aliyun storage already is deployed !");
            return new AliyunStorageAdapter();
        }else {
            throw new IllegalArgumentException("lunsir.storage.type可能为空！也可能值不是 minio或者aliyun");
        }
    }


//    @Bean
//    @RefreshScope
//    @ConditionalOnProperty(prefix = "lunsir.storage",name = "type",havingValue = "aliyun")
//    public StorageAdapter AliyunStorageService(){
//        log.info("aliyun storage already is deployed !");
//        return new AliyunStorageAdapter();
//    }
}
