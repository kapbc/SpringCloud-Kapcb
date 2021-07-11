package com.kapcb.ccc.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <a>Title: AuthProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/10 0:45
 */
@Data
@ConfigurationProperties(prefix = "kapcb.auth")
public class AuthProperties {

    private Boolean enableJwt = Boolean.FALSE;

    private String jwtAccessKey;

    private VerificationCodeProperties verificationCode = new VerificationCodeProperties();
}
