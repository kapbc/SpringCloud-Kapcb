package com.kapcb.ccc.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;

/**
 * <a>Title: KapcbUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/14 13:18
 */
@Slf4j
public class KapcbUtil {

    private KapcbUtil() {
    }

    public static void serverStartUpBanner(Environment environment) {
        String banner = "----------------------------------------------------------------------------------\n" +
                "服务启动成功，时间：" + LocalDateTime.now() + "\n" +
                "服务名称：" + environment.getProperty("spring.application.name") + "\n" +
                "端口号：" + environment.getProperty("server.port") + "\n" +
                "----------------------------------------------------------------------------------";
    }
}