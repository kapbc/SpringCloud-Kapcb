package com.kapcb.ccc.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <a>Title: UserRolePO </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/28 21:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "access_user_role")
public class AccessUserRolePO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "role_id")
    private Integer roleId;

    @TableField(value = "user_id")
    private Long userId;
}
