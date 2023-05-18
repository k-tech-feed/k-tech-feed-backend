package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.application.port.in.UpdateArticleCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UpdateArticleRequest {

    private String title;
    private String summary;
    private String linkUrl;
    private String thumbnailUrl;
    private LocalDateTime timestamp;
    private Collection<String> hashtags;

    public UpdateArticleCommand toCommand() {
        return new UpdateArticleCommand(
            title,
            summary,
            linkUrl,
            thumbnailUrl,
            timestamp,
            hashtags
        );
    }
}
