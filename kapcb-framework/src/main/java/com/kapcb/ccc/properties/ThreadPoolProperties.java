package com.kapcb.ccc.properties;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
@Component
@ConfigurationProperties(prefix = "kapcb.thread.pool", ignoreInvalidFields = true)
public class ThreadPoolProperties {

//    private int corePoolSize;
//    private int maxPoolSize;
//    private int queueCapacity;
//    private int keepAliveTime;
//    private String threadNamePrefix;
}
