package com.gongmeda.ktechfeedbackend.application.service;

import com.gongmeda.ktechfeedbackend.application.port.in.ArticleViewRecordUseCase;
import com.gongmeda.ktechfeedbackend.application.port.out.ArticleViewRecordPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ArticleViewRecordService implements ArticleViewRecordUseCase {

    private final ArticleViewRecordPersistencePort articleViewRecordPersistencePort;

    @Override
    public void recordView(long articleId, String ipAddress) {
        if (!articleViewRecordPersistencePort.isViewedToday(articleId, ipAddress)) {
            articleViewRecordPersistencePort.recordView(articleId, ipAddress);
        }
    }
}
