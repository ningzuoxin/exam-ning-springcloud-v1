package com.ning.interfaces.validation.constraints;

import com.ning.interfaces.validation.PaperTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 试卷类型校验注解
 *
 * @author zuoxin.ning
 * @since 2024-11-27 11:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = PaperTypeValidator.class)
public @interface PaperTypeConstraint {

    String message() default "试卷类型错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
