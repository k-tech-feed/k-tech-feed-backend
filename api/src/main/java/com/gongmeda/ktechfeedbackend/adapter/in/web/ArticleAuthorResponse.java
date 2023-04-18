package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.domain.Author;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ArticleAuthorResponse {

    private final Long id;
    private final String name;
    private final String logoUrl;

    public static ArticleAuthorResponse from(Author author) {
        return new ArticleAuthorResponse(
            author.getId(),
            author.getName(),
            author.getLogoUrl()
        );
    }
}
