package com.kapcb.ccc.handler;

import com.kapcb.ccc.constants.CoreConstant;
import com.kapcb.ccc.constants.enmus.ResultCodeEnum;
import com.kapcb.ccc.exception.BusinessException;
import com.kapcb.ccc.exception.CoreException;
import com.kapcb.ccc.model.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.nio.file.AccessDeniedException;
import java.util.Set;

/**
 * <a>Title: BaseGlobalExceptionHandler </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Base Global ExceptionHandler <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 21:07
 */
@Slf4j
@RestControllerAdvice
public class BaseGlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public Result handlerException(Exception e) {
        log.error("handlerException : " + e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(value = {CoreException.class})
    public Result handlerSystemException(CoreException e) {
        log.error("handler CoreException : ", e);
        return Result.fail(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = {BusinessException.class})
    public Result handlerBusinessException(BusinessException e) {
        return Result.fail(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(value = {BindException.class})
    public Result handlerBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(" ,");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error("handler BindException : " + message.toString());
        return Result.fail(message.toString(), ResultCodeEnum.PARAMETER_VALIDATION_FAIL.getCode());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public Result handlerConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArray[1]).append(violation.getMessage()).append((" ,"));
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error("handler ConstraintViolationException : " + message.toString());
        return Result.fail(message.toString(), ResultCodeEnum.PARAMETER_VALIDATION_FAIL.getCode());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(CoreConstant.StringPool.SPACE_COMMA.value());
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error("handler MethodArgumentNotValidException : " + message.toString());
        return Result.fail(message.toString(), ResultCodeEnum.PARAMETER_VALIDATION_FAIL.getCode());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public Result handlerAccessDeniedException(AccessDeniedException e) {
        log.error("handler AccessDeniedException : " + e.getMessage());
        return Result.fail("No access Permission", HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public Result handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        StringBuilder message = new StringBuilder();
        message.append("target method not support [").append(StringUtils.substringBetween(e.getMessage(), CoreConstant.StringPool.SINGLE_QUOTES.value(), CoreConstant.StringPool.SINGLE_QUOTES.value())).append("] ").append("media type");
        log.error("handler HttpMediaTypeNotSupportedException : " + message.toString());
        return Result.fail(message.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    public Result handlerHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        StringBuilder message = new StringBuilder();
        message.append("target method not accept [").append(StringUtils.substringBetween(e.getMessage(), CoreConstant.StringPool.SINGLE_QUOTES.value(), CoreConstant.StringPool.SINGLE_QUOTES.value())).append("] ").append(" method request");
        log.error("handler HttpMediaTypeNotAcceptableException : " + message.toString());
        return Result.fail(message.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = {AuthException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handlerAuthException(AuthException e) {
        log.error("handler AuthException : " + e.getMessage());
        return Result.fail(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }
}