package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.domain.Article;
import java.util.List;

public interface ArticleUseCase {

    List<Article> getArticles(PagingQuery pagingQuery);
    List<Article> getArticlesByAuthorId(long authorId, PagingQuery pagingQuery);
    List<Article> getArticlesByHashtag(String hashtag, PagingQuery pagingQuery);
    List<Article> getArticlesByKeyword(String keyword, PagingQuery pagingQuery);
    boolean articleExistsByLinkUrl(String linkUrl);
    String viewArticle(long articleId, String ipAddress);
    void addArticle(AddArticleCommand addArticleCommand);
    void deleteArticle(long articleId);
    void updateArticle(long articleId, UpdateArticleCommand updateArticleCommand);
}
