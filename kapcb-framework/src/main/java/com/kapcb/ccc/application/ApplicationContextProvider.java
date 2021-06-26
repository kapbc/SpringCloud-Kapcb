package com.kapcb.ccc.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * <a>Title: ApplicationContextProvider </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Application Context Provider <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 21:14
 */
@Slf4j
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Objects.isNull(ApplicationContextProvider.applicationContext)) {
            ApplicationContextProvider.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextProvider.applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}