package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import com.gongmeda.ktechfeedbackend.application.port.out.ArticleViewRecordPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
class ArticleViewRecordPersistenceAdapter implements ArticleViewRecordPersistencePort {

    private final ArticleViewRecordRepository articleViewRecordRepository;

    @Override
    public boolean isViewedToday(long articleId, String ipAddress) {
        return articleViewRecordRepository.existsByArticleIdAndIpAddressAndCreatedAtGreaterThanEqual(
            articleId,
            ipAddress,
            LocalDate.now().atStartOfDay()
        );
    }

    @Override
    public void recordView(long articleId, String ipAddress) {
        articleViewRecordRepository.save(
            new ArticleViewRecordJpaEntity(
                articleId,
                ipAddress,
                LocalDateTime.now()
            )
        );
    }
}
