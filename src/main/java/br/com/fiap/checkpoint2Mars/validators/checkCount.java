package br.com.fiap.checkpoint2Mars.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = checkCountValidator.class)
@Documented
public @interface checkCount {
    String message() default "Quantidade inválida, deve ser um múltiplo de 3!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}