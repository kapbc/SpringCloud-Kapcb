package com.kapcb.ccc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <a>Title: KapcbLogProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/14 19:37
 */
@Data
@ConfigurationProperties(prefix = "kapcb.logstash")
public class KapcbLogProperties {

    /**
     * logstash的日志上穿地址
     */
    private String logStashHost = "127.0.0.1:4560";

    /**
     * 是否开启controller层api调用的日志
     */
    private Boolean enableLogForController=false;

    /**
     * 是否开启ELK日志收集
     */
    private Boolean enableElkLogstash=false;
}