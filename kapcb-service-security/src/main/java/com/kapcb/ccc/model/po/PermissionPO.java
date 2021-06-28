package com.kapcb.ccc.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <a>Title: PermissionPO </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/28 21:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionPO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer permissionId;
}
