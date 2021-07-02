package com.kapcb.ccc.utils;

import com.kapcb.ccc.constants.enmus.ResultStatus;
import com.kapcb.ccc.model.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

/**
 * <a>Title: ResultUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/2 22:51
 */
@Slf4j
public class ResultUtil {

    private ResultUtil() {
    }

    @Nullable
    public static <T> T checkAndGet(Result<T> result) {
        if (ResultStatus.SUCCESS.value().equals(result.getCode()) && result.getData() != null) {
            return result.getData();
        }
        return null;
    }
}
