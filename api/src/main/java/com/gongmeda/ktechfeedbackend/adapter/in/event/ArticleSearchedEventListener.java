package com.gongmeda.ktechfeedbackend.adapter.in.event;

import com.gongmeda.ktechfeedbackend.application.event.ArticleSearchedEvent;
import com.gongmeda.ktechfeedbackend.application.event.ArticleViewedEvent;
import com.gongmeda.ktechfeedbackend.application.port.in.ArticleViewRecordUseCase;
import com.gongmeda.ktechfeedbackend.application.port.in.RelatedUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
class ArticleSearchedEventListener {

    private final RelatedUseCase relatedUseCase;

    @Async
    @EventListener
    public void onArticleViewedEvent(ArticleSearchedEvent event) {
        relatedUseCase.recordSearch(event.keyword());
    }
}
