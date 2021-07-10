package com.kapcb.ccc.auth.handler;

import kapcb.framework.web.model.base.BaseResult;
import kapcb.framework.web.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <a>Title: WebAuthenticationFailureHandler </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 * 登陆失败处理
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/10 23:59
 */
@Slf4j
@Component
public class WebAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        String exceptionMessage = "authentication fail, please connect with administrator!";
        if (exception instanceof BadCredentialsException) {
            exceptionMessage = "username or password error!";
        }
        if (exception instanceof LockedException) {
            exceptionMessage = "user is locked!";
        }
        ResultUtil.setUpFailureResponse(httpServletResponse, BaseResult.fail(exceptionMessage));
    }
}