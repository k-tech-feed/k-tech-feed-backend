package com.gongmeda.ktechfeedbackend.adapter.in.event;

import com.gongmeda.ktechfeedbackend.application.event.ArticleViewedEvent;
import com.gongmeda.ktechfeedbackend.application.port.in.ArticleViewRecordUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
class ArticleViewedEventListener {

    private final ArticleViewRecordUseCase articleViewRecordUseCase;

    @Async
    @EventListener
    public void onArticleViewedEvent(ArticleViewedEvent event) {
        log.debug("article view event received: {}", event);
        articleViewRecordUseCase.recordView(event.articleId(), event.ipAddress());
    }
}
