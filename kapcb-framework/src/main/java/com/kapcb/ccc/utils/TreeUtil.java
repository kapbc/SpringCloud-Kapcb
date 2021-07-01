package com.kapcb.ccc.utils;

import com.kapcb.ccc.model.TestMenu;
import com.kapcb.ccc.model.base.BaseTree;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <a>Title: TreeUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/1 22:32
 */
@Slf4j
public class TreeUtil<T> {

    private TreeUtil() {
    }

    public static <T extends BaseTree<T>> List<T> handlerTortoise(List<T> originalList) {
    }

    public static void main(String[] args) {
        TestMenu testMenu = new TestMenu();
        List<TestMenu> children = testMenu.getChildren();
    }
}
