package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.domain.Author;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class AuthorResponse {

    private Long id;
    private String name;
    private String logoUrl;
    private String blogUrl;
    private String description;
    private Map<String, String> links;

    public static AuthorResponse from(Author author) {
        return new AuthorResponse(
            author.getId(),
            author.getName(),
            author.getLogoUrl(),
            author.getBlogUrl(),
            author.getDescription(),
            author.getLinks()
        );
    }
}
