package com.gongmeda.ktechfeedbackend.adapter.in.web;

import com.gongmeda.ktechfeedbackend.application.port.in.AuthorUseCase;
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
import org.springframework.web.bind.annotation.RestController;

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
