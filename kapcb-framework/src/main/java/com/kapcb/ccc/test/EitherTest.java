//package com.kapcb.ccc.test;
//
//import io.vavr.control.Either;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
///**
// * <a>Title: EitherTest </a>
// * <a>Author: Kapcb <a>
// * <a>Description: EitherTest <a>
// *
// * @author Kapcb
// * @version 1.0.0
// * @date 2021/6/17 22:10
// */
//public class EitherTest {
//
//    public static void main(String[] args) {
//        Either<String, Integer> divideNumber = divideNumber(1, 0);
//        boolean right = divideNumber.isRight();
//        System.out.println("right = " + right);
//        boolean left = divideNumber.isLeft();
//        System.out.println("left = " + left);
//        String left1 = divideNumber.getLeft();
//        System.out.println("left1 = " + left1);
//        Integer orElseGet = divideNumber.getOrElse(10);
//        System.out.println("orElseGet = " + orElseGet);
//
//        Either<String, Integer> divideNumber1 = divideNumber(10, 2);
//        boolean right1 = divideNumber1.isRight();
//        System.out.println("right1 = " + right1);
//        boolean left2 = divideNumber1.isLeft();
//        System.out.println("left2 = " + left2);
//
//        List<Integer> collect = divideNumber1.map(s -> s).collect(Collectors.toList());
//        System.out.println("collect = " + collect);
//    }
//
//    private static Either<String, Integer> divideNumber(Integer a, Integer b) {
//        try {
//            return Either.right(a / b);
//        } catch (ArithmeticException e) {
//            return Either.left("Can't divide a number per zero, error message is : " + e.getMessage());
//        } catch (Exception e) {
//            return Either.left("try to divide the numbers error, error message is : " + e.getMessage());
//        }
//    }
//}
