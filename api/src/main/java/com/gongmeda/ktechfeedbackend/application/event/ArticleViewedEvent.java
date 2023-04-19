package com.gongmeda.ktechfeedbackend.application.event;

public record ArticleViewedEvent(long articleId, String ipAddress) {

}
