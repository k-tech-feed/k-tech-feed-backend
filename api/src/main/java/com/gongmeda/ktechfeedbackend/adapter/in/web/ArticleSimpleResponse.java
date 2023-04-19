package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.domain.Article;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ArticleSimpleResponse {

    private final Long id;
    private final ArticleAuthorResponse author;
    private final String title;
    private final LocalDateTime timestamp;

    public static ArticleSimpleResponse from(Article article) {
        return new ArticleSimpleResponse(
            article.getId(),
            ArticleAuthorResponse.from(article.getAuthor()),
            article.getTitle(),
            article.getTimestamp()
        );
    }
}
