package com.gongmeda.ktechfeedbackend.application.port.out;

public interface ArticleViewRecordPersistencePort {

    boolean isViewedToday(long articleId, String ipAddress);

    void recordView(long articleId, String ipAddress);
}
