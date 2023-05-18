package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class PagingQuery extends SelfValidating<PagingQuery> {

    @PositiveOrZero
    Long afterId;

    @Positive
    @NotNull
    Integer size;

    public PagingQuery(@Nullable Long afterId, Integer size) {
        this.afterId = afterId == null ? 0 : afterId;
        this.size = size;
        this.validateSelf();
    }
}
