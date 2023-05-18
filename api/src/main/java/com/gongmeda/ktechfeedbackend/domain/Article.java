package com.gongmeda.ktechfeedbackend.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Article {

    private final Author author;
    private Long id;
    private String title;
    private String summary;
    private String linkUrl;
    private String thumbnailUrl;
    private LocalDateTime timestamp;
    private Set<String> hashtags = new HashSet<>();

    public Article(
        Long id,
        Author author,
        String title,
        String summary,
        String linkUrl,
        String thumbnailUrl,
        LocalDateTime timestamp,
        Collection<String> hashtags
    ) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.summary = summary;
        this.linkUrl = linkUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.timestamp = timestamp;
        this.hashtags.addAll(hashtags);
    }

    public Article(
        Author author,
        String title,
        String summary,
        String linkUrl,
        String thumbnailUrl,
        LocalDateTime timestamp,
        Collection<String> hashtags
    ) {
        this.author = author;
        this.title = title;
        this.summary = summary;
        this.linkUrl = linkUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.timestamp = timestamp;
        this.hashtags.addAll(hashtags);
    }

    public Set<String> getHashtags() {
        return Collections.unmodifiableSet(hashtags);
    }

    public void update(
        String title,
        String summary,
        String linkUrl,
        String thumbnailUrl,
        LocalDateTime timestamp,
        Collection<String> hashtags
    ) {
        this.title = title;
        this.summary = summary;
        this.linkUrl = linkUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.timestamp = timestamp;
        this.hashtags.clear();
        this.hashtags.addAll(hashtags);
    }
}
