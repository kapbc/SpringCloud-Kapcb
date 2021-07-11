package com.kapcb.ccc.auth.properties;

import kapcb.framework.web.constants.enums.Image;
import lombok.Data;

/**
 * <a>Title: VerificationCodeProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 * 验证码的长宽高时间配置
 * 详细配置可参照{@link com.wf.captcha.utils.CaptchaUtil}
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/11 15:11
 */
@Data
public class VerificationCodeProperties {

    /**
     * 验证码的有效时间, 秒
     */
    private Long time = 120L;

    /**
     * 验证码类型, 可选值 png 和 gif
     */
    private String type = Image.Type.PNG.type();

    /**
     * 图片宽度, px
     * {@link com.wf.captcha.utils.CaptchaUtil}
     */
    private Integer width = 130;

    /**
     * 图片高度, px
     * {@link com.wf.captcha.utils.CaptchaUtil}
     */
    private Integer height = 48;

    /**
     * 验证码位数
     * {@link com.wf.captcha.utils.CaptchaUtil}
     */
    private Integer length = 4;

    /**
     * 验证码值的类型
     * 1. 数字加字母
     * 2. 纯数字
     * 3. 纯字母
     */
    private Integer charType = 2;
}