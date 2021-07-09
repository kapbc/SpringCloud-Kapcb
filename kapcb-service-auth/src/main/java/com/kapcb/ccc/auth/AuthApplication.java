package com.kapcb.ccc.auth;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * <a>Title: AuthApplication </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Auth Application <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/6 21:27
 */
@ConfigurationPropertiesScan(basePackages = {"com.kapcb.ccc.*"})
@ComponentScan(basePackages = {"com.kapcb.ccc.*", "kapcb.framework.web.*"})
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(AuthApplication.class)
                .web(WebApplicationType.SERVLET)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}