package com.gongmeda.ktechfeedbackend.application.port.out;

import java.util.List;

public interface TrendingPersistencePort {

    List<Long> getArticleIdsByViewCountSinceLastWeek(int size);
    List<Long> getArticleIdsByViewCountSinceLastMonth(int size);
    List<String> getHashtagsByViewCountSinceLastWeek(int size);
    List<String> getHashtagsByViewCountSinceLastMonth(int size);
}
