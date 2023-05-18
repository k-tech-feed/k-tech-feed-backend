package com.gongmeda.ktechfeedbackend.application.event;

import com.gongmeda.ktechfeedbackend.common.IpAddress;
import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ArticleViewedEvent extends SelfValidating<ArticleViewedEvent> {

    @Positive
    long articleId;

    @IpAddress
    String ipAddress;

    public ArticleViewedEvent(long articleId, String ipAddress) {
        this.articleId = articleId;
        this.ipAddress = ipAddress;
        this.validateSelf();
    }
}
