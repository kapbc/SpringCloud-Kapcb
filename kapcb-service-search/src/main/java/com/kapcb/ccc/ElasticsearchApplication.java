package com.kapcb.ccc;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * <a>Title: ElasticsearchApplication </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/25 22:32
 */
@SpringCloudApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ElasticsearchApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }
    
}