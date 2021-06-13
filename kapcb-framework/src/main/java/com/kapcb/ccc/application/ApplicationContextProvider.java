package com.kapcb.ccc.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
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

    @Nullable
    public static Object getBean(@NonNull String name) {
        return applicationContext.getBean(name);
    }

    @Nullable
    public static <T> T getBean(@NonNull Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @Nullable
    public static <T> T getBean(@NonNull String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}