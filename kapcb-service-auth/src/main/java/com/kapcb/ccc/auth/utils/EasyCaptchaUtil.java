package com.kapcb.ccc.auth.utils;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import kapcb.framework.web.constants.enums.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

/**
 * <a>Title: EasyCaptchaUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/11 15:43
 */
@Slf4j
public class EasyCaptchaUtil {

    private EasyCaptchaUtil() {
    }

    public static Captcha createCaptcha(@NonNull String imageType, @NonNull Integer width, @NonNull Integer height, @NonNull Integer length, @NonNull Integer charType) {
        Captcha captcha = matchCaptcha(imageType, width, height, length);
        captcha.setCharType(charType);
        return captcha;
    }

    private static Captcha matchCaptcha(String imageType, Integer width, Integer height, Integer length) {
        return Match(imageType).of(
                Case($(Image.Type.GIF.type()), new GifCaptcha(width, height, length)),
                Case($(Image.Type.GIF.type()), new SpecCaptcha(width, height, length)));
    }
}
