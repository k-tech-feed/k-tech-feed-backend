package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.application.port.in.ArticleUseCase;
import com.gongmeda.ktechfeedbackend.application.port.in.PagingQuery;
import com.gongmeda.ktechfeedbackend.domain.Article;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("articles")
class ArticleController {

    private final ArticleUseCase articleUseCase;

    @GetMapping
    List<ArticleResponse> getList(
        PagingQuery pagingQuery,
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String hashtag,
        @RequestParam(required = false) Long authorId
    ) {
        List<Article> result;
        if (keyword != null) {
            result = articleUseCase.getArticlesByKeyword(keyword, pagingQuery);
        } else if (hashtag != null) {
            result = articleUseCase.getArticlesByHashtag(hashtag, pagingQuery);
        } else if (authorId != null) {
            result = articleUseCase.getArticlesByAuthorId(authorId, pagingQuery);
        } else {
            result = articleUseCase.getArticles(pagingQuery);
        }
        return result.stream().map(ArticleResponse::from).toList();
    }

    @GetMapping("{id}")
    RedirectView view(@PathVariable @Positive long id, HttpServletRequest request) {
        String redirectUrl = articleUseCase.viewArticle(id, request.getRemoteAddr());
        return new RedirectView(redirectUrl);
    }

    @PostMapping
    void add(@RequestBody AddArticleRequest addArticleRequest) {
        articleUseCase.addArticle(addArticleRequest.toCommand());
    }

    @PatchMapping("{id}")
    void update(@PathVariable @Positive long id, @RequestBody UpdateArticleRequest updateArticleRequest) {
        articleUseCase.updateArticle(id, updateArticleRequest.toCommand());
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable @Positive long id) {
        articleUseCase.deleteArticle(id);
    }
}
