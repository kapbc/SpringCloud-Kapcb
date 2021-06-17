package com.kapcb.ccc.test;

import io.vavr.collection.List;

/**
 * <a>Title: ListTest </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/17 22:47
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> of = List.of("mike", "kapcb", "ccc");
        System.out.println("of = " + of);
        List<String> append = of.tail().append("nb!");
        System.out.println("append = " + append);
        List<String> wocao = append.tail().prepend("wocao");
        System.out.println("wocao = " + wocao);


        String ccc = List.of("mike", "kapcb", "ccc")
                .intersperse(",")
                .foldLeft(new StringBuilder(), StringBuilder::append)
                .toString();
        System.out.println("ccc = " + ccc);

        String k = List.of("1", "2", "3")
//                .intersperse(",")
                .foldRight(new StringBuilder("k"), StringBuilder::append).toString();
    }
}
