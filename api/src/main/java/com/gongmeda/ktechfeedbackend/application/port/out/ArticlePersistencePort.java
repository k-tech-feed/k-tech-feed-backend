package com.gongmeda.ktechfeedbackend.application.port.out;

import com.gongmeda.ktechfeedbackend.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticlePersistencePort {

    List<Article> getAllByIds(List<Long> articleIds);

    List<Article> getAll(long afterId, int size);

    List<Article> getAllByAuthorId(long authorId, long afterId, int size);

    List<Article> getAllByHashtag(String hashtag, long afterId, int size);

    List<Article> getAllByKeyword(String keyword, long afterId, int size);

    Optional<Article> getById(long articleId);

    void save(Article article);

    boolean existsByLinkUrl(String linkUrl);

    void delete(Article article);
}
