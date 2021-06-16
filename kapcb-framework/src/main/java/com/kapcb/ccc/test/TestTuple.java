package com.kapcb.ccc.test;

import io.vavr.Tuple;
import io.vavr.Tuple2;

/**
 * <a>Title: TestTuple </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/16 21:37
 */
public class TestTuple {

    public static void main(String[] args) {
        Tuple2<String, Integer> testTuple2 = testTuple2();
        String java = testTuple2._1;
        Integer level = testTuple2._2;
        System.out.println("kapcb is : " + java + " age is : " + level);

        Tuple2<String, Integer> testTupleMap = testTupleMap(testTuple2);
        String vavr = testTupleMap._1();
        Integer version = testTupleMap._2();
        System.out.println("vavr is : " + vavr + " version is : " + version);

        Tuple2<String, Integer> testTupleMapping = testTupleMapping(testTuple2);
        String vavrMapping = testTupleMapping._1;
        Integer versionMapping = testTupleMapping._2;
        System.out.println("vavrMapping is : " + vavrMapping + " versionMapping is : " + versionMapping);

        String transformTuple = transformTuple(testTuple2);
        System.out.println("transformTuple = " + transformTuple);
    }

    /**
     * 元组是通过静态工厂方法创建的 Tuple.of()
     * 通过 ._1获取指定的元素, ._1 和 .1_() API一样
     *
     * @return Tuple2<String, Integer>
     */
    private static Tuple2<String, Integer> testTuple2() {
        return Tuple.of("java", 8);
    }

    /**
     * 以组件方式映射元组
     * 组件映射计算元组中每个元素的函数，返回另一个元组。
     *
     * @param tuple2 Tuple2<String, Integer>
     * @return Tuple2<String, Integer>
     */
    private static Tuple2<String, Integer> testTupleMap(Tuple2<String, Integer> tuple2) {
        Tuple2<String, Integer> mapTuple = tuple2.map(
                s -> s.substring(2) + "vr",
                i -> i / 8);
        return mapTuple;
    }

    /**
     * 使用一个映射器映射一个元组
     * 使用一个映射函数来映射元组
     *
     * @param tuple2 Tuple2<String, Integer>
     * @return Tuple2<String, Integer>
     */
    private static Tuple2<String, Integer> testTupleMapping(Tuple2<String, Integer> tuple2) {
        Tuple2<String, Integer> mapTuple = tuple2.map((s, i) -> Tuple.of(s.substring(2) + "vr", i / 8));
        return mapTuple;
    }

    /**
     * 转换元组
     * Transform 根据元组的内容创建一个新类型
     *
     * @param tuple2 Tuple2<String, Integer> tuple2
     * @return String
     */
    public static String transformTuple(Tuple2<String, Integer> tuple2) {
        String transform = tuple2.apply((s, i) -> s.substring(2) + "vr" + i);
        return transform;
    }
}
