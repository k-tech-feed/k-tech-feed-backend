package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import com.gongmeda.ktechfeedbackend.application.port.out.TrendingPersistencePort;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.gongmeda.ktechfeedbackend.adapter.out.persistence.QArticleJpaEntity.articleJpaEntity;
import static com.gongmeda.ktechfeedbackend.adapter.out.persistence.QArticleViewRecordJpaEntity.articleViewRecordJpaEntity;
import static com.gongmeda.ktechfeedbackend.adapter.out.persistence.QHashtagJpaEntity.hashtagJpaEntity;

@RequiredArgsConstructor
@Repository
class TrendingPersistenceAdapter implements TrendingPersistencePort {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> getArticleIdsByViewCountSinceLastWeek(int size) {
        return queryFactory.select(articleJpaEntity.id)
            .from(articleJpaEntity)
            .join(articleViewRecordJpaEntity).on(articleJpaEntity.id.eq(articleViewRecordJpaEntity.articleId))
            .where(articleJpaEntity.timestamp.after(LocalDate.now().minusWeeks(1).atStartOfDay()))
            .groupBy(articleJpaEntity.id)
            .orderBy(Expressions.numberTemplate(Long.class, "count({0})", articleViewRecordJpaEntity.articleId).desc())
            .limit(size)
            .fetch();
    }

    @Override
    public List<Long> getArticleIdsByViewCountSinceLastMonth(int size) {
        return queryFactory.select(articleJpaEntity.id)
            .from(articleJpaEntity)
            .join(articleViewRecordJpaEntity).on(articleJpaEntity.id.eq(articleViewRecordJpaEntity.articleId))
            .where(articleJpaEntity.timestamp.after(LocalDate.now().minusMonths(1).atStartOfDay()))
            .groupBy(articleJpaEntity.id)
            .orderBy(Expressions.numberTemplate(Long.class, "count({0})", articleViewRecordJpaEntity.articleId).desc())
            .limit(size)
            .fetch();
    }

    @Override
    public List<String> getHashtagsByViewCountSinceLastWeek(int size) {
        return queryFactory.select(hashtagJpaEntity.name)
            .from(hashtagJpaEntity)
            .join(articleJpaEntity).on(hashtagJpaEntity.name.eq(articleJpaEntity.hashtags.any().name))
            .join(articleViewRecordJpaEntity).on(articleJpaEntity.id.eq(articleViewRecordJpaEntity.articleId))
            .where(articleJpaEntity.timestamp.after(LocalDate.now().minusWeeks(1).atStartOfDay()))
            .groupBy(hashtagJpaEntity.name)
            .orderBy(Expressions.numberTemplate(Long.class, "count({0})", articleViewRecordJpaEntity.articleId).desc())
            .limit(size)
            .fetch();
    }

    @Override
    public List<String> getHashtagsByViewCountSinceLastMonth(int size) {
        return queryFactory.select(hashtagJpaEntity.name)
            .from(hashtagJpaEntity)
            .join(articleJpaEntity).on(hashtagJpaEntity.name.eq(articleJpaEntity.hashtags.any().name))
            .join(articleViewRecordJpaEntity).on(articleJpaEntity.id.eq(articleViewRecordJpaEntity.articleId))
            .where(articleJpaEntity.timestamp.after(LocalDate.now().minusMonths(1).atStartOfDay()))
            .groupBy(hashtagJpaEntity.name)
            .orderBy(Expressions.numberTemplate(Long.class, "count({0})", articleViewRecordJpaEntity.articleId).desc())
            .limit(size)
            .fetch();
    }
}
