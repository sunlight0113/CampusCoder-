package com.lunsir.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio配置类
 * @author lunSir
 * @create 2024-09-11 21:38
 */
@Configuration
public class MinioConfig {
    // minio访问的url
    @Value("${minio.url}")
    private String url;
    // minio账户
    @Value("${minio.accessKey}")
    private String accessKey;
    // minio密码
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient getMinioClient(){
      return  MinioClient.builder().endpoint(url).credentials(accessKey,secretKey).build();
    }
}
