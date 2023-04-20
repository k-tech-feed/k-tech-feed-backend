package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.domain.Author;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class AuthorUrlResponse {

    private final Long id;
    private final String blogUrl;
    private final String rssUrl;

    public static AuthorUrlResponse from(Author author) {
        return new AuthorUrlResponse(
            author.getId(),
            author.getBlogUrl(),
            author.getRssUrl()
        );
    }
}
