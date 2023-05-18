package com.gongmeda.ktechfeedbackend.common;

import jakarta.validation.*;

import java.util.Set;

public abstract class SelfValidating<T> {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator;

    protected SelfValidating() {
        validator = factory.getValidator();
    }

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
