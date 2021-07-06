package com.kapcb.ccc.auth.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kapcb.ccc.constants.enmus.ResultStatus;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <a>Title: Result </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 21:12
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "响应结果")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * status code
     */
    private Integer code;

    /**
     * response message
     */
    private String msg;

    /**
     * data
     */
    private T data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssS")
    private LocalDateTime time = LocalDateTime.now();

    public Result() {
    }

    public Result(@NonNull ResultStatus resultCodeEnum, T data) {
        this.code = resultCodeEnum.value();
        this.msg = resultCodeEnum.reasonPhrase();
        this.data = data;
    }

    public Result(@NonNull Integer code, @NonNull String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @NonNull
    public static <T> Result<T> success() {
        return new Result<>(ResultStatus.SUCCESS, null);
    }

    @NonNull
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultStatus.SUCCESS, data);
    }

    @NonNull
    public static <T> Result<T> success(String message) {
        return new Result<>(ResultStatus.SUCCESS.value(), message, null);
    }

    @NonNull
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultStatus.SUCCESS.value(), message, data);
    }

    @NonNull
    public static <T> Result<T> fail() {
        return new Result<>(ResultStatus.FAIL, null);
    }

    @NonNull
    public static <T> Result<T> fail(T data) {
        return new Result<>(ResultStatus.FAIL, data);
    }

    @NonNull
    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultStatus.FAIL.value(), message, null);
    }

    @NonNull
    public static <T> Result<T> fail(String message, T data) {
        return new Result<T>(ResultStatus.FAIL.value(), message, data);
    }

    public static <T> Result<T> fail(ResultStatus resultCodeEnum) {
        return new Result<>(resultCodeEnum, null);
    }

    public static <T> Result<T> fail(ResultStatus resultCodeEnum, T data) {
        return new Result<>(resultCodeEnum, data);
    }

    public static <T> Result<T> fail(ResultStatus resultCodeEnum, String message, T data) {
        return new Result<>(resultCodeEnum.value(), message, data);
    }

    public static <T> Result<T> fail(Throwable throwable) {
        return new Result<>(ResultStatus.FAIL.value(), throwable.getMessage(), null);
    }

    public static <T> Result<T> fail(ResultStatus resultCodeEnum, Throwable throwable) {
        return new Result<>(resultCodeEnum.value(), throwable.getMessage(), null);
    }

    public static <T> Result<T> fail(ResultStatus resultCodeEnum, Throwable throwable, T data) {
        return new Result<>(resultCodeEnum.value(), throwable.getMessage(), data);
    }
}