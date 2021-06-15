package com.kapcb.ccc.configuration;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapcb.ccc.aspect.ControllerLogAspect;
import com.kapcb.ccc.properties.KapcbLogProperties;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <a>Title: KapcbLogAutoConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/14 19:41
 */
@Configuration
@EnableConfigurationProperties(KapcbLogProperties.class)
public class KapcbLogAutoConfiguration {

    private static final LoggerContext LOGGER_CONTEXT;
    private static final Logger ROOT_LOGGER;

    static {
        LOGGER_CONTEXT = (LoggerContext) LoggerFactory.getILoggerFactory();
        ROOT_LOGGER = LOGGER_CONTEXT.getLogger("ROOT");
    }

    private final KapcbLogProperties kapcbLogProperties;

    @Value("${spring.application.name:unknown}")
    private String applicationName;

    @Autowired
    public KapcbLogAutoConfiguration(KapcbLogProperties kapcbLogProperties) {
        this.kapcbLogProperties = kapcbLogProperties;
    }

    @Bean
    @ConditionalOnProperty(name = "kapcb.log.enable-log-for-controller", havingValue = "true")
    public ControllerLogAspect controllerLogAspect() {
        return new ControllerLogAspect();
    }

    @Bean
    @ConditionalOnProperty(name = "kapcb.log.enable-elk", havingValue = "true")
    public void enableElk() throws JsonProcessingException {
        LogstashTcpSocketAppender logstashTcpSocketAppender = new LogstashTcpSocketAppender();
        LogstashEncoder logstashEncoder = new LogstashEncoder();
        Map<String, String> customFields = new HashMap<>(2);
        customFields.put("application-name", applicationName);
        String customFieldsString = new ObjectMapper().writeValueAsString(customFields);
        logstashEncoder.setCustomFields(customFieldsString);

        logstashTcpSocketAppender.setEncoder(logstashEncoder);
        logstashTcpSocketAppender.addDestination(kapcbLogProperties.getLogStashHost());
        logstashTcpSocketAppender.setName("logstash[" + applicationName + "]");
        logstashTcpSocketAppender.start();
        logstashTcpSocketAppender.setContext(LOGGER_CONTEXT);
        ROOT_LOGGER.addAppender(logstashTcpSocketAppender);
    }
    
}
