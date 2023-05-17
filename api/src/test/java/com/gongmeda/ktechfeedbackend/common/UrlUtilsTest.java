package com.gongmeda.ktechfeedbackend.common;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class UrlUtilsTest {

    @Test
    void encodeOnlyKorean() {
        String urlWithKoreanQueryString = "https://k-tech-feed.site/search?q=한글";
        String urlWithKoreanEndPath = "https://k-tech-feed.site/한글";
        String urlWithKoreanMiddlePath = "https://k-tech-feed.site/한글/english";

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(UrlUtils.encodeKorean(urlWithKoreanQueryString)).isEqualTo("https://k-tech-feed.site/search?q=%ED%95%9C%EA%B8%80");
            softAssertions.assertThat(UrlUtils.encodeKorean(urlWithKoreanEndPath)).isEqualTo("https://k-tech-feed.site/%ED%95%9C%EA%B8%80");
            softAssertions.assertThat(UrlUtils.encodeKorean(urlWithKoreanMiddlePath)).isEqualTo("https://k-tech-feed.site/%ED%95%9C%EA%B8%80/english");
        });
    }

    @Test
    void encodeWithTrailingSlashKept() {
        String urlWithTrailingSlash = "https://k-tech-feed.site/search/?q=한글/";
        String urlWithoutTrailingSlash = "https://k-tech-feed.site/search/?q=한글";

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(UrlUtils.encodeKorean(urlWithTrailingSlash)).isEqualTo("https://k-tech-feed.site/search/?q=%ED%95%9C%EA%B8%80/");
            softAssertions.assertThat(UrlUtils.encodeKorean(urlWithoutTrailingSlash)).isEqualTo("https://k-tech-feed.site/search/?q=%ED%95%9C%EA%B8%80");
        });
    }
}