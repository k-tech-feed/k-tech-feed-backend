package com.gongmeda.ktechfeedbackend.adapter.out.persistence;

import com.gongmeda.ktechfeedbackend.domain.Article;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class ArticleMapper {

    private final AuthorMapper authorMapper;
    private final HashtagMapper hashtagMapper;

    Article toDomain(ArticleJpaEntity articleJpaEntity) {
        return new Article(
            articleJpaEntity.getId(),
            authorMapper.toDomain(articleJpaEntity.getAuthor()),
            articleJpaEntity.getTitle(),
            articleJpaEntity.getSummary(),
            articleJpaEntity.getLinkUrl(),
            articleJpaEntity.getThumbnailUrl(),
            articleJpaEntity.getTimestamp(),
            articleJpaEntity.getHashtags().stream().map(hashtagMapper::toDomain).collect(Collectors.toList())
        );
    }

    ArticleJpaEntity toJpaEntity(Article article) {
        return new ArticleJpaEntity(
            article.getId(),
            authorMapper.toJpaEntity(article.getAuthor()),
            article.getTitle(),
            article.getSummary(),
            article.getLinkUrl(),
            article.getThumbnailUrl(),
            article.getTimestamp(),
            article.getHashtags().stream().map(hashtagMapper::toJpaEntity).collect(Collectors.toList())
        );
    }
}
