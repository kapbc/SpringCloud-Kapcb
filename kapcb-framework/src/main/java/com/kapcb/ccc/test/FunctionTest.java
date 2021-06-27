//package com.kapcb.ccc.test;
//
//import io.vavr.Function2;
//import io.vavr.Function3;
//
//import java.util.function.Function;
//
///**
// * <a>Title: FunctionTest </a>
// * <a>Author: Kapcb <a>
// * <a>Description:  <a>
// *
// * @author Kapcb
// * @version 1.0.0
// * @date 2021/6/16 22:08
// */
//public class FunctionTest {
//
//    public static void main(String[] args) {
//
//        Function2<Integer, Integer, Integer> function2 = sumFunction();
//        Integer result = function2.apply(3, 4);
//        System.out.println("result = " + result);
//
//        FunctionTest functionTest = new FunctionTest();
//        Function3<String, String, String, String> function = functionTest.createFunction();
//        String appendResult = function.apply("kapcb ", "nb", "zui ");
//        System.out.println("appendResult = " + appendResult);
//
//        Function2<Integer, Integer, Integer> function21 = compositeFunction();
//        Integer apply = function21.apply(3, 4);
//        System.out.println("apply = " + apply);
//    }
//
//    /**
//     * 两个入参，一个返回值的函数式接口
//     *
//     * @return Function2<Integer, Integer, Integer>
//     */
//    private static Function2<Integer, Integer, Integer> sumFunction() {
//        Function2<Integer, Integer, Integer> function2 = new Function2<Integer, Integer, Integer>() {
//            @Override
//            public Integer apply(Integer a, Integer b) {
//                return a + b;
//            }
//        };
//        return function2;
//    }
//
//    /**
//     * 可以使用静态工厂方法Function3.of()从任何方法引用创建函数。
//     *
//     * @return Function3<String, String, String, String>
//     */
//    private Function3<String, String, String, String> createFunction() {
//        Function3<String, String, String, String> threeParamFunction = Function3.of(this::appendCharacter);
//        return threeParamFunction;
//    }
//
//    private String appendCharacter(String a, String b, String c) {
//        return a + c + b;
//    }
//
//    /**
//     * 组合函数
//     */
//    private static Function2<Integer, Integer, Integer> compositeFunction() {
//        Function2<Integer, Integer, Integer> plusSum = Integer::sum;
//        Function<Integer, Integer> multiplyByTwo = c -> c * 2;
//        Function2<Integer, Integer, Integer> addSumAndMultiplyBy2 = plusSum.andThen(multiplyByTwo);
//        return addSumAndMultiplyBy2;
//    }
//}
