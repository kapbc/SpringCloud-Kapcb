package com.kapcb.ccc.auth.model.base;

import com.kapcb.ccc.constants.enmus.LongPool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "分页", description = "分页")
public class BasePagination implements Serializable {

    @ApiModelProperty(value = "页数(默认值为1)", required = true)
    private long pageNum = LongPool.ONE.value();

    @ApiModelProperty(value = "每页显示条数(默认值为10)", required = true)
    private long pageSize = LongPool.TEN.value();
}