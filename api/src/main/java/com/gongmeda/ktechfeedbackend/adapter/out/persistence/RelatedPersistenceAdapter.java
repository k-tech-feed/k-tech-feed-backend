package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import static com.gongmeda.ktechfeedbackend.adapter.out.persistence.QAuthorJpaEntity.authorJpaEntity;
import static com.gongmeda.ktechfeedbackend.adapter.out.persistence.QHashtagJpaEntity.hashtagJpaEntity;
import static com.gongmeda.ktechfeedbackend.adapter.out.persistence.QSearchRecordJpaEntity.searchRecordJpaEntity;

import com.gongmeda.ktechfeedbackend.application.port.out.AuthorPersistencePort;
import com.gongmeda.ktechfeedbackend.application.port.out.RelatedPersistencePort;
import com.gongmeda.ktechfeedbackend.domain.Author;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
class RelatedPersistenceAdapter implements RelatedPersistencePort {

    private final JPAQueryFactory queryFactory;
    private final AuthorMapper authorMapper;
    private final EntityManager entityManager;

    @Override
    public List<String> getRelatedKeywords(String keyword) {
        return queryFactory.selectFrom(searchRecordJpaEntity)
            .where(searchRecordJpaEntity.keyword.startsWith(keyword))
            .orderBy(searchRecordJpaEntity.searchCount.desc())
            .limit(3)
            .fetch()
            .stream()
            .map(SearchRecordJpaEntity::getKeyword)
            .toList();
    }

    @Override
    public List<Author> getRelatedAuthors(String keyword) {
        return queryFactory.selectFrom(authorJpaEntity)
            .where(authorJpaEntity.name.contains(keyword))
            .limit(3)
            .fetch()
            .stream()
            .map(authorMapper::toDomain)
            .toList();
    }

    @Override
    public List<String> getRelatedHashtags(String keyword) {
        return queryFactory.selectFrom(hashtagJpaEntity)
            .where(hashtagJpaEntity.name.startsWith(keyword))
            .limit(3)
            .fetch()
            .stream()
            .map(HashtagJpaEntity::getName)
            .toList();
    }

    @Transactional
    @Override
    public void recordSearch(String keyword) {
        SearchRecordJpaEntity searchRecord = queryFactory.selectFrom(searchRecordJpaEntity)
            .where(searchRecordJpaEntity.keyword.eq(keyword))
            .fetchFirst();

        if (searchRecord == null) {
            searchRecord = new SearchRecordJpaEntity(keyword);
            entityManager.persist(searchRecord);
        } else {
            searchRecord.incrementCount();
            entityManager.merge(searchRecord);
        }
    }
}
