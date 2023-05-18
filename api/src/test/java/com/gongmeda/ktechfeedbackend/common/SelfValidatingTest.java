package com.gongmeda.ktechfeedbackend.common;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.assertj.core.api.Condition;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class SelfValidatingTest {

    @Test
    void validateSelf() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThatThrownBy(() -> new NeedsValidation(null, " ", 0))
                .isInstanceOf(ConstraintViolationException.class)
                .is(new Condition<>(e -> {
                    ConstraintViolationException exception = (ConstraintViolationException) e;
                    return exception.getConstraintViolations().size() == 3;
                }, "3 violations"));
        });
    }

    class NeedsValidation extends SelfValidating<NeedsValidation> {

        @NotNull
        private final String notNullValue;

        @NotBlank
        private final String notBlankValue;

        @Positive
        private final long positveValue;

        public NeedsValidation(String notNullValue, String notBlankValue, long positiveValue) {
            this.notNullValue = notNullValue;
            this.notBlankValue = notBlankValue;
            this.positveValue = positiveValue;
            validateSelf();
        }
    }
}