package com.kapcb.ccc.configuration;

import com.alibaba.fastjson.JSON;
import com.kapcb.ccc.model.vo.MybatisPlusSqlDataVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * <a>Title: MybatisPlusSqlInjector </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Mybatis Plus Sql Injector <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 22:42
 */
@Slf4j
//@Component
@Intercepts(value = {
        @Signature(method = "batch", type = StatementHandler.class, args = {Statement.class}),
        @Signature(method = "update", type = StatementHandler.class, args = {Statement.class}),
        @Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class}),
        @Signature(method = "commit", type = Executor.class, args = {boolean.class})
})
public class MybatisPlusSqlInjector implements Interceptor {

    private static final String TYPE_BATCH = "batch";
    private static final String TYPE_COMMIT = "commit";

    /**
     * 存放当前线程sql数据
     */
    private ThreadLocal<MybatisPlusSqlDataVO> threadLocal = new ThreadLocal<>();

    @Value(value = "${spring.application.name:unknown}")
    private String applicationName;

    /**
     * mybatis 调用流程 executor.update -> statementHandler.update -> statementHandler.commit 都发生在同一个线程
     *
     * @param invocation Invocation
     * @return Object
     * @throws Throwable Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        Object target = invocation.getTarget();

        if (target instanceof StatementHandler) {
            // 是否为批量插入
            boolean batchInsert = TYPE_BATCH.equals(invocation.getMethod().getName());

            return updateWithStatementHandler((StatementHandler) target, (Statement) args[0], batchInsert);
        } else if (target instanceof Executor) {
            // 用户提交了事务之后触发, sql数据分发
            if (TYPE_COMMIT.equals(invocation.getMethod().getName())) {
                invocation.proceed();
                MybatisPlusSqlDataVO mybatisPlusSqlDataVO = threadLocal.get();
                threadLocal.remove();
                // 分发数据
                dispatcherSqlData(mybatisPlusSqlDataVO);
                return null;
            }

            // 获取变更数据
            return updateWithExecutor((Executor) target, (MappedStatement) args[0], args[1]);
        } else {
            log.info("------------unknown mybatis object : " + invocation.getTarget().getClass().getName());
            return invocation.proceed();
        }
    }

    private static Object updateWithExecutor(Executor executor, MappedStatement mappedStatement, Object params) throws SQLException {
        return executor.update(mappedStatement, params);
    }

    private static Object updateWithStatementHandler(StatementHandler statementHandler, Statement statement, boolean batchInsert) {
        return null;
    }

    private static void dispatcherSqlData(MybatisPlusSqlDataVO mybatisPlusSqlDataVO) {
        log.info("------------MyBatis Plus Begin To Dispatcher SQL Message------------\n sql data : {}", JSON.toJSONString(mybatisPlusSqlDataVO));
    }
}
