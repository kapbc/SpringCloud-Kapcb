package com.kapcb.ccc.configuration;

import com.kapcb.ccc.handler.impl.UserOperationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

/**
 * <a>Title: UserRouterConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/5 22:15
 */
@Slf4j
@Configuration
public class UserRouterConfiguration {


    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserOperationHandler userOperationHandler){
        route()
    }
}
