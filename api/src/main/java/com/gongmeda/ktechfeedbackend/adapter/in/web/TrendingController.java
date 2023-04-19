package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.application.port.in.TrendingType;
import com.gongmeda.ktechfeedbackend.application.port.in.TrendingUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("trending")
public class TrendingController {

    private final TrendingUseCase trendingUseCase;

    @GetMapping("articles")
    List<ArticleSimpleResponse> getTrendingArticles(@RequestParam TrendingType type) {
        return trendingUseCase.getTrendingArticles(type).stream()
            .map(ArticleSimpleResponse::from)
            .toList();
    }

    @GetMapping("hashtags")
    List<String> getTrendingHashtags(@RequestParam TrendingType type) {
        return trendingUseCase.getTrendingHashtags(type);
    }
}
