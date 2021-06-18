package com.kapcb.ccc.model.base;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <a>Title: PageResult </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/18 20:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页响应结果")
public class PageResult<T> extends BasePagination implements Serializable {

    private static final long serialVersionUID = 1L;

    private long total;

    private long totalPage;

    private List<T> records;
}
