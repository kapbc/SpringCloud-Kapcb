package com.kapcb.ccc.constants.enmus;

/**
 * <a>Title: LongPool </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/7/2 22:38
 */
public enum LongPool {

    ZERO(0L, "number 0"),
    ONE(1L, "number 1"),
    FIVE(5L, "number 5"),
    TEN(10L, "number 10");

    private Long value;
    private String description;

    LongPool(Long value, String description) {
        this.value = value;
        this.description = description;
    }

    public Long value() {
        return this.value;
    }
}
