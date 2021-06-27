//package com.kapcb.ccc.test;
//
//
//import io.vavr.Function1;
//
//import static io.vavr.API.$;
//import static io.vavr.API.Case;
//import static io.vavr.API.Match;
//
///**
// * <a>Title: MatchTest </a>
// * <a>Author: Kapcb <a>
// * <a>Description:  <a>
// *
// * @author Kapcb
// * @version 1.0.0
// * @date 2021/6/17 22:33
// */
//public class MatchTest {
//
//    public static void main(String[] args) {
//        Integer ccc = matchTest("ccc", 10);
//        System.out.println("ccc = " + ccc);
//    }
//
//    private static Integer matchTest(String v, Integer number) {
//        return Match(v).of(
//                Case($("kapcb"), number + 10),
//                Case($("mike"), number + 20),
//                Case($("ccc"), number + 30),
//                Case($(), 100));
//    }
//
//    private static Function1<String, Integer> matchTest2(Integer number) {
//        return null;
//    }
//
//}
