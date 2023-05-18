package com.gongmeda.ktechfeedbackend.application.port.in;

import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AddArticleCommandTest {

    @Test
    void validateAllHashtags() {
        List<String> hashtags = List.of("a", "b", "", " ", "e", "f");

        assertThatThrownBy(() -> new AddArticleCommand(
            1,
            "title",
            "summary",
            "https://example.com",
            "https://example.com/thumbnail.jpg",
            LocalDateTime.now(),
            hashtags
        )).isInstanceOf(ConstraintViolationException.class)
            .is(new Condition<>(e -> {
                ConstraintViolationException exception = (ConstraintViolationException) e;
                return exception.getConstraintViolations().size() == 2;
            }, "2 violations"));
    }
}