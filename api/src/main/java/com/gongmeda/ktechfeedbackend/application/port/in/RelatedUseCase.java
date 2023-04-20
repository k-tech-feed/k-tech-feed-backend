package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.domain.Author;
import java.util.List;

public interface RelatedUseCase {

    List<String> getRelatedKeywords(String keyword);
    List<Author> getRelatedAuthors(String keyword);
    List<String> getRelatedHashtags(String keyword);
    void recordSearch(String keyword);
}
