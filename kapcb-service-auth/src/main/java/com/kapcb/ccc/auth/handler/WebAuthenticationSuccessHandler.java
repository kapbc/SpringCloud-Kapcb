package com.kapcb.ccc.auth.handler;

import kapcb.framework.web.model.base.BaseResult;
import kapcb.framework.web.util.RequestUtil;
import kapcb.framework.web.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <a>Title: WebAuthenticationSuccessHandler </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/11 0:20
 */
@Slf4j
@Component
public class WebAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * redirect to original user access url
     *
     * @param httpServletRequest  HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @param authentication      Authentication
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
        HttpSession session = httpServletRequest.getSession();
        if (session != null) {
            // 跳转到登录页的地址
            Object attribute = session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            log.info("redirect to user login page's url is {} ", attribute);
        }
        // ajax request
        if (RequestUtil.ajaxRequest(httpServletRequest)) {
            if (savedRequest == null) {
                // 请通过授权码模式跳转到该页面
                ResultUtil.setUpFailureResponse(httpServletResponse, BaseResult.fail("please access to this page by Authorization code!"));
                return;
            }
            ResultUtil.setUpSuccessResponse(httpServletResponse, BaseResult.success(savedRequest.getRedirectUrl()));
        } else {
            if (savedRequest == null) {
                super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
                return;
            }
            // request中移除 Authentication Attributes
            clearAuthenticationAttributes(httpServletRequest);
            // redirect to user page before login
            getRedirectStrategy().sendRedirect(httpServletRequest, httpServletResponse, savedRequest.getRedirectUrl());
        }
    }
}
