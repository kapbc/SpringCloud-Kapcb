package com.kapcb.ccc.auth.service;

import kapcb.framework.web.exception.VerificationCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <a>Title: VerificationCodeService </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/11 15:07
 */
public interface VerificationCodeService {

    /**
     * 创建验证码
     *
     * @param httpServletRequest  HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return boolean
     * @throws IOException               IOException
     * @throws VerificationCodeException VerificationCodeException
     */
    boolean createVerificationCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, VerificationCodeException;

    /**
     * 校验验证码
     *
     * @param key  String 前端传过来的 key
     * @param code String 用户输入的验证码
     */
    void verifyCode(String key, String code);
}
