package com.gongmeda.ktechfeedbackend.application.service;

import com.gongmeda.ktechfeedbackend.application.port.in.TrendingType;
import com.gongmeda.ktechfeedbackend.application.port.in.TrendingUseCase;
import com.gongmeda.ktechfeedbackend.application.port.out.ArticlePersistencePort;
import com.gongmeda.ktechfeedbackend.application.port.out.TrendingPersistencePort;
import com.gongmeda.ktechfeedbackend.domain.Article;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class TrendingService implements TrendingUseCase {

    private final TrendingPersistencePort trendingPersistencePort;
    private final ArticlePersistencePort articlePersistencePort;

    @Override
    public List<Article> getTrendingArticles(TrendingType type) {
        List<Long> trendingArticleIds;
        if (type == TrendingType.WEEKLY) {
            trendingArticleIds = trendingPersistencePort.getArticleIdsByViewCountSinceLastWeek(5);
        } else if (type == TrendingType.MONTHLY) {
            trendingArticleIds = trendingPersistencePort.getArticleIdsByViewCountSinceLastMonth(5);
        } else {
            throw new IllegalArgumentException("Invalid TrendingType: " + type);
        }

        return articlePersistencePort.getAllByIds(trendingArticleIds);
    }

    @Override
    public List<String> getTrendingHashtags(TrendingType type) {
        if (type == TrendingType.WEEKLY) {
            return trendingPersistencePort.getHashtagsByViewCountSinceLastWeek(10);
        } else if (type == TrendingType.MONTHLY) {
            return trendingPersistencePort.getHashtagsByViewCountSinceLastMonth(10);
        }
        throw new IllegalArgumentException("Invalid TrendingType: " + type);
    }
}
