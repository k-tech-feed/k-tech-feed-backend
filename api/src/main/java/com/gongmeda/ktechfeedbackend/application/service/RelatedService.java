package com.gongmeda.ktechfeedbackend.application.service;

import com.gongmeda.ktechfeedbackend.application.port.in.RelatedUseCase;
import com.gongmeda.ktechfeedbackend.application.port.out.RelatedPersistencePort;
import com.gongmeda.ktechfeedbackend.domain.Author;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class RelatedService implements RelatedUseCase {

    private final RelatedPersistencePort relatedPersistencePort;

    @Override
    public List<String> getRelatedKeywords(String keyword) {
        return relatedPersistencePort.getRelatedKeywords(keyword);
    }

    @Override
    public List<Author> getRelatedAuthors(String keyword) {
        return relatedPersistencePort.getRelatedAuthors(keyword);
    }

    @Override
    public List<String> getRelatedHashtags(String keyword) {
        return relatedPersistencePort.getRelatedHashtags(keyword);
    }

    @Override
    public void recordSearch(String keyword) {
        relatedPersistencePort.recordSearch(keyword);
    }
}
