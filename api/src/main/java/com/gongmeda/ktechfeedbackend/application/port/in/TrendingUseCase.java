package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.domain.Article;
import java.util.List;

public interface TrendingUseCase {

    List<Article> getTrendingArticles(TrendingType type);
    List<String> getTrendingHashtags(TrendingType type);
}
