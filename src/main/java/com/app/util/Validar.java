package com.app.util;

import com.app.exception.DadosInvalidosException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import java.util.Set;

public class Validar {

    private static final jakarta.validation.Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validar(T objeto) {
        Set<ConstraintViolation<T>> erros = VALIDATOR.validate(objeto);

        if (!erros.isEmpty()) {
            throw new DadosInvalidosException("Há campos inválidos!");
        }
    }
}
