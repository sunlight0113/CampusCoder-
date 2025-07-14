package com.lunsir.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.DigestUtils;

/**
 * @author lunSir
 * @create 2024-02-29 20:57
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.lunsir")
@MapperScan("com.lunsir.**.dao")
@EnableFeignClients(basePackages = "com.lunsir")
public class ClubSubjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClubSubjectApplication.class,args);
    }
}
