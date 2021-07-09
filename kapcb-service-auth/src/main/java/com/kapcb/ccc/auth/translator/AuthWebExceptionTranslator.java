package com.kapcb.ccc.auth.translator;

import com.kapcb.ccc.auth.model.base.Result;
import kapcb.framework.web.model.base.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

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

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        BaseResult<String> baseResult = new BaseResult<>();
        log.error("error message is : " + e.getMessage());
        return null;
    }

}
