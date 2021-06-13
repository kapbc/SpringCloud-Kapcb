package com.kapcb.ccc.exception;

/**
 * <a>Title: BusinessException </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 21:11
 */
public class BusinessException extends CoreException{

    private static final long serialVersionUID=1L;

    private int code;

    public int getCode(){
        return this.code;
    }
}
