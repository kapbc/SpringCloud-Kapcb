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

    private Integer code;

    private String msg;

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
        return new Result<T>(ResultCodeEnum.SUCCESS, null);
    }

    @NonNull
    public static <T> Result<T> success(T data){
        return new Result<T>(ResultCodeEnum.SUCCESS, data);
    }

    @NonNull
    public static <T> Result<T> success(String message){
        return new Result<T>(ResultCodeEnum.SUCCESS.getCode(), message, null);
    }

    @NonNull
    public static <T> Result<T> success(String message,T data){
        return new Result<T>(ResultCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> fail(){
        
    }

}
