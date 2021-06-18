package com.kapcb.ccc.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kapcb.ccc.model.base.PageResult;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple4;
import io.vavr.Tuple5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * <a>Title: PageUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/18 20:02
 */
@Slf4j
public class PageUtil {

    private PageUtil() {
    }

    /**
     * user tuple to assemble pagination params
     *
     * @param paginationTuple Tuple5<List<T>, Integer, Integer, Integer, Integer>
     * @param <T>             <T>
     * @return PageResult<T>
     */
    public static <T> PageResult<T> assemblePagination(@NonNull Tuple2<List<T>, Tuple4<Long, Long, Long, Long>> paginationTuple) {
        Tuple4<Long, Long, Long, Long> paginationParamTuple4 = paginationTuple._2;
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setRecords(paginationTuple._1);
        pageResult.setTotal(paginationParamTuple4._1);
        pageResult.setTotalPage(paginationParamTuple4._2);
        pageResult.setPageNum(paginationParamTuple4._3);
        pageResult.setPageSize(paginationParamTuple4._4);
        return pageResult;
    }

    public static <T> PageResult<T> assemblePagination(@NonNull Tuple4<Long, Long, Long, Long> paginationTuple, List<T> resultList) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setRecords(resultList);
        pageResult.setTotal(paginationTuple._1);
        pageResult.setTotalPage(paginationTuple._2);
        pageResult.setPageNum(paginationTuple._3);
        pageResult.setPageSize(paginationTuple._4);
        return pageResult;
    }

    /**
     * records : 查询出来的数据
     * total : 返回数据的总数
     * pages : 总页数
     * size : 每页显示的条数
     * current : 当前页
     *
     * @param page Page<T>
     * @param <T>  <T>
     */
    @NonNull
    private static <T> Tuple constructPagination(@NonNull Page<T> page) {
        return Tuple.of(page.getRecords(), Tuple.of(page.getTotal(), page.getPages(), page.getSize(), page.getCurrent()));
    }

    /**
     * get pagination records
     *
     * @param paginationTuple Tuple5<List<T>, Long, Long, Long, Long>
     * @param <T>             <T>
     * @return List<T>
     */
    @Nullable
    private static <T> List<T> getPaginationRecords(@NonNull Tuple5<List<T>, Long, Long, Long, Long> paginationTuple) {
        return paginationTuple._1;
    }

}
