package com.kapcb.ccc.annotation.validation;

import com.kapcb.ccc.validator.ListSizeValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a>Title: ListSize </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ListSize <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/14 15:28
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ListSizeValidator.class})
public @interface ListSize {
}
