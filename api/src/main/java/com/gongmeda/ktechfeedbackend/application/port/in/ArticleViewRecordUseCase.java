package com.gongmeda.ktechfeedbackend.application.port.in;

public interface ArticleViewRecordUseCase {

    void recordView(long articleId, String ipAddress);
}
