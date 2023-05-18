package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Value
public class AddArticleCommand extends SelfValidating<AddArticleCommand> {

    @Positive
    long authorId;

    @NotBlank
    String title;

    String summary;

    @NotNull
    @URL
    String linkUrl;

    @URL
    String thumbnailUrl;

    LocalDateTime timestamp;
    
    Collection<@NotBlank String> hashtags;

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
