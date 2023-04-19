package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class UpdateArticleCommand extends SelfValidating<AddArticleCommand> {

    private final String title;
    private final String summary;
    private final String linkUrl;
    private final String thumbnailUrl;
    private final LocalDateTime timestamp;
    private final Collection<String> hashtags;

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
    }
}
