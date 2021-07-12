package com.kapcb.ccc.auth.utils;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.vavr.Tuple5;
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

    public static Captcha createCaptcha(@NonNull Tuple5<String, Integer, Integer, Integer, Integer> codeToTuple) {
        Captcha captcha = matchCaptcha(codeToTuple);
        captcha.setCharType(codeToTuple._5);
        return captcha;
    }

    private static Captcha matchCaptcha(Tuple5<String, Integer, Integer, Integer, Integer> codeToTuple) {
        return Match(codeToTuple._1).of(
                Case($(Image.Type.GIF.type()), new GifCaptcha(codeToTuple._2, codeToTuple._3, codeToTuple._4)),
                Case($(Image.Type.GIF.type()), new SpecCaptcha(codeToTuple._2, codeToTuple._3, codeToTuple._4)));
    }
}
