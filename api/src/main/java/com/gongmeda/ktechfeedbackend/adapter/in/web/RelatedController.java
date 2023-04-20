package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.application.port.in.RelatedUseCase;
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
@RequestMapping("related")
public class RelatedController {

    private final RelatedUseCase relatedUseCase;

    @GetMapping("keywords")
    List<String> getRelatedKeywords(@RequestParam String keyword) {
        return relatedUseCase.getRelatedKeywords(keyword);
    }

    @GetMapping("authors")
    List<AuthorSimpleResponse> getRelatedAuthors(@RequestParam String keyword) {
        return relatedUseCase.getRelatedAuthors(keyword).stream()
            .map(AuthorSimpleResponse::from).toList();
    }

    @GetMapping("hashtags")
    List<String> getRelatedHashtags(@RequestParam String keyword) {
        return relatedUseCase.getRelatedHashtags(keyword);
    }
}
