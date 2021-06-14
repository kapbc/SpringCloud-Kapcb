package com.kapcb.ccc.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kapcb.ccc.constants.enmus.ResultCodeEnum;
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

    public Result(@NonNull ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
        this.data = data;
    }

    public Result(@NonNull Integer code, @NonNull String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @NonNull
    public static <T> Result<T> success() {
        return new Result<>(ResultCodeEnum.SUCCESS, null);
    }

    @NonNull
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCodeEnum.SUCCESS, data);
    }

    @NonNull
    public static <T> Result<T> success(String message) {
        return new Result<>(ResultCodeEnum.SUCCESS.getCode(), message, null);
    }

    @NonNull
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCodeEnum.SUCCESS.getCode(), message, data);
    }

    @NonNull
    public static <T> Result<T> fail() {
        return new Result<>(ResultCodeEnum.FAIL, null);
    }

    @NonNull
    public static <T> Result<T> fail(T data) {
        return new Result<>(ResultCodeEnum.FAIL, data);
    }

    @NonNull
    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultCodeEnum.FAIL.getCode(), message, null);
    }

    @NonNull
    public static <T> Result<T> fail(String message, T data) {
        return new Result<T>(ResultCodeEnum.FAIL.getCode(), message, data);
    }

    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum) {
        return new Result<>(resultCodeEnum, null);
    }

    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum, T data) {
        return new Result<>(resultCodeEnum, data);
    }

    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum, String message, T data) {
        return new Result<>(resultCodeEnum.getCode(), message, data);
    }

    public static <T> Result<T> fail(Throwable throwable) {
        return new Result<>(ResultCodeEnum.FAIL.getCode(), throwable.getMessage(), null);
    }

    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum, Throwable throwable) {
        return new Result<>(resultCodeEnum.getCode(), throwable.getMessage(), null);
    }

    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum, Throwable throwable, T data) {
        return new Result<>(resultCodeEnum.getCode(), throwable.getMessage(), data);
    }
}