package com.gongmeda.ktechfeedbackend.application.port.out;

import com.gongmeda.ktechfeedbackend.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorPersistencePort {

    List<Author> getAll();

    Optional<Author> getById(long authorId);

    void save(Author author);

    void delete(Author author);
}
