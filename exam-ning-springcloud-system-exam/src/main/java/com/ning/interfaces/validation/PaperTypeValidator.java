package com.ning.interfaces.validation;

import com.ning.domain.enums.PaperTypeEnum;
import com.ning.interfaces.validation.constraints.PaperTypeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 试卷类型校验注解校验器
 *
 * @author zuoxin.ning
 * @since 2024-11-27 11:30
 */
public class PaperTypeValidator implements ConstraintValidator<PaperTypeConstraint, Integer> {

    @Override
    public boolean isValid(Integer type, ConstraintValidatorContext context) {
        return Objects.nonNull(type) && PaperTypeEnum.validTypeList().contains(type);
    }

}
