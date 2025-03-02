package com.eventos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalTimeFormatValidator implements ConstraintValidator<ValidLocalTimeFormat, LocalTime> {

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Permite valores nulos, ajustar según necesidades
        }
        try {
            DateTimeFormatter.ofPattern("HH:mm").format(value);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}