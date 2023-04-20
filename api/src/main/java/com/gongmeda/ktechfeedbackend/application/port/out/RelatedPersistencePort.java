package com.gongmeda.ktechfeedbackend.application.port.out;

import com.gongmeda.ktechfeedbackend.domain.Author;
import java.util.List;

public interface RelatedPersistencePort {

    List<String> getRelatedKeywords(String keyword);
    List<Author> getRelatedAuthors(String keyword);
    List<String> getRelatedHashtags(String keyword);
    void recordSearch(String keyword);
}
