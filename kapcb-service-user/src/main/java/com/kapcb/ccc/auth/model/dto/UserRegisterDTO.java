package com.kapcb.ccc.auth.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <a>Title: UserRegisterDTO </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/5 22:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ApiModel(value = "用户注册", description = "用户注册")
public class UserRegisterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "登陆密码")
    private String password;
}
