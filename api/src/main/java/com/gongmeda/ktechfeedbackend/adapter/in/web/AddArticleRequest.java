package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.application.port.in.AddArticleCommand;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class AddArticleRequest {

    private long authorId;
    private String title;
    private String summary;
    private String linkUrl;
    private String thumbnailUrl;
    private LocalDateTime timestamp;
    private List<String> hashtags;

    public AddArticleCommand toCommand() {
        return new AddArticleCommand(
            authorId,
            title,
            summary,
            linkUrl,
            thumbnailUrl,
            timestamp,
            hashtags
        );
    }
}
