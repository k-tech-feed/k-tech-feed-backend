package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.application.port.in.AuthorUseCase;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("authors")
public class AuthorController {

    private final AuthorUseCase authorUseCase;

    @GetMapping("url")
    List<AuthorUrlResponse> getList() {
        return authorUseCase.getAllAuthors().stream().map(AuthorUrlResponse::from).toList();
    }

    @GetMapping("{id}")
    AuthorResponse get(@PathVariable @Positive long id) {
        return AuthorResponse.from(authorUseCase.getAuthor(id));
    }

    @PostMapping
    void add(@RequestBody AddAuthorRequest addAuthorRequest) {
        authorUseCase.addAuthor(addAuthorRequest.toCommand());
    }

    @PatchMapping("{id}")
    void update(@PathVariable @Positive long id, @RequestBody UpdateAuthorRequest updateAuthorRequest) {
        authorUseCase.updateAuthor(id, updateAuthorRequest.toCommand());
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable @Positive long id) {
        authorUseCase.deleteAuthor(id);
    }
}
