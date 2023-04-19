package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class AddArticleCommand extends SelfValidating<AddArticleCommand> {

    @Positive
    @NotNull
    private final long authorId;

    @NotNull
    private final String title;
    private final String summary;

    @NotNull
    private final String linkUrl;
    private final String thumbnailUrl;
    private final LocalDateTime timestamp;
    private final Collection<String> hashtags;

    public AddArticleCommand(
        long authorId,
        String title,
        String summary,
        String linkUrl,
        String thumbnailUrl,
        LocalDateTime timestamp,
        Collection<String> hashtags
    ) {
        this.authorId = authorId;
        this.title = title;
        this.summary = summary;
        this.linkUrl = linkUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.timestamp = timestamp;
        this.hashtags = hashtags;
        this.validateSelf();
    }
}
