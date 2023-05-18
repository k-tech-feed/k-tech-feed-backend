package com.gongmeda.ktechfeedbackend.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Author {

    private final Map<String, String> links = new HashMap<>();
    private Long id;
    private String name;
    private String logoUrl;
    private String blogUrl;
    private String rssUrl;
    private String description;

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
        this.links.putAll(links);
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
        this.links.putAll(links);
    }

    public Map<String, String> getLinks() {
        return Collections.unmodifiableMap(links);
    }

    public void update(
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
        this.links.clear();
        this.links.putAll(links);
    }
}
