package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.domain.Article;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ArticleResponse {

    private final Long id;
    private final AuthorSimpleResponse author;
    private final String title;
    private final String summary;
//    private final String linkUrl;
    private final String thumbnailUrl;
    private final LocalDateTime timestamp;
    private final List<String> hashtags;

    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
            article.getId(),
            AuthorSimpleResponse.from(article.getAuthor()),
            article.getTitle(),
            article.getSummary(),
//            article.getLinkUrl(),
            article.getThumbnailUrl(),
            article.getTimestamp(),
            article.getHashtags().stream().sorted().toList()
        );
    }
}
