package com.kapcb.ccc.auth.utils;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.vavr.Tuple4;
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

    /**
     * Tuple4:
     * Width
     * Height
     * Length
     * CharType
     *
     * @param type      String
     * @param codeTuple Tuple4
     * @return Captcha
     */
    public static Captcha createCaptcha(String type, Tuple4<Integer, Integer, Integer, Integer> codeTuple) {
        Captcha captcha = matchCaptcha(type, codeTuple);
        captcha.setCharType(codeTuple._4);
        return captcha;
    }

    private static Captcha matchCaptcha(String type, Tuple4<Integer, Integer, Integer, Integer> codeTuple) {
        return Match(type).of(
                Case($(Image.Type.GIF.type()), new GifCaptcha(codeTuple._1, codeTuple._2, codeTuple._3)),
                Case($(Image.Type.GIF.type()), new SpecCaptcha(codeTuple._1, codeTuple._2, codeTuple._3)));
    }
}
