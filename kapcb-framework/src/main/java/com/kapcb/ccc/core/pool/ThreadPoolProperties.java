package com.kapcb.ccc.core.pool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <a>Title: ThreadPoolProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/18 19:44
 */
@Data
@Builder
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@EnableAutoConfiguration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "kapcb.thread.pool")
public class ThreadPoolProperties {

    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
    private int keepAliveTime;
    private String threadNamePrefix;
}
