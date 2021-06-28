package com.kapcb.ccc.model.po;

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
public class UserRolePO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Long userId;
}
