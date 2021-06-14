package com.kapcb.ccc.runner;

import com.kapcb.ccc.utils.KapcbUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * <a>Title: BaseApplicationRunner </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 22:28
 */
@Slf4j
@Component
public class BaseApplicationRunner implements ApplicationRunner {

    private final Environment environment;
    private final ConfigurableApplicationContext applicationContext;

    @Autowired
    public BaseApplicationRunner(@Qualifier("environment") Environment environment, @Qualifier("configurableApplicationContext") ConfigurableApplicationContext configurableApplicationContext) {
        this.environment = environment;
        this.applicationContext = configurableApplicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (applicationContext.isActive()) {
            KapcbUtil.serverStartUpBanner(environment);
        }
    }
}