package com.kapcb.ccc.constants;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

/**
 * <a>Title: CoreConstant </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 21:12
 */
public class CoreConstant {

    public enum StringPool {

    }

    public enum IntegerPool {

        SEMICOLON(";", "分号"),
        COLON(":", "冒号"),
        COMMA(",", "逗号"),
        SPACE(" ", "空格"),
        SPACE_COMMA(" ,", "空格逗号"),
        EMPTY_STRING("", "空字符串"),
        SINGLE_QUOTES("'", "单引号"),

        SQL_ORDER("ORDER", "SQL排序"),
        SQL_ACS("ASC", "SQL正序"),
        SQL_DESC("DESC", "SQL倒序"),
        SQL_LIMIT("LIMIT ", "SQL限定数量"),
        ;

        private String value;
        private String description;

        IntegerPool(String value, String description) {
            this.value = value;
            this.description = description;
        }

        @NonNull
        public String value() {
            return this.value;
        }
    }

    public enum LongPool {

    }

    public enum DoublePool {

    }

    public enum BigDecimalPool {

    }
    

    /**
     * 横杠
     */
    public static final String HYPHEN = "-";

    /**
     * 下横杠
     */
    public static final String UNDER_CROSS = "_";

    /**
     * 名称分隔符
     */
    public static final String NAME_SEPARATOR = "#";

    /**
     * @
     */
    public static final String AT = "@";

    /**
     * 冒号
     */
    public static final String COLON = ":";

    /**
     * 核心信息
     */
    public static final String TOKEN = "Token";

    /**
     * 主键字段
     */
    public static final String PK_FIELD = "id";

    /**
     * 主键为空的字符串值
     */
    public static final String PK_NULL_VALUE = "0";

    /**
     * 用户名前缀
     */
    public static final String USERNAME_PREFIX = "accounts_";

    /**
     * 连续登录失败次数
     */
    public static final int CONSECUTIVE_LOGIN_FAILURES = 5;

    /**
     * 斜杠
     */
    public static final String SLASH = "/";

    /**
     * 点
     */
    public static final String DOT = ".";

    /**
     * 在...之间分隔符
     */
    String BETWEEN = " - ";

    /**
     * 通用的page size 最大值
     */
    int MAX_PAGE_SIZE = 100;
}
