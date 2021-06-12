package com.kapcb.ccc.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <a>Title: BasePagination </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Base Pagination <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 23:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasePagination implements Serializable {

    private int pageNum = 1;
    private int pageSize = 10;

}
