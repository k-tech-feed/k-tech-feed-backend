package com.gongmeda.ktechfeedbackend.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthorTest {

    @Test
    void linksPassedToConstructorCopied() {
        Map<String, String> links = new HashMap<>() {{
            put("key", "value");
        }};
        Author author1 = new Author(
            1L,
            "name",
            "https://example.com/logo.png",
            "https://example.com",
            "https://example.com/rss",
            "description",
            links
        );
        Author newAuthor = new Author(
            "name",
            "https://example.com/logo.png",
            "https://example.com",
            "https://example.com/rss",
            "description",
            links
        );

        links.put("key2", "value2");

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(author1.getLinks()).size().isEqualTo(1);
            softAssertions.assertThat(newAuthor.getLinks()).size().isEqualTo(1);
        });
    }

    @Test
    void getLinksReturnUnmodifiableMap() {
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

        assertThatThrownBy(() -> author.getLinks().put("key2", "value2"))
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

        author.update(
            "name2",
            "https://example.com/logo2.png",
            "https://example.com/blog2",
            "https://example.com/rss2",
            "description2",
            new HashMap<>() {{
                put("key2", "value2");
            }}
        );

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(author.getId()).isEqualTo(1L);
            softAssertions.assertThat(author.getName()).isEqualTo("name2");
            softAssertions.assertThat(author.getLogoUrl()).isEqualTo("https://example.com/logo2.png");
            softAssertions.assertThat(author.getBlogUrl()).isEqualTo("https://example.com/blog2");
            softAssertions.assertThat(author.getRssUrl()).isEqualTo("https://example.com/rss2");
            softAssertions.assertThat(author.getDescription()).isEqualTo("description2");
            softAssertions.assertThat(author.getLinks()).size().isEqualTo(1);
            softAssertions.assertThat(author.getLinks().get("key2")).isEqualTo("value2");
        });
    }
}