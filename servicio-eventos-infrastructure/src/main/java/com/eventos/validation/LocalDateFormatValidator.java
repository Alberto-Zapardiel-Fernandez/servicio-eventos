package com.eventos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateFormatValidator implements ConstraintValidator<ValidLocalDateFormat, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Permite valores nulos, ajusta según tus necesidades
        }
        try {
            DateTimeFormatter.ISO_LOCAL_DATE.format(value);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}