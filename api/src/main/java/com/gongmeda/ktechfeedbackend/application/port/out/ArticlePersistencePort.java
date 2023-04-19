package com.gongmeda.ktechfeedbackend.application.port.out;

import com.gongmeda.ktechfeedbackend.domain.Article;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

public interface ArticlePersistencePort {

    List<Article> getAll(long afterId, @Positive @NotNull Integer size);
    List<Article> getAllByAuthorId(long authorId, Long afterId, Integer size);
    List<Article> getAllByHashtag(String hashtag, Long afterId, Integer size);
    List<Article> getAllByKeyword(String keyword, Long afterId, Integer size);
    Optional<Article> getById(long articleId);
    void save(Article article);
    boolean existsByLinkUrl(String linkUrl);
    void delete(Article article);
}
