package com.lunsir.subject.domain.config;

import com.zaxxer.hikari.util.UtilityElf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author lunSir
 * @create 2024-10-09 20:55
 */
@Configuration
public class TreadPoolConfig {

    /**
     * 查询分类标签的线程池
     * @return
     */
    @Bean(name = "labelThreadPool")
    public ThreadPoolExecutor getLabelThreadPool(){
        //要求： corePoolSize <= MaximumPoolSize，不然会报错
        return new ThreadPoolExecutor(20,
                100,
                5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(40),
                new CustomNameThreadFactory("label"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
