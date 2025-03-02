package com.eventos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = LocalDateFormatValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocalDateFormat {

    String message() default "Formato de fecha inválido (YYYY-MM-DD)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}