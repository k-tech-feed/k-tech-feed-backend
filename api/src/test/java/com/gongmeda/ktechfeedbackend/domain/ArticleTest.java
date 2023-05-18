package com.gongmeda.ktechfeedbackend.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTest {

    @Test
    void hashtagsPassedToConstructorCopied() {
        Set<String> hashtags = new HashSet<>() {{
            add("hashtag");
        }};
        Author author = new Author(
            1L,
            "name",
            "https://example.com/logo.png",
            "https://example.com",
            "https://example.com/rss",
            "description",
            new HashMap<>() {{
                put("key", "value");
            }}
        );
        Article article1 = new Article(
            1L,
            author,
            "title",
            "summary",
            "https://example.com/logo.png",
            "https://example.com/thumbnail.png",
            LocalDateTime.now(),
            hashtags
        );
        Article newArticle = new Article(
            author,
            "title",
            "summary",
            "https://example.com/logo.png",
            "https://example.com/thumbnail.png",
            LocalDateTime.now(),
            new HashSet<>() {{
                add("hashtag");
            }}
        );

        hashtags.add("hashtag2");

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(article1.getHashtags()).size().isEqualTo(1);
            softAssertions.assertThat(newArticle.getHashtags()).size().isEqualTo(1);
        });
    }

    @Test
    void getHashtagsReturnUnmodifiableSet() {
        Author author = new Author(
            1L,
            "name",
            "https://example.com/logo.png",
            "https://example.com",
            "https://example.com/rss",
            "description",
            new HashMap<>() {{
                put("key", "value");
            }}
        );
        Article article = new Article(
            1L,
            author,
            "title",
            "summary",
            "https://example.com/logo.png",
            "https://example.com/thumbnail.png",
            LocalDateTime.now(),
            new HashSet<>() {{
                add("hashtag");
            }}
        );

        assertThatThrownBy(() -> article.getHashtags().add("hashtag2"))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void update() {
        Author author = new Author(
            1L,
            "name",
            "https://example.com/logo.png",
            "https://example.com",
            "https://example.com/rss",
            "description",
            new HashMap<>() {{
                put("key", "value");
            }}
        );
        LocalDateTime now = LocalDateTime.now();
        Article article = new Article(
            1L,
            author,
            "title",
            "summary",
            "https://example.com/logo.png",
            "https://example.com/thumbnail.png",
            now,
            new HashSet<>() {{
                add("hashtag");
            }}
        );
        LocalDateTime newTimestamp = now.minusDays(1);

        article.update(
            "title2",
            "summary2",
            "https://example.com/logo2.png",
            "https://example.com/thumbnail2.png",
            newTimestamp,
            new HashSet<>() {{
                add("hashtag2");
            }}
        );

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(article.getAuthor()).isEqualTo(author);
            softAssertions.assertThat(article.getId()).isEqualTo(1L);
            softAssertions.assertThat(article.getTitle()).isEqualTo("title2");
            softAssertions.assertThat(article.getSummary()).isEqualTo("summary2");
            softAssertions.assertThat(article.getLinkUrl()).isEqualTo("https://example.com/logo2.png");
            softAssertions.assertThat(article.getThumbnailUrl()).isEqualTo("https://example.com/thumbnail2.png");
            softAssertions.assertThat(article.getTimestamp()).isEqualTo(newTimestamp);
            softAssertions.assertThat(article.getHashtags()).size().isEqualTo(1);
            softAssertions.assertThat(article.getHashtags()).contains("hashtag2");
        });
    }
}