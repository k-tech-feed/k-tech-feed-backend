package com.gongmeda.ktechfeedbackend.application.service;

import com.gongmeda.ktechfeedbackend.application.event.ArticleSearchedEvent;
import com.gongmeda.ktechfeedbackend.application.event.ArticleViewedEvent;
import com.gongmeda.ktechfeedbackend.application.port.in.AddArticleCommand;
import com.gongmeda.ktechfeedbackend.application.port.in.ArticleUseCase;
import com.gongmeda.ktechfeedbackend.application.port.in.PagingQuery;
import com.gongmeda.ktechfeedbackend.application.port.in.UpdateArticleCommand;
import com.gongmeda.ktechfeedbackend.application.port.out.ArticlePersistencePort;
import com.gongmeda.ktechfeedbackend.application.port.out.AuthorPersistencePort;
import com.gongmeda.ktechfeedbackend.application.port.out.EventPublisher;
import com.gongmeda.ktechfeedbackend.domain.Article;
import com.gongmeda.ktechfeedbackend.domain.Author;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class ArticleService implements ArticleUseCase {

    private final ArticlePersistencePort articlePersistencePort;
    private final AuthorPersistencePort authorPersistencePort;
    private final EventPublisher eventPublisher;

    @Override
    public List<Article> getArticles(PagingQuery pagingQuery) {
        return articlePersistencePort.getAll(pagingQuery.getAfterId(), pagingQuery.getSize());
    }

    @Override
    public List<Article> getArticlesByAuthorId(long authorId, PagingQuery pagingQuery) {
        return articlePersistencePort.getAllByAuthorId(authorId, pagingQuery.getAfterId(), pagingQuery.getSize());
    }

    @Override
    public List<Article> getArticlesByHashtag(String hashtag, PagingQuery pagingQuery) {
        return articlePersistencePort.getAllByHashtag(hashtag, pagingQuery.getAfterId(), pagingQuery.getSize());
    }

    @Override
    public List<Article> getArticlesByKeyword(String keyword, PagingQuery pagingQuery) {
        // 아티클 검색 이벤트 발행
        eventPublisher.publish(new ArticleSearchedEvent(keyword));

        return articlePersistencePort.getAllByKeyword(keyword, pagingQuery.getAfterId(), pagingQuery.getSize());
    }

    @Override
    public boolean articleExistsByLinkUrl(String linkUrl) {
        return articlePersistencePort.existsByLinkUrl(linkUrl);
    }

    @Override
    public String viewArticle(long articleId, String ipAddress) {
        Article article = articlePersistencePort.getById(articleId).orElseThrow(/*TODO 예외 수정*/);

        // 아티클 조회 이벤트 발행
        eventPublisher.publish(new ArticleViewedEvent(articleId, ipAddress));

        return article.getLinkUrl();
    }

    @Transactional
    @Override
    public void addArticle(AddArticleCommand addArticleCommand) {
        if (articlePersistencePort.existsByLinkUrl(addArticleCommand.getLinkUrl())) {
            throw new RuntimeException();   // TODO: 커스텀 예외 처리
        }

        Author author = authorPersistencePort.getById(addArticleCommand.getAuthorId())
            .orElseThrow(RuntimeException::new);// TODO: 커스텀 예외 처리
        Article article = new Article(
            author,
            addArticleCommand.getTitle(),
            addArticleCommand.getSummary(),
            addArticleCommand.getLinkUrl(),
            addArticleCommand.getThumbnailUrl(),
            addArticleCommand.getTimestamp(),
            addArticleCommand.getHashtags()
        );

        articlePersistencePort.save(article);
    }

    @Override
    public void deleteArticle(long articleId) {
        Article article = articlePersistencePort.getById(articleId)
            .orElseThrow(RuntimeException::new);    // TODO: 커스텀 예외 처리
        articlePersistencePort.delete(article);
    }

    @Transactional
    @Override
    public void updateArticle(long articleId, UpdateArticleCommand updateArticleCommand) {
        Article article = articlePersistencePort.getById(articleId)
            .orElseThrow(RuntimeException::new);    // TODO: 커스텀 예외 처리

        article.update(
            updateArticleCommand.getTitle(),
            updateArticleCommand.getSummary(),
            updateArticleCommand.getLinkUrl(),
            updateArticleCommand.getThumbnailUrl(),
            updateArticleCommand.getTimestamp(),
            updateArticleCommand.getHashtags()
        );

        articlePersistencePort.save(article);
    }
}
