package com.lunsir.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lunSir
 * @create 2024-09-27 19:38
 */
@SpringBootApplication
@ComponentScan("com.lunsir")
@MapperScan("com.lunsir.**.mapper")
@EnableDiscoveryClient
@EnableTransactionManagement
public class AuthApplication {
    public static void main(String[] args){
        SpringApplication.run(AuthApplication.class,args);
    }
}
