package com.gongmeda.ktechfeedbackend.application.port.in;

import com.gongmeda.ktechfeedbackend.domain.Author;

import java.util.List;

public interface AuthorUseCase {

    List<Author> getAllAuthors();

    Author getAuthor(long authorId);

    void addAuthor(AddAuthorCommand addAuthorCommand);

    void deleteAuthor(long authorId);

    void updateAuthor(long authorId, UpdateAuthorCommand updateAuthorCommand);
}
