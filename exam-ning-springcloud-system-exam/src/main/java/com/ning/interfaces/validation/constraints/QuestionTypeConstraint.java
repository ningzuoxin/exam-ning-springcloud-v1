package com.ning.interfaces.validation.constraints;

import com.ning.interfaces.validation.QuestionTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 试题类型校验注解
 *
 * @author zuoxin.ning
 * @since 2024-11-22 15:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = QuestionTypeValidator.class)
public @interface QuestionTypeConstraint {

    String message() default "试题类型错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
