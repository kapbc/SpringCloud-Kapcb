package com.kapcb.ccc.core.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <a>Title: KapcbThreadPool </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Kapcb Thread Pool <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 21:09
 */
@Slf4j
@Configuration
public class KapcbThreadPool {

    @Bean("kapcbThreadPoolTaskExecutor")
    @ConditionalOnClass(value = {ThreadPoolProperties.class})
    public ThreadPoolTaskExecutor kapcbThreadPoolTaskExecutor(@Autowired ThreadPoolProperties threadPoolProperties) {
        log.info("begin to init kapcb thread pool task executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        executor.setThreadNamePrefix(threadPoolProperties.getThreadNamePrefix());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }
}