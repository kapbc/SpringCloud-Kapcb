package com.kapcb.ccc.annotation.validation;

import com.kapcb.ccc.validator.PaginationValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a>Title: Pagination </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Pagination <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/14 15:32
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PaginationValidator.class})
public @interface Pagination {
}
