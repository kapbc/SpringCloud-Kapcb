package com.kapcb.ccc.auth.filter;

import com.kapcb.ccc.auth.service.VerificationCodeService;
import kapcb.framework.web.constants.enums.RequestParamEnum;
import kapcb.framework.web.exception.VerificationCodeException;
import kapcb.framework.web.model.base.BaseResult;
import kapcb.framework.web.util.ResultUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <a>Title: VerificationCodeFilter </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/11 0:44
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class VerificationCodeFilter extends OncePerRequestFilter {

    private final VerificationCodeService verificationCodeService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("request authorization is {}", header);
        RequestMatcher requestMatcher = new AntPathRequestMatcher("auth_token", HttpMethod.POST.name());
        log.info("the request matcher is {} ", requestMatcher);
        boolean matches = requestMatcher.matches(httpServletRequest);
        boolean password = StringUtils.equalsAnyIgnoreCase(httpServletRequest.getParameter(RequestParamEnum.AUTHENTICATION_GRANT_TYPE.value()), "password");
        if (matches && password) {
            try {
                validationCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (Exception e) {
                ResultUtil.setUpFailureResponse(httpServletResponse, BaseResult.fail(e.getMessage()));
                log.error(e.getMessage(), e);
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private void validationCode(HttpServletRequest httpServletRequest) throws VerificationCodeException {
        verificationCodeService.verifyCode(httpServletRequest.getParameter(RequestParamEnum.VERIFICATION_CODE_KEY.value()), httpServletRequest.getParameter(RequestParamEnum.VERIFICATION_CODE_CODE.value()));
    }
}
