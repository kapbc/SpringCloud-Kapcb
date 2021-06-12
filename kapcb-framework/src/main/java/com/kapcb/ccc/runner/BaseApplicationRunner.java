package com.kapcb.ccc.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
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
@RequiredArgsConstructor
public class BaseApplicationRunner implements ApplicationRunner {

    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
