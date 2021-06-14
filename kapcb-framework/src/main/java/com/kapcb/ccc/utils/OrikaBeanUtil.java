package com.kapcb.ccc.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;


/**
 * <a>Title: OrikaBeanUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Orika Bean Util <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/14 13:33
 */
@Slf4j
public class OrikaBeanUtil {

    private static final MapperFacade MAPPER_FACADE;

    static {
        // 默认字段工厂
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().useAutoMapping(true).mapNulls(true).build();
        // 默认字段实例
        MAPPER_FACADE = mapperFactory.getMapperFacade();
    }

    /**
     * 映射实体
     *
     * @param data    数据
     * @param toClass 需要映射的类对象
     * @param <R>     映射类型
     * @param <T>     数据
     * @return R
     */
    @SneakyThrows(Exception.class)
    public static <R, T> R map(T data, Class<R> toClass) {
        return MAPPER_FACADE.map(data, toClass);
    }

//    public static <S, T> S map(T data, Class<S> toClass, Map<String, String> configMap) {
//    }

    @SneakyThrows(Exception.class)
    public static <R, T> List<R> mapList(@NonNull Collection<T> data, Class<R> toClass) {
        return MAPPER_FACADE.mapAsList(data, toClass);
    }
}
