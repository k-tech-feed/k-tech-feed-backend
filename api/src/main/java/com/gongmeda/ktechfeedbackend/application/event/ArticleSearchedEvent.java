package com.gongmeda.ktechfeedbackend.application.event;

import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ArticleSearchedEvent extends SelfValidating<ArticleSearchedEvent> {

    @NotEmpty
    String keyword;

    public ArticleSearchedEvent(String keyword) {
        this.keyword = keyword;
        this.validateSelf();
    }
}
