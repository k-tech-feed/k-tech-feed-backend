package com.gongmeda.ktechfeedbackend.domain;

import java.util.Collections;
import java.util.Map;
import lombok.Getter;

@Getter
public class Author {

    private Long id;
    private String name;
    private String logoUrl;
    private String blogUrl;
    private String rssUrl;
    private String description;
    private Map<String, String> links;

    public Map<String, String> getLinks() {
        return Collections.unmodifiableMap(links);
    }

    public Author(
        Long id,
        String name,
        String logoUrl,
        String blogUrl,
        String rssUrl,
        String description,
        Map<String, String> links
    ) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.blogUrl = blogUrl;
        this.rssUrl = rssUrl;
        this.description = description;
        this.links = links;
    }

    public Author(
        String name,
        String logoUrl,
        String blogUrl,
        String rssUrl,
        String description,
        Map<String, String> links
    ) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.blogUrl = blogUrl;
        this.rssUrl = rssUrl;
        this.description = description;
        this.links = links;
    }

    public void update(
        String name,
        String logoUrl,
        String blogUrl,
        String RSSUrl,
        String description,
        Map<String, String> links
    ) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.blogUrl = blogUrl;
        this.rssUrl = RSSUrl;
        this.description = description;
        this.links = links;
    }
}
