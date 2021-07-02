package com.kapcb.ccc.constants.enmus;

/**
 * <a>Title: IntegerPool </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/2 22:34
 */
public enum IntegerPool {

    ZERO(0, "number 0"),
    ONE(1, "number 1"),
    FIVE(5, "number 5"),
    TEN(10, "number 10");

    private Integer value;
    private String description;

    IntegerPool(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer value() {
        return this.value;
    }
}
