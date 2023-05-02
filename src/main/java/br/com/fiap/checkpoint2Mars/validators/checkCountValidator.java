package br.com.fiap.checkpoint2Mars.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class checkCountValidator implements ConstraintValidator<checkCount, Integer> {
    public void initialize(checkCount constraint) {
    }

    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value % 3 == 0 && value > 0;
    }
}