package com.kapcb.ccc.validator;

import com.kapcb.ccc.annotation.validation.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <a>Title: PaginationValidator </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/14 15:32
 */
@Slf4j
@Component
public class PaginationValidator implements ConstraintValidator<Pagination,Integer> {

    
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return false;
    }
}
