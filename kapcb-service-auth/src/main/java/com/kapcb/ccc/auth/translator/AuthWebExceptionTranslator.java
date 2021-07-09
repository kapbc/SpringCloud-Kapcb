package com.kapcb.ccc.auth.translator;

import kapcb.framework.web.model.base.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;


import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

/**
 * <a>Title: AuthWebExceptionTranslator </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/9 19:12
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class AuthWebExceptionTranslator implements WebResponseExceptionTranslator {

    private static final String UN_SUPPORTED_GRANT_TYPE_EXCEPTION = "UN_SUPPORTED_GRANT_TYPE_EXCEPTION";
    private static final String INVALID_TOKEN_EXCEPTION = "INVALID_TOKEN_EXCEPTION";
    private static final String INVALID_SCOPE_EXCEPTION = "INVALID_SCOPE_EXCEPTION";
    private static final String REDIRECT_MISMATCH_EXCEPTION = "REDIRECT_MISMATCH_EXCEPTION";
    private static final String BAD_CLIENT_CREDENTIALS_EXCEPTION = "BAD_CLIENT_CREDENTIALS_EXCEPTION";
    private static final String UNSUPPORTED_RESPONSE_TYPE_EXCEPTION = "UNSUPPORTED_RESPONSE_TYPE_EXCEPTION";

    private static final String INVALID_REFRESH_TOKEN = "Invalid refresh token";
    private static final String INVALID_AUTHORIZATION_CODE = "Invalid authorization code";
    private static final String LOCKED = "Locked";
    private static final String USERNAME_OR_PASSWORD_ERROR = "USERNAME_OR_PASSWORD_ERROR";
    private static final String DEFAULT_MESSAGE = "DEFAULT_MESSAGE";

    @Override
    public ResponseEntity translate(Exception exception) throws Exception {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        BaseResult<String> baseResult = new BaseResult<>();
        String message = exception.getMessage();
        log.error("error message is : " + message);
        if (exception instanceof UnsupportedGrantTypeException) {
            // 不支持的认证类型
            return status.body(baseResult.fail(getResponseMessage(UN_SUPPORTED_GRANT_TYPE_EXCEPTION, message)));
        }
        if (exception instanceof InvalidTokenException && StringUtils.equalsAnyIgnoreCase(exception.getMessage(), "Invalid refresh token (expired)")) {
            // 刷新令牌已过期, 请重新登录
            return status.body(baseResult.fail(getResponseMessage(INVALID_TOKEN_EXCEPTION, message)));
        }
        if (exception instanceof InvalidScopeException) {
            // 不是有效的 scope 值
            return status.body(baseResult.fail(getResponseMessage(INVALID_SCOPE_EXCEPTION, message)));
        }
        if (exception instanceof RedirectMismatchException) {
            // redirect uri 的值不正确
            return status.body(baseResult.fail(getResponseMessage(REDIRECT_MISMATCH_EXCEPTION, message)));
        }
        if (exception instanceof BadClientCredentialsException) {
            // client 不合法
            return status.body(baseResult.fail(getResponseMessage(BAD_CLIENT_CREDENTIALS_EXCEPTION, message)));
        }
        if (exception instanceof UnsupportedResponseTypeException) {
            // 不是合法的 response type 值
            return status.body(baseResult.fail(getResponseMessage(UNSUPPORTED_RESPONSE_TYPE_EXCEPTION, message)));
        }
        if (exception instanceof InvalidGrantException) {
            if (StringUtils.equalsAnyIgnoreCase(message, INVALID_REFRESH_TOKEN)) {
                // 授权码错误// refresh token 无效
                return status.body(baseResult.fail(getResponseMessage(INVALID_REFRESH_TOKEN, message)));
            }
            if (StringUtils.equalsAnyIgnoreCase(message, INVALID_AUTHORIZATION_CODE)) {
                // 授权码错误
                return status.body(baseResult.fail(getResponseMessage(INVALID_AUTHORIZATION_CODE, message)));
            }
            if (StringUtils.equalsAnyIgnoreCase(message, LOCKED)) {
                // 用户账号被锁定
                return status.body(baseResult.fail(getResponseMessage(LOCKED, message)));
            }
            // 用户名或密码错误
            return status.body(baseResult.fail(getResponseMessage(USERNAME_OR_PASSWORD_ERROR, message)));
        }
        // 认证失败
        return status.body(baseResult.fail(getResponseMessage(DEFAULT_MESSAGE, message)));
    }

    private static String getResponseMessage(String type, String exceptionMessage) {
        return Match(type).of(
                // 不支持的认证类型
                Case($(UN_SUPPORTED_GRANT_TYPE_EXCEPTION), "Un support authentication type!"),
                // 刷新令牌已过期, 请重新登录
                Case($(INVALID_TOKEN_EXCEPTION), "Invalid refresh token, please log back in!"),
                // 不是有效的 scope 值
                Case($(INVALID_SCOPE_EXCEPTION), "Invalid scope value!"),
                // redirect uri 的值不正确
                Case($(REDIRECT_MISMATCH_EXCEPTION), "Redirect uri is mismatch!"),
                // client 不合法
                Case($(BAD_CLIENT_CREDENTIALS_EXCEPTION), "Client is invalid!"),
                // 不是合法的 response type 值
                Case($(UNSUPPORTED_RESPONSE_TYPE_EXCEPTION), StringUtils.substringBetween(exceptionMessage, "[", "]") + " is invalid response type!"),
                // refresh token 无效
                Case($(INVALID_REFRESH_TOKEN), INVALID_REFRESH_TOKEN),
                // 授权码错误
                Case($(INVALID_AUTHORIZATION_CODE), "Invalid authorization code : " + StringUtils.substringAfterLast(exceptionMessage, ": ")),
                // 用户账号被锁定
                Case($(LOCKED), "User account is locked, please connection with patform admin!"),
                // 用户名或密码错误
                Case($(USERNAME_OR_PASSWORD_ERROR), "Input username or password error!"),
                // 认证失败
                Case($(DEFAULT_MESSAGE), "Authentication fail!")
        );
    }
}