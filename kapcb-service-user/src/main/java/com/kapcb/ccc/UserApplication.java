package com.kapcb.ccc;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * <a>Title: UserApplication </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/25 22:55
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.kapcb.ccc"})
public class UserApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(UserApplication.class)
                .web(WebApplicationType.REACTIVE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}