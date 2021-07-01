package com.kapcb.ccc.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <a>Title: BaseTree </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/1 22:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseTree<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer parentId;

    private List<T> children;
}
