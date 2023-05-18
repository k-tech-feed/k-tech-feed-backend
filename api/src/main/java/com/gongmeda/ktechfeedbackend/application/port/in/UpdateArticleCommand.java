package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Value
public class UpdateArticleCommand extends SelfValidating<UpdateArticleCommand> {

    String title;
    String summary;
    String linkUrl;
    String thumbnailUrl;
    LocalDateTime timestamp;
    Collection<String> hashtags;

    public UpdateArticleCommand(
        String title,
        String summary,
        String linkUrl,
        String thumbnailUrl,
        LocalDateTime timestamp,
        Collection<String> hashtags
    ) {
        this.title = title;
        this.summary = summary;
        this.linkUrl = linkUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.timestamp = timestamp;
        this.hashtags = hashtags;
        this.validateSelf();
    }
}
