package com.kapcb.ccc.auth.service.impl;

import com.kapcb.ccc.auth.properties.AuthProperties;
import com.kapcb.ccc.auth.properties.VerificationCodeProperties;
import com.kapcb.ccc.auth.service.VerificationCodeService;
import com.kapcb.ccc.auth.utils.EasyCaptchaUtil;
import com.wf.captcha.base.Captcha;
import kapcb.framework.web.constants.enums.RequestParamEnum;
import kapcb.framework.web.exception.VerificationCodeException;
import kapcb.framework.web.service.RedisService;
import kapcb.framework.web.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <a>Title: VerificationCodeServiceImpl </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 * 验证码生成与校验
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/11 15:06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final RedisService redisService;
    private final AuthProperties authProperties;

    @Override
    public boolean createVerificationCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, VerificationCodeException {
        String verificationCode = httpServletRequest.getParameter(RequestParamEnum.VERIFICATION_CODE_KEY.value());
        log.info("the verification code is : " + verificationCode);
        if (StringUtils.isBlank(verificationCode)) {
            throw new VerificationCodeException("verification code can not be null");
        }
        VerificationCodeProperties verificationCodeProperties = authProperties.getVerificationCode();
        ResponseUtil.setHeader(httpServletResponse, verificationCodeProperties.getType());

        Captcha captcha = EasyCaptchaUtil.createCaptcha(verificationCodeProperties.getType(), verificationCodeProperties.getWidth(), verificationCodeProperties.getHeight(), verificationCodeProperties.getLength(), verificationCodeProperties.getCharType());
        redisService.set("verification" + verificationCode, StringUtils.lowerCase(captcha.text()), verificationCodeProperties.getTime());
        captcha.out(httpServletResponse.getOutputStream());
        return true;
    }

    @Override
    public void verifyCode(String key, @Nullable String code) {
        if (StringUtils.isBlank(code)) {
            throw new VerificationCodeException("Please input the verification code!");
        }
        String verificationCode = (String) redisService.get(false, 0, "verification" + key);
        if (verificationCode == null) {
            throw new VerificationCodeException("The verification code is expired!");
        }
        if (!StringUtils.equalsAnyIgnoreCase(verificationCode, code)) {
            throw new VerificationCodeException("Invalid verification code!");
        }
    }
}
