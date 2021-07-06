package com.kapcb.ccc.auth.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.Date;
import java.util.List;

/**
 * <a>Title: MybatisPlusSqlDataVO </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Mybatis Plus Sql Data VO <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 22:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MybatisPlusSqlDataVO {

    /**
     * 触发时间
     */
    private Date triggerTime;

    /**
     * 来源服务名称
     */
    private String serverName;

    /**
     * 详细数据(批量插入是一个List集合 HashMap)
     */
    private List<SqlData> data;

    /**
     * 具体sql数据
     */
    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SqlData {

        /**
         * 当前的自增id
         */
        private Object pid;

        /**
         * sql语句
         */
        private String sqlString;

        /**
         * 是否来自批量插入
         */
        private boolean batch;

        /**
         * sql的类型
         */
        private SqlCommandType sqlCommandType;

        /**
         * 数据
         */
        private Object data;
    }
}