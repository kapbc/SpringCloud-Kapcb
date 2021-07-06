package com.kapcb.ccc.auth.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <a>Title: RolePO </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/28 21:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "access_role")
public class RolePO implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer roleId;

    private String roleName;

    private Integer roleSort;

    private Integer dataScope;

    private String description;

    private LocalDateTime createDate;

    private Long createUser;

    private LocalDateTime updateDate;

    private Long updateUser;

    private Boolean deleted;

    private Integer version;
}
