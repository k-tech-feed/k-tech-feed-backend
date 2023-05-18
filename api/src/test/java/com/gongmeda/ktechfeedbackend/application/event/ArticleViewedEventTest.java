package com.gongmeda.ktechfeedbackend.application.event;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleViewedEventTest {

    @Test
    void WrongIpAddressThrowsValidationException() {
        assertThatThrownBy(() -> new ArticleViewedEvent(1, "wrong ip"))
            .isInstanceOf(ConstraintViolationException.class);
    }
}