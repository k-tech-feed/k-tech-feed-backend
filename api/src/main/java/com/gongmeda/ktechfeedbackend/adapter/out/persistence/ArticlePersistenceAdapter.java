package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import static com.gongmeda.ktechfeedbackend.adapter.out.persistence.QArticleJpaEntity.articleJpaEntity;

import com.gongmeda.ktechfeedbackend.application.port.out.ArticlePersistencePort;
import com.gongmeda.ktechfeedbackend.domain.Article;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
class ArticlePersistenceAdapter implements ArticlePersistencePort {

    private final ArticleRepository articleRepository;
    private final HashtagRepository hashtagRepository;
    private final ArticleMapper articleMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Article> getAllByIds(List<Long> articleIds) {
        return articleRepository.findAllById(articleIds).stream()
            .map(articleMapper::toDomain)
            .toList();
    }

    @Override
    public List<Article> getAll(long afterId, int size) {
        return queryFactory.selectFrom(articleJpaEntity)
            .where(articleJpaEntity.id.gt(afterId))
            .limit(size)
            .orderBy(articleJpaEntity.timestamp.desc())
            .fetch()
            .stream()
            .map(articleMapper::toDomain)
            .toList();
    }

    @Override
    public List<Article> getAllByAuthorId(long authorId, long afterId, int size) {
        return queryFactory.selectFrom(articleJpaEntity)
            .where(articleJpaEntity.id.gt(afterId)
                       .and(articleJpaEntity.author.id.eq(authorId)))
            .limit(size)
            .orderBy(articleJpaEntity.timestamp.desc())
            .fetch()
            .stream()
            .map(articleMapper::toDomain)
            .toList();
    }

    @Override
    public List<Article> getAllByHashtag(String hashtag, long afterId, int size) {
        return queryFactory.selectFrom(articleJpaEntity)
            .where(articleJpaEntity.id.gt(afterId)
                       .and(articleJpaEntity.hashtags.any().name.eq(hashtag)))
            .limit(size)
            .orderBy(articleJpaEntity.timestamp.desc())
            .fetch()
            .stream()
            .map(articleMapper::toDomain)
            .toList();
    }

    @Override
    public List<Article> getAllByKeyword(String keyword, long afterId, int size) {
        // TODO: 로직 고도화
        return queryFactory.selectFrom(articleJpaEntity)
            .where(articleJpaEntity.id.gt(afterId)
                       .and(articleJpaEntity.title.containsIgnoreCase(keyword)
                                .or(articleJpaEntity.summary.containsIgnoreCase(keyword))
                                .or(articleJpaEntity.hashtags.any().name.containsIgnoreCase(keyword))))
            .limit(size)
            .orderBy(articleJpaEntity.timestamp.desc())
            .fetch()
            .stream()
            .map(articleMapper::toDomain)
            .toList();
    }

    @Override
    public Optional<Article> getById(long articleId) {
        return articleRepository.findById(articleId)
            .map(articleMapper::toDomain);
    }

    @Override
    public void save(Article article) {
        ArticleJpaEntity articleJpaEntity = articleMapper.toJpaEntity(article);
        hashtagRepository.saveAll(articleJpaEntity.getHashtags());
        articleRepository.save(articleJpaEntity);
    }

    @Override
    public boolean existsByLinkUrl(String linkUrl) {
        return articleRepository.existsByLinkUrl(linkUrl);
    }

    @Override
    public void delete(Article article) {
        articleRepository.delete(articleMapper.toJpaEntity(article));
    }
}
