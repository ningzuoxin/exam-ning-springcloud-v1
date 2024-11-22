package com.ning.interfaces.validation;

import cn.hutool.core.util.StrUtil;
import com.ning.domain.enums.QuestionTypeEnum;
import com.ning.interfaces.validation.constraints.QuestionTypeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 试题类型校验注解校验器
 *
 * @author zuoxin.ning
 * @since 2024-11-22 15:00
 */
public class QuestionTypeValidator implements ConstraintValidator<QuestionTypeConstraint, String> {

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        return StrUtil.isNotBlank(type) && QuestionTypeEnum.validTypeList().contains(type);
    }

}
